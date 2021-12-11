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
public class AmmInfoLayoutV3 {

  //    u64("status"),
  private long status;
  private static final int STATUS_OFFSET = ByteUtils.UINT_64_LENGTH;
  //    u64("nonce"),
  private long nonce;
  private static final int NONCE_OFFSET = STATUS_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("orderNum"),
  private long orderNum;
  private static final int ORDER_NUM_OFFSET = NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("depth"),
  private long depth;
  private static final int DEPTH_OFFSET = ORDER_NUM_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64('coinDecimals'),
  private long coinDecimals;
  private static final int COIN_DECIMAL_OFFSET = DEPTH_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64('pcDecimals'),
  private long pcDecimals;
  private static final int PC_DECIMAL_OFFSET = COIN_DECIMAL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("state"),
  private long state;
  private static final int STATE_OFFSET = PC_DECIMAL_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("resetFlag"),
  private long resetFlag;
  private static final int RESET_FLAG_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("fee"),
  private long fee;
  private static final int FEE_OFFSET = RESET_FLAG_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("min_separate"),
  private long minSeparate;
  private static final int MIN_SEPARATE_OFFSET = FEE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minSize"),
  private long minSize;
  private static final int MIN_SIZE_OFFSET = MIN_SEPARATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("volMaxCutRatio"),
  private long volMaxCutRatio;
  private static final int VOL_MAX_CUT_RATIO_OFFSET = MIN_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("pnlRatio"),
  private long pnlRatio;
  private static final int PNL_RATIO_OFFSET = VOL_MAX_CUT_RATIO_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("amountWaveRatio"),
  private long amountWaveRatio;
  private static final int AMOUNT_WAVE_RATIO_OFFSET = PNL_RATIO_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64('coinLotSize'),
  private long coinLotSize;
  private static final int COIN_LOT_SIZE_OFFSET =
      AMOUNT_WAVE_RATIO_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("pcLotSize"),
  private long pcLotSize;
  private static final int PC_LOT_SIZE_OFFSET = COIN_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minPriceMultiplier"),
  private long minPriceMultiplier;
  private static final int MIN_PRICE_MULTIPLIER_OFFSET =
      PC_LOT_SIZE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("maxPriceMultiplier"),
  private long maxPriceMultiplier;
  private static final int MAX_PRICE_MULTIPLIER_OFFSET =
      MIN_PRICE_MULTIPLIER_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("needTakePnlCoin"),
  private long needTakePnlCoin;
  private static final int NEED_TAKE_PNL_COIN_OFFSET =
      MAX_PRICE_MULTIPLIER_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("needTakePnlPc"),
  private long needTakePnlPc;
  private static final int NEED_TAKE_PNL_PC_OFFSET =
      NEED_TAKE_PNL_COIN_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("totalPnlX"),
  private long totalPnlX;
  private static final int TOTAL_PNL_X_OFFSET = NEED_TAKE_PNL_PC_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("totalPnlX"),
  private long totalPnlY;
  private static final int TOTAL_PNL_Y_OFFSET = TOTAL_PNL_X_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("poolTotalDepositPc"),
  private long poolTotalDepositPc;
  private static final int POOL_TOTAL_DEPOSIT_PC_OFFSET =
      TOTAL_PNL_Y_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("poolTotalDepositCoin"),
  private long poolTotalDepositCoin;
  private static final int POOL_TOTAL_DEPOSIT_COIN_OFFSET =
      POOL_TOTAL_DEPOSIT_PC_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("systemDecimalValue"),
  private long systemDecimalValue;
  private static final int SYSTEM_DECIMAL_VALUE_OFFSET =
      POOL_TOTAL_DEPOSIT_COIN_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    // amm vault
  //    publicKey('poolCoinTokenAccount'),
  private PublicKey poolCoinTokenAccount;
  private static final int POOL_COIN_TOKEN_ACCOUNT_OFFSET =
      SYSTEM_DECIMAL_VALUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('poolPcTokenAccount'),
  private PublicKey poolPcTokenAccount;
  private static final int POOL_PC_TOKEN_ACCOUNT_OFFSET =
      POOL_COIN_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('coinMintAddress'),
  private PublicKey coinMintAddress;
  private static final int COIN_MINT_ADDRESS_OFFSET =
      POOL_PC_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('pcMintAddress'),
  private PublicKey pcMintAddress;
  private static final int PC_MINT_ADDRESS_OFFSET =
      COIN_MINT_ADDRESS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('lpMintAddress'),
  private PublicKey lpMintAddress;
  private static final int LP_MINT_ADDRESS_OFFSET =
      PC_MINT_ADDRESS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    // market
  //    publicKey('ammOpenOrders'),
  private PublicKey ammOpenOrders;
  private static final int AMM_OPEN_ORDERS_OFFSET =
      LP_MINT_ADDRESS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('serumMarket'),
  private PublicKey serumMarket;
  private static final int SERUM_MARKET_OFFSET =
      AMM_OPEN_ORDERS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('serumProgramId'),
  private PublicKey serumProgramId;
  private static final int SERUM_PROGRAM_ID_OFFSET =
      SERUM_MARKET_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('ammTargetOrders'),
  private PublicKey ammTargetOrders;
  private static final int AMM_TARGET_ORDERS_OFFSET =
      SERUM_PROGRAM_ID_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('ammQuantities'),
  private PublicKey ammQuantities;
  private static final int AMM_QUANTITIES_OFFSET =
      AMM_TARGET_ORDERS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('poolWithdrawQueue'),
  private PublicKey poolWithdrawQueue;
  private static final int POOL_WITHDRAW_QUEUE_OFFSET =
      AMM_QUANTITIES_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('poolTempLpTokenAccount'),
  private PublicKey poolTempLpTokenAccount;
  private static final int POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET =
      POOL_WITHDRAW_QUEUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("ammOwner"),
  private PublicKey ammOwner;
  private static final int AMM_OWNER_OFFSET =
      POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("pnlOwner"),
  private PublicKey pnlOwner;
  private static final int PNL_OWNER_OFFSET = AMM_OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("srmTokenAccount"),
  private PublicKey srmTokenAccount;
  private static final int SRM_TOKEN_ACCOUNT_OFFSET =
      PNL_OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  public static AmmInfoLayoutV3 readData(byte[] data) {
    AmmInfoLayoutV3 ammInfoLayoutV3 = new AmmInfoLayoutV3();

    data = Base64.getDecoder().decode(data);

    ammInfoLayoutV3.setStatus(ByteUtils.readUint64(data, STATUS_OFFSET).longValue());

    ammInfoLayoutV3.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());

    ammInfoLayoutV3.setOrderNum(ByteUtils.readUint64(data, ORDER_NUM_OFFSET).longValue());

    ammInfoLayoutV3.setDepth(ByteUtils.readUint64(data, DEPTH_OFFSET).longValue());

    ammInfoLayoutV3.setCoinDecimals(ByteUtils.readUint64(data, COIN_DECIMAL_OFFSET).longValue());

    ammInfoLayoutV3.setPcDecimals(ByteUtils.readUint64(data, PC_DECIMAL_OFFSET).longValue());

    ammInfoLayoutV3.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());

    ammInfoLayoutV3.setResetFlag(ByteUtils.readUint64(data, RESET_FLAG_OFFSET).longValue());

    ammInfoLayoutV3.setFee(ByteUtils.readUint64(data, FEE_OFFSET).longValue());

    ammInfoLayoutV3.setMinSeparate(ByteUtils.readUint64(data, MIN_SEPARATE_OFFSET).longValue());

    ammInfoLayoutV3.setMinSize(ByteUtils.readUint64(data, MIN_SIZE_OFFSET).longValue());

    ammInfoLayoutV3.setVolMaxCutRatio(
        ByteUtils.readUint64(data, VOL_MAX_CUT_RATIO_OFFSET).longValue());

    ammInfoLayoutV3.setPnlRatio(ByteUtils.readUint64(data, PNL_RATIO_OFFSET).longValue());

    ammInfoLayoutV3.setAmountWaveRatio(
        ByteUtils.readUint64(data, AMOUNT_WAVE_RATIO_OFFSET).longValue());

    ammInfoLayoutV3.setCoinLotSize(ByteUtils.readUint64(data, COIN_LOT_SIZE_OFFSET).longValue());

    ammInfoLayoutV3.setPcLotSize(ByteUtils.readUint64(data, PC_LOT_SIZE_OFFSET).longValue());

    ammInfoLayoutV3.setMinPriceMultiplier(
        ByteUtils.readUint64(data, MIN_PRICE_MULTIPLIER_OFFSET).longValue());

    ammInfoLayoutV3.setMaxPriceMultiplier(
        ByteUtils.readUint64(data, MAX_PRICE_MULTIPLIER_OFFSET).longValue());

    ammInfoLayoutV3.setNeedTakePnlCoin(
        ByteUtils.readUint64(data, NEED_TAKE_PNL_COIN_OFFSET).longValue());

    ammInfoLayoutV3.setNeedTakePnlPc(
        ByteUtils.readUint64(data, NEED_TAKE_PNL_PC_OFFSET).longValue());

    ammInfoLayoutV3.setTotalPnlX(ByteUtils.readUint64(data, TOTAL_PNL_X_OFFSET).longValue());

    ammInfoLayoutV3.setTotalPnlY(ByteUtils.readUint64(data, TOTAL_PNL_Y_OFFSET).longValue());

    ammInfoLayoutV3.setPoolTotalDepositPc(
        ByteUtils.readUint64(data, POOL_TOTAL_DEPOSIT_PC_OFFSET).longValue());

    ammInfoLayoutV3.setPoolTotalDepositCoin(
        ByteUtils.readUint64(data, POOL_TOTAL_DEPOSIT_COIN_OFFSET).longValue());

    ammInfoLayoutV3.setSystemDecimalValue(
        ByteUtils.readUint64(data, SYSTEM_DECIMAL_VALUE_OFFSET).longValue());

    // Public Key
    ammInfoLayoutV3.setPoolCoinTokenAccount(
        PublicKey.readPubkey(data, POOL_COIN_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV3.setPoolPcTokenAccount(PublicKey.readPubkey(data, POOL_PC_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV3.setCoinMintAddress(PublicKey.readPubkey(data, COIN_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV3.setPcMintAddress(PublicKey.readPubkey(data, PC_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV3.setLpMintAddress(PublicKey.readPubkey(data, LP_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV3.setAmmOpenOrders(PublicKey.readPubkey(data, AMM_OPEN_ORDERS_OFFSET));

    ammInfoLayoutV3.setSerumMarket(PublicKey.readPubkey(data, SERUM_MARKET_OFFSET));

    ammInfoLayoutV3.setSerumProgramId(PublicKey.readPubkey(data, SERUM_PROGRAM_ID_OFFSET));

    ammInfoLayoutV3.setAmmTargetOrders(PublicKey.readPubkey(data, AMM_TARGET_ORDERS_OFFSET));

    ammInfoLayoutV3.setAmmQuantities(PublicKey.readPubkey(data, AMM_QUANTITIES_OFFSET));

    ammInfoLayoutV3.setPoolWithdrawQueue(PublicKey.readPubkey(data, POOL_WITHDRAW_QUEUE_OFFSET));

    ammInfoLayoutV3.setPoolTempLpTokenAccount(
        PublicKey.readPubkey(data, POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV3.setAmmOwner(PublicKey.readPubkey(data, AMM_OWNER_OFFSET));

    ammInfoLayoutV3.setPnlOwner(PublicKey.readPubkey(data, PNL_OWNER_OFFSET));

    ammInfoLayoutV3.setSrmTokenAccount(PublicKey.readPubkey(data, PNL_OWNER_OFFSET));

    return ammInfoLayoutV3;
  }
}
