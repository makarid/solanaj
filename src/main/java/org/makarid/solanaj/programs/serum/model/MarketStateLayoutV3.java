package org.makarid.solanaj.programs.serum.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.makarid.solanaj.core.PublicKey;
import org.makarid.solanaj.utils.ByteUtils;

import java.util.Base64;

@ToString
@Getter
@Setter
public class MarketStateLayoutV3 {

  private AccountFlags accountFlags;

  private PublicKey ownAddress;
  private static final int OWN_ADDRESS_OFFSET = 13;

  private long vaultSignerNonce;
  private static final int VAULT_SIGNER_NONCE_OFFSET =
      OWN_ADDRESS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private PublicKey baseMint;
  private static final int BASE_MINT_OFFSET = VAULT_SIGNER_NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey quoteMint;
  private static final int QUOTE_MINT_OFFSET = BASE_MINT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private PublicKey baseVault;
  private static final int BASE_VAULT_OFFSET = QUOTE_MINT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private long baseDepositsTotal;
  private static final int BASE_DEPOSITS_TOTAL_OFFSET =
      BASE_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private long baseFeesAccrued;
  private static final int BASE_FEES_ACCRUED_OFFSET =
      BASE_DEPOSITS_TOTAL_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey quoteVault;
  private static final int QUOTE_VAULT_OFFSET = BASE_FEES_ACCRUED_OFFSET + ByteUtils.UINT_64_LENGTH;

  private long quoteDepositsTotal;
  private static final int QUOTE_DEPOSITS_TOTAL_OFFSET =
      QUOTE_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private long quoteFeesAccrued;
  private static final int QUOTE_FEES_ACCRUED_OFFSET =
      QUOTE_DEPOSITS_TOTAL_OFFSET + ByteUtils.UINT_64_LENGTH;

  private long quoteDustThreshold;
  private static final int QUOTE_DUST_THRESHOLD_OFFSET =
      QUOTE_FEES_ACCRUED_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey requestQueue;
  private static final int REQUEST_QUEUE_OFFSET =
      QUOTE_DUST_THRESHOLD_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey eventQueueKey;
  private static final int EVENT_QUEUE_OFFSET = REQUEST_QUEUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private PublicKey bids;
  private static final int BIDS_OFFSET = EVENT_QUEUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private PublicKey asks;
  private static final int ASKS_OFFSET = BIDS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private long baseLotSize;
  private static final int BASE_LOT_SIZE_OFFSET = ASKS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  private long quoteLotSize;
  private static final int QUOTE_LOT_SIZE_OFFSET = BASE_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;

  private long feeRateBps;
  private static final int FEE_RATE_BPS_OFFSET = QUOTE_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;

  private long referrerRebatesAccrued;
  private static final int REFERRER_REBATES_ACCRUED_OFFSET =
      FEE_RATE_BPS_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey authority;
  private static final int AUTHORITY_OFFSET =
      REFERRER_REBATES_ACCRUED_OFFSET + ByteUtils.UINT_64_LENGTH;

  private PublicKey pruneAuthority;
  private static final int PRUNE_AUTHORITY_OFFSET = AUTHORITY_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  //    blob(1024,
  //    blob(7)

  public static MarketStateLayoutV3 readData(byte[] data) {
    MarketStateLayoutV3 marketStateLayoutV3 = new MarketStateLayoutV3();

    data = Base64.getDecoder().decode(data);

    marketStateLayoutV3.setAccountFlags(AccountFlags.readAccountFlags(data));
    marketStateLayoutV3.setOwnAddress(PublicKey.readPubkey(data, OWN_ADDRESS_OFFSET));
    marketStateLayoutV3.setVaultSignerNonce(
        ByteUtils.readUint64(data, VAULT_SIGNER_NONCE_OFFSET).longValue());
    marketStateLayoutV3.setBaseMint(PublicKey.readPubkey(data, BASE_MINT_OFFSET));
    marketStateLayoutV3.setQuoteMint(PublicKey.readPubkey(data, QUOTE_MINT_OFFSET));
    marketStateLayoutV3.setBaseVault(PublicKey.readPubkey(data, BASE_VAULT_OFFSET));
    marketStateLayoutV3.setBaseDepositsTotal(
        ByteUtils.readUint64(data, BASE_DEPOSITS_TOTAL_OFFSET).longValue());
    marketStateLayoutV3.setBaseFeesAccrued(
        ByteUtils.readUint64(data, BASE_FEES_ACCRUED_OFFSET).longValue());
    marketStateLayoutV3.setQuoteVault(PublicKey.readPubkey(data, QUOTE_VAULT_OFFSET));
    marketStateLayoutV3.setQuoteDepositsTotal(
        ByteUtils.readUint64(data, QUOTE_DEPOSITS_TOTAL_OFFSET).longValue());
    marketStateLayoutV3.setQuoteFeesAccrued(
        ByteUtils.readUint64(data, QUOTE_FEES_ACCRUED_OFFSET).longValue());
    marketStateLayoutV3.setQuoteDustThreshold(
        ByteUtils.readUint64(data, QUOTE_DUST_THRESHOLD_OFFSET).longValue());
    marketStateLayoutV3.setRequestQueue(PublicKey.readPubkey(data, REQUEST_QUEUE_OFFSET));
    marketStateLayoutV3.setEventQueueKey(PublicKey.readPubkey(data, EVENT_QUEUE_OFFSET));
    marketStateLayoutV3.setBids(PublicKey.readPubkey(data, BIDS_OFFSET));
    marketStateLayoutV3.setAsks(PublicKey.readPubkey(data, ASKS_OFFSET));
    marketStateLayoutV3.setBaseLotSize(
        ByteUtils.readUint64(data, BASE_LOT_SIZE_OFFSET).longValue());
    marketStateLayoutV3.setQuoteLotSize(
        ByteUtils.readUint64(data, QUOTE_LOT_SIZE_OFFSET).longValue());
    marketStateLayoutV3.setFeeRateBps(ByteUtils.readUint64(data, FEE_RATE_BPS_OFFSET).longValue());
    marketStateLayoutV3.setReferrerRebatesAccrued(
        ByteUtils.readUint64(data, REFERRER_REBATES_ACCRUED_OFFSET).longValue());
    marketStateLayoutV3.setAuthority(PublicKey.readPubkey(data, AUTHORITY_OFFSET));
    marketStateLayoutV3.setPruneAuthority(PublicKey.readPubkey(data, PRUNE_AUTHORITY_OFFSET));

    return marketStateLayoutV3;
  }
}
