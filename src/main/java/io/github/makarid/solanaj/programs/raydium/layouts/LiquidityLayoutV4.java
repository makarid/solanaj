package io.github.makarid.solanaj.programs.raydium.layouts;

import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.utils.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@ToString
@Getter
@Setter
public class LiquidityLayoutV4 {

  //    u64("status")
  private long status;
  private static final int STATUS_OFFSET = 0;
  //    u64("nonce"),
  private long nonce;
  private static final int NONCE_OFFSET = STATUS_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("maxOrder"),
  private long maxOrder;
  private static final int MAX_ORDER_OFFSET = NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("depth"),
  private long depth;
  private static final int DEPTH_OFFSET = MAX_ORDER_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("baseDecimal"),
  private long baseDecimal;
  private static final int BASE_DECIMAL_OFFSET = DEPTH_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("quoteDecimal"),
  private long quoteDecimal;
  private static final int QUOTE_DECIMAL_OFFSET = BASE_DECIMAL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("state"),
  private long state;
  private static final int STATE_OFFSET = QUOTE_DECIMAL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("resetFlag"),
  private long resetFlag;
  private static final int RESET_FLAG_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minSize"),
  private long minSize;
  private static final int MIN_SIZE_OFFSET = RESET_FLAG_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("volMaxCutRatio"),
  private long volMaxCutRatio;
  private static final int VOL_MAX_CUT_RATIO_OFFSET = MIN_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("amountWaveRatio"),
  private long amountWaveRatio;
  private static final int AMOUNT_WAVE_RATIO_OFFSET =
      VOL_MAX_CUT_RATIO_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("baseLotSize"),
  private long baseLotSize;
  private static final int BASE_LOT_SIZE_OFFSET =
      AMOUNT_WAVE_RATIO_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("quoteLotSize"),
  private long quoteLotSize;
  private static final int QUOTE_LOT_SIZE_OFFSET = BASE_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minPriceMultiplier"),
  private long minPriceMultiplier;
  private static final int MIN_PRICE_MULTIPLIER_OFFSET =
      QUOTE_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("maxPriceMultiplier"),
  private long maxPriceMultiplier;
  private static final int MAX_PRICE_MULTIPLIER_OFFSET =
      MIN_PRICE_MULTIPLIER_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("systemDecimalValue"),
  private long systemDecimalValue;
  private static final int SYSTEM_DECIMAL_VALUE_OFFSET =
      MAX_PRICE_MULTIPLIER_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minSeparateNumerator"),
  private long minSeparateNumerator;
  private static final int MIN_SEPARATE_NUMERATOR_OFFSET =
      SYSTEM_DECIMAL_VALUE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minSeparateDenominator"),
  private long minSeparateDenominator;
  private static final int MIN_SEPARATE_DENOMINATOR_OFFSET =
      MIN_SEPARATE_NUMERATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("tradeFeeNumerator"),
  private long tradeFeeNumerator;
  private static final int TRADE_FEE_NUMERATOR_OFFSET =
      MIN_SEPARATE_DENOMINATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("tradeFeeDenominator"),
  private long tradeFeeDenominator;
  private static final int TRADE_FEE_DENOMINATOR_OFFSET =
      TRADE_FEE_NUMERATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("pnlNumerator"),
  private long pnlNumerator;
  private static final int PNL_NUMERATOR_OFFSET =
      TRADE_FEE_DENOMINATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("pnlDenominator"),
  private long pnlDenominator;
  private static final int PNL_DENOMINATOR_OFFSET = PNL_NUMERATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("swapFeeNumerator"),
  private long swapFeeNumerator;
  private static final int SWAP_FEE_NUMERATOR_OFFSET =
      PNL_DENOMINATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("swapFeeDenominator"),
  private long swapFeeDenominator;
  private static final int SWAP_FEE_DENOMINATOR_OFFSET =
      SWAP_FEE_NUMERATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("baseNeedTakePnl"),
  private long baseNeedTakePnl;
  private static final int BASE_NEED_TAKE_PNL_OFFSET =
      SWAP_FEE_DENOMINATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("quoteNeedTakePnl"),
  private long quoteNeedTakePnl;
  private static final int QUOTE_NEED_TAKE_PNL_OFFSET =
      BASE_NEED_TAKE_PNL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("quoteTotalPnl"),
  private long quoteTotalPnl;
  private static final int QUOTE_TOTAL_PNL_OFFSET =
      QUOTE_NEED_TAKE_PNL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("baseTotalPnl"),
  private long baseTotalPnl;
  private static final int BASE_TOTAL_PNL_OFFSET =
      QUOTE_TOTAL_PNL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u128("quoteTotalDeposited"),
  private long quoteTotalDeposited;
  private static final int QUOTE_TOTAL_DEPOSITED_OFFSET =
      BASE_TOTAL_PNL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u128("baseTotalDeposited"),
  private long baseTotalDeposited;
  private static final int BASE_TOTAL_DEPOSITED_OFFSET =
      QUOTE_TOTAL_DEPOSITED_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapBaseInAmount"),
  private long swapBaseInAmount;
  private static final int SWAP_BASE_IN_AMOUNT_OFFSET =
      BASE_TOTAL_DEPOSITED_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapQuoteOutAmount"),
  private long swapQuoteOutAmount;
  private static final int SWAP_QUOTE_OUT_AMOUNT_OFFSET =
      SWAP_BASE_IN_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64("swapBase2QuoteFee"),
  private long swapBase2QuoteFee;
  private static final int SWAP_BASE2_QUOTE_FEE_OFFSET =
      SWAP_QUOTE_OUT_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapQuoteInAmount"),
  private long swapQuoteInAmount;
  private static final int SWAP_QUOTE_IN_AMOUNT_OFFSET =
      SWAP_BASE2_QUOTE_FEE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u128("swapBaseOutAmount"),
  private long swapBaseOutAmount;
  private static final int SWAP_BASE_OUT_AMOUNT_OFFSET =
      SWAP_QUOTE_IN_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64("swapQuote2BaseFee"),
  private long swapQuote2BaseFee;
  private static final int SWAP_QUOTE2_BASE_FEE_OFFSET =
      SWAP_BASE_OUT_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    // amm vault
  //    publicKey("baseVault"),
  private PublicKey baseVault;
  private static final int BASE_VAULT_OFFSET =
      SWAP_QUOTE2_BASE_FEE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey("quoteVault"),
  private PublicKey quoteVault;
  private static final int QUOTE_VAULT_OFFSET = BASE_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    // mint
  //    publicKey("baseMint"),
  private PublicKey baseMint;
  private static final int BASE_MINT_OFFSET = QUOTE_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("quoteMint"),
  private PublicKey quoteMint;
  private static final int QUOTE_MINT_OFFSET = BASE_MINT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("lpMint"),
  private PublicKey lpMint;
  private static final int LP_MINT_OFFSET = QUOTE_MINT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    // market
  //    publicKey("openOrders"),
  private PublicKey openOrders;
  private static final int OPEN_ORDERS_OFFSET = LP_MINT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("marketId"),
  private PublicKey marketId;
  private static final int MARKET_ID_OFFSET = OPEN_ORDERS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("marketProgramId"),
  private PublicKey marketProgramId;
  private static final int MARKET_PROGRAM_ID_OFFSET =
      MARKET_ID_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("targetOrders"),
  private PublicKey targetOrders;
  private static final int TARGET_ORDERS_OFFSET =
      MARKET_PROGRAM_ID_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("withdrawQueue"),
  private PublicKey withdrawQueue;
  private static final int WITHDRAW_QUEUE_OFFSET =
      TARGET_ORDERS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("tempLpVault"),
  private PublicKey tempLpVault;
  private static final int TEMP_LP_VAULT_OFFSET =
      WITHDRAW_QUEUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("owner"),
  private PublicKey owner;
  private static final int OWNER_OFFSET = TEMP_LP_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("pnlOwner"),
  private PublicKey pnlOwner;
  private static final int PNL_OWNER_OFFSET = OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  public static LiquidityLayoutV4 readData(byte[] data) {
    LiquidityLayoutV4 liquidityLayoutV4 = new LiquidityLayoutV4();

    data = Base64.getDecoder().decode(data);

    liquidityLayoutV4.setStatus(ByteUtils.readUint64(data, STATUS_OFFSET).longValue());

    liquidityLayoutV4.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());

    liquidityLayoutV4.setMaxOrder(ByteUtils.readUint64(data, MAX_ORDER_OFFSET).longValue());

    liquidityLayoutV4.setDepth(ByteUtils.readUint64(data, DEPTH_OFFSET).longValue());

    liquidityLayoutV4.setBaseDecimal(ByteUtils.readUint64(data, BASE_DECIMAL_OFFSET).longValue());

    liquidityLayoutV4.setQuoteDecimal(ByteUtils.readUint64(data, QUOTE_DECIMAL_OFFSET).longValue());

    liquidityLayoutV4.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());

    liquidityLayoutV4.setResetFlag(ByteUtils.readUint64(data, RESET_FLAG_OFFSET).longValue());

    liquidityLayoutV4.setMinSize(ByteUtils.readUint64(data, MIN_SIZE_OFFSET).longValue());

    liquidityLayoutV4.setVolMaxCutRatio(
        ByteUtils.readUint64(data, VOL_MAX_CUT_RATIO_OFFSET).longValue());

    liquidityLayoutV4.setAmountWaveRatio(
        ByteUtils.readUint64(data, AMOUNT_WAVE_RATIO_OFFSET).longValue());

    liquidityLayoutV4.setBaseLotSize(ByteUtils.readUint64(data, BASE_LOT_SIZE_OFFSET).longValue());

    liquidityLayoutV4.setQuoteLotSize(
        ByteUtils.readUint64(data, QUOTE_LOT_SIZE_OFFSET).longValue());

    liquidityLayoutV4.setMinPriceMultiplier(
        ByteUtils.readUint64(data, MIN_PRICE_MULTIPLIER_OFFSET).longValue());

    liquidityLayoutV4.setMaxPriceMultiplier(
        ByteUtils.readUint64(data, MAX_PRICE_MULTIPLIER_OFFSET).longValue());

    liquidityLayoutV4.setSystemDecimalValue(
        ByteUtils.readUint64(data, SYSTEM_DECIMAL_VALUE_OFFSET).longValue());

    // Fees
    liquidityLayoutV4.setMinSeparateNumerator(
        ByteUtils.readUint64(data, MIN_SEPARATE_NUMERATOR_OFFSET).longValue());

    liquidityLayoutV4.setMinSeparateDenominator(
        ByteUtils.readUint64(data, MIN_SEPARATE_DENOMINATOR_OFFSET).longValue());

    liquidityLayoutV4.setTradeFeeNumerator(
        ByteUtils.readUint64(data, TRADE_FEE_NUMERATOR_OFFSET).longValue());

    liquidityLayoutV4.setTradeFeeDenominator(
        ByteUtils.readUint64(data, TRADE_FEE_DENOMINATOR_OFFSET).longValue());

    liquidityLayoutV4.setPnlNumerator(ByteUtils.readUint64(data, PNL_NUMERATOR_OFFSET).longValue());

    liquidityLayoutV4.setPnlDenominator(
        ByteUtils.readUint64(data, PNL_DENOMINATOR_OFFSET).longValue());

    liquidityLayoutV4.setSwapFeeNumerator(
        ByteUtils.readUint64(data, SWAP_FEE_NUMERATOR_OFFSET).longValue());

    liquidityLayoutV4.setSwapFeeDenominator(
        ByteUtils.readUint64(data, BASE_NEED_TAKE_PNL_OFFSET).longValue());

    // OutPutData
    liquidityLayoutV4.setBaseNeedTakePnl(
        ByteUtils.readUint64(data, BASE_NEED_TAKE_PNL_OFFSET).longValue());

    liquidityLayoutV4.setQuoteNeedTakePnl(
        ByteUtils.readUint64(data, QUOTE_NEED_TAKE_PNL_OFFSET).longValue());

    liquidityLayoutV4.setQuoteTotalPnl(
        ByteUtils.readUint64(data, QUOTE_TOTAL_PNL_OFFSET).longValue());

    liquidityLayoutV4.setBaseTotalPnl(
        ByteUtils.readUint64(data, BASE_TOTAL_PNL_OFFSET).longValue());

    liquidityLayoutV4.setQuoteTotalDeposited(
        ByteUtils.readUint128(data, QUOTE_TOTAL_DEPOSITED_OFFSET).longValue());

    liquidityLayoutV4.setBaseTotalDeposited(
        ByteUtils.readUint128(data, BASE_TOTAL_DEPOSITED_OFFSET).longValue());

    liquidityLayoutV4.setSwapBaseInAmount(
        ByteUtils.readUint128(data, SWAP_BASE_IN_AMOUNT_OFFSET).longValue());

    liquidityLayoutV4.setSwapQuoteOutAmount(
        ByteUtils.readUint128(data, SWAP_QUOTE_OUT_AMOUNT_OFFSET).longValue());

    liquidityLayoutV4.setSwapBase2QuoteFee(
        ByteUtils.readUint64(data, SWAP_BASE2_QUOTE_FEE_OFFSET).longValue());

    liquidityLayoutV4.setSwapQuoteInAmount(
        ByteUtils.readUint128(data, SWAP_QUOTE_IN_AMOUNT_OFFSET).longValue());

    liquidityLayoutV4.setSwapBaseOutAmount(
        ByteUtils.readUint128(data, SWAP_BASE_OUT_AMOUNT_OFFSET).longValue());

    liquidityLayoutV4.setSwapQuote2BaseFee(
        ByteUtils.readUint64(data, SWAP_QUOTE2_BASE_FEE_OFFSET).longValue());

    // Public Key
    liquidityLayoutV4.setBaseVault(PublicKey.readPubkey(data, BASE_VAULT_OFFSET));

    liquidityLayoutV4.setQuoteVault(PublicKey.readPubkey(data, QUOTE_VAULT_OFFSET));

    liquidityLayoutV4.setBaseMint(PublicKey.readPubkey(data, BASE_MINT_OFFSET));

    liquidityLayoutV4.setQuoteMint(PublicKey.readPubkey(data, QUOTE_MINT_OFFSET));

    liquidityLayoutV4.setLpMint(PublicKey.readPubkey(data, LP_MINT_OFFSET));

    liquidityLayoutV4.setOpenOrders(PublicKey.readPubkey(data, OPEN_ORDERS_OFFSET));

    liquidityLayoutV4.setMarketId(PublicKey.readPubkey(data, MARKET_ID_OFFSET));

    liquidityLayoutV4.setMarketProgramId(PublicKey.readPubkey(data, MARKET_PROGRAM_ID_OFFSET));

    liquidityLayoutV4.setTargetOrders(PublicKey.readPubkey(data, TARGET_ORDERS_OFFSET));

    liquidityLayoutV4.setWithdrawQueue(PublicKey.readPubkey(data, WITHDRAW_QUEUE_OFFSET));

    liquidityLayoutV4.setTempLpVault(PublicKey.readPubkey(data, TEMP_LP_VAULT_OFFSET));

    liquidityLayoutV4.setOwner(PublicKey.readPubkey(data, OWNER_OFFSET));

    liquidityLayoutV4.setPnlOwner(PublicKey.readPubkey(data, PNL_OWNER_OFFSET));

    return liquidityLayoutV4;
  }
}
