package io.github.makarid.solanaj.programs.serum;

import io.github.makarid.solanaj.programs.serum.model.*;
import org.bitcoinj.core.Utils;
import org.junit.Ignore;
import org.junit.Test;
import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.rpc.Cluster;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.utils.ByteUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MarketTest {

  private final RpcClient client = new RpcClient(Cluster.MAINNET);
  private static final Logger LOGGER = Logger.getLogger(MarketTest.class.getName());

  /** Uses a {@link MarketBuilder} class to retrieve data about the BTC/USDC Serum market. */
  @Test
  public void marketBuilderBtcUsdcTest() {
    // Pubkey of SRM/USDC market
    final PublicKey publicKey =
        new PublicKey("ByRys5tuUWDgL73G8JBAEfkdFf8JWBzPBDHsBVQ5vbQA"); // SRM/USDC

    final Market solUsdcMarket =
        new MarketBuilder()
            .setClient(client)
            .setPublicKey(publicKey)
            .setRetrieveOrderBooks(true)
            .build();

    final OrderBook bids = solUsdcMarket.getBidOrderBook();
    final OrderBook asks = solUsdcMarket.getAskOrderBook();

    LOGGER.info("Best bid = " + bids.getBestBid().getPrice() / 1000.0);
    LOGGER.info("Best ask = " + asks.getBestAsk().getPrice() / 1000.0);

    // Verify at least 1 bid and 1 ask (should always be for BTC/USDC)
    assertTrue(bids.getOrders().size() > 0);
    assertTrue(asks.getOrders().size() > 0);
  }

  /**
   * Verifies that {@link OrderBook} headers are properly read by {@link
   * OrderBook#readOrderBook(byte[])}
   */
  @Test
  @Ignore
  public void orderBookTest() {
    byte[] data = new byte[0];

    try {
      data = Files.readAllBytes(Paths.get("src/test/resources/orderbook.bin"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    OrderBook bidOrderBook = OrderBook.readOrderBook(data);

    LOGGER.info(bidOrderBook.getAccountFlags().toString());

    Slab slab = bidOrderBook.getSlab();

    assertNotNull(slab);
    assertEquals(141, slab.getBumpIndex());
    assertEquals(78, slab.getFreeListLen());
    assertEquals(56, slab.getFreeListHead());
    assertEquals(32, slab.getLeafCount());
  }

  /**
   * Will verify {@link ByteUtils} or {@link SerumUtils} can read seqNum and price. Currently just
   * reads price and logs it.
   */
  @Test
  public void testPriceDeserialization() {
    /* C:\apps\solanaj\lqidusdc.bin (1/12/2021 8:55:59 AM)
    StartOffset(d): 00001277, EndOffset(d): 00001292, Length(d): 00000016 */

    byte[] rawData = {
      (byte) 0xDB, (byte) 0xFE, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
      (byte) 0xFF, (byte) 0xFF, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
    };

    long seqNum = Utils.readInt64(rawData, 0);
    long price = Utils.readInt64(rawData, 8);

    LOGGER.info("Price = " + price);
    LOGGER.info("seqNum = " + seqNum);

    assertEquals(1, price);
    assertEquals(seqNum, -293L);
  }

  /** Uses a {@link MarketBuilder} class to retrieve data about the SOL/USDC Serum market. */
  @Test
  public void marketBuilderSolUsdcTest() {
    final PublicKey solUsdcPublicKey =
        new PublicKey("9wFFyRfZBsuAha4YcuxcXLKwMxJR43S7fPfQLusDBzvT");

    final Market solUsdcMarket =
        new MarketBuilder()
            .setClient(client)
            .setPublicKey(solUsdcPublicKey)
            .setRetrieveOrderBooks(true)
            .build();

    final OrderBook bids = solUsdcMarket.getBidOrderBook();
    final OrderBook asks = solUsdcMarket.getAskOrderBook();
    LOGGER.info("Market = " + solUsdcMarket.toString());

    final ArrayList<Order> asksOrders = asks.getOrders();
    asksOrders.sort(Comparator.comparingLong(Order::getPrice).reversed());
    asksOrders.forEach(
        order -> {
          System.out.printf(
              "SOL/USDC Ask: $%.4f (Quantity: %.4f)%n",
              order.getFloatPrice(), order.getFloatQuantity());
        });

    LOGGER.info("Bids");

    final ArrayList<Order> orders = bids.getOrders();
    orders.sort(Comparator.comparingLong(Order::getPrice).reversed());
    orders.forEach(
        order -> {
          System.out.printf(
              "SOL/USDC Bid: $%.4f (Quantity: %.4f)%n",
              order.getFloatPrice(), order.getFloatQuantity());
        });

    // Verify that an order exists
    assertTrue(orders.size() > 0);
  }

  /**
   * Uses a {@link MarketBuilder} class to retrieve the Event Queue from the SOL/USDC Serum market.
   */
  @Test
  public void marketBuilderEventQueueTest() {
    final PublicKey solUsdcPublicKey =
        new PublicKey("2Pbh1CvRVku1TgewMfycemghf6sU9EyuFDcNXqvRmSxc");

    final MarketBuilder solUsdcMarketBuilder =
        new MarketBuilder()
            .setPublicKey(solUsdcPublicKey)
            .setClient(client)
            .setRetrieveOrderBooks(true)
            .setRetrieveEventQueue(true);

    Market solUsdcMarket = solUsdcMarketBuilder.build();

    LOGGER.info("Market = " + solUsdcMarket.toString());
    LOGGER.info("Event Queue = " + solUsdcMarket.getEventQueue());

    List<TradeEvent> tradeEvents = solUsdcMarket.getEventQueue().getEvents();

    tradeEvents.forEach(
        tradeEvent -> {
          LOGGER.info(tradeEvent.toString());
        });
  }
}
