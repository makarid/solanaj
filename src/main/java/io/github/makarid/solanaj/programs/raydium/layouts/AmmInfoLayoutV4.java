package io.github.makarid.solanaj.programs.raydium.layouts;

import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.utils.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AmmInfoLayoutV4 {
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
  //    u64("systemDecimalValue"),
  private long systemDecimalValue;
  private static final int SYSTEM_DECIMAL_VALUE_OFFSET =
      MAX_PRICE_MULTIPLIER_OFFSET + ByteUtils.UINT_64_LENGTH;
  // Fees
  //    u64("minSeparateNumerator"),
  private long minSeparateNumerator;
  private static final int MIN_SEPARATE_NUMERATOR_OFFSET =
      SYSTEM_DECIMAL_VALUE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("minSeparateNumerator"),
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
  //    u64("pnlNumerator"),
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
  // OutPutData
  //    u64("needTakePnlCoin"),
  private long needTakePnlCoin;
  private static final int NEED_TAKE_PNL_COIN_OFFSET =
      SWAP_FEE_DENOMINATOR_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("needTakePnlPc"),
  private long needTakePnlPc;
  private static final int NEED_TAKE_PNL_PC_OFFSET =
      NEED_TAKE_PNL_COIN_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("totalPnlPc"),
  private long totalPnlPc;
  private static final int TOTAL_PNL_PC_OFFSET = NEED_TAKE_PNL_PC_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("totalPnlCoin"),
  private long totalPnlCoin;
  private static final int TOTAL_PNL_COIN_OFFSET = TOTAL_PNL_PC_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u128("poolTotalDepositPc"),
  private long poolTotalDepositPc;
  private static final int POOL_TOTAL_DEPOSIT_PC_OFFSET =
      TOTAL_PNL_COIN_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("poolTotalDepositCoin"),
  private long poolTotalDepositCoin;
  private static final int POOL_TOTAL_DEPOSIT_COIN_OFFSET =
      POOL_TOTAL_DEPOSIT_PC_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapCoinInAmount"),
  private long swapCoinInAmount;
  private static final int SWAP_COIN_IN_AMOUNT_OFFSET =
      POOL_TOTAL_DEPOSIT_COIN_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapPcOutAmount"),
  private long swapPcOutAmount;
  private static final int SWAP_PC_OUT_AMOUNT_OFFSET =
      SWAP_COIN_IN_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64("swapCoin2PcFee"),
  private long swapCoin2PcFee;
  private static final int SWAP_COIN2_PC_FEE_OFFSET =
      SWAP_PC_OUT_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u128("swapPcInAmount"),
  private long swapPcInAmount;
  private static final int SWAP_PC_IN_AMOUNT_OFFSET =
      SWAP_COIN2_PC_FEE_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u128("swapCoinOutAmount"),
  private long swapCoinOutAmount;
  private static final int SWAP_COIN_OUT_AMOUNT_OFFSET =
      SWAP_PC_IN_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64("swapPc2CoinFee"),
  private long swapPc2CoinFee;
  private static final int SWAP_PC2_COIN_FEE_OFFSET =
      SWAP_COIN_OUT_AMOUNT_OFFSET + ByteUtils.UINT_64_LENGTH;
  // amm vault
  //   publicKey('poolCoinTokenAccount'),
  private PublicKey poolCoinTokenAccount;
  private static final int POOL_COIN_TOKEN_ACCOUNT_OFFSET =
      SWAP_PC2_COIN_FEE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
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
  //    publicKey('poolWithdrawQueue'),
  private PublicKey poolWithdrawQueue;
  private static final int POOL_WITHDRAW_QUEUE_OFFSET =
      AMM_TARGET_ORDERS_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey('poolTempLpTokenAccount'),
  private PublicKey poolTempLpTokenAccount;
  private static final int POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET =
      POOL_WITHDRAW_QUEUE_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    publicKey("ammOwner"),
  private PublicKey ammOwner;
  private static final int AMM_OWNER_OFFSET =
      POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    //    publicKey("pnlOwner"),
  private PublicKey pnlOwner;
  private static final int PNL_OWNER_OFFSET = AMM_OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;

  public static AmmInfoLayoutV4 readData(byte[] data) {
    AmmInfoLayoutV4 ammInfoLayoutV4 = new AmmInfoLayoutV4();

    ammInfoLayoutV4.setStatus(ByteUtils.readUint64(data, STATUS_OFFSET).longValue());

    ammInfoLayoutV4.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());

    ammInfoLayoutV4.setOrderNum(ByteUtils.readUint64(data, ORDER_NUM_OFFSET).longValue());

    ammInfoLayoutV4.setDepth(ByteUtils.readUint64(data, DEPTH_OFFSET).longValue());

    ammInfoLayoutV4.setCoinDecimals(ByteUtils.readUint64(data, COIN_DECIMAL_OFFSET).longValue());

    ammInfoLayoutV4.setPcDecimals(ByteUtils.readUint64(data, PC_DECIMAL_OFFSET).longValue());

    ammInfoLayoutV4.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());

    ammInfoLayoutV4.setResetFlag(ByteUtils.readUint64(data, RESET_FLAG_OFFSET).longValue());

    ammInfoLayoutV4.setMinSize(ByteUtils.readUint64(data, MIN_SIZE_OFFSET).longValue());

    ammInfoLayoutV4.setVolMaxCutRatio(
        ByteUtils.readUint64(data, VOL_MAX_CUT_RATIO_OFFSET).longValue());

    ammInfoLayoutV4.setAmountWaveRatio(
        ByteUtils.readUint64(data, AMOUNT_WAVE_RATIO_OFFSET).longValue());

    ammInfoLayoutV4.setCoinLotSize(ByteUtils.readUint64(data, COIN_LOT_SIZE_OFFSET).longValue());

    ammInfoLayoutV4.setPcLotSize(ByteUtils.readUint64(data, PC_LOT_SIZE_OFFSET).longValue());

    ammInfoLayoutV4.setMinPriceMultiplier(
        ByteUtils.readUint64(data, MIN_PRICE_MULTIPLIER_OFFSET).longValue());

    ammInfoLayoutV4.setMaxPriceMultiplier(
        ByteUtils.readUint64(data, MAX_PRICE_MULTIPLIER_OFFSET).longValue());

    ammInfoLayoutV4.setSystemDecimalValue(
        ByteUtils.readUint64(data, SYSTEM_DECIMAL_VALUE_OFFSET).longValue());

    // Fees
    ammInfoLayoutV4.setMinSeparateNumerator(
        ByteUtils.readUint64(data, MIN_SEPARATE_NUMERATOR_OFFSET).longValue());

    ammInfoLayoutV4.setMinSeparateDenominator(
        ByteUtils.readUint64(data, MIN_SEPARATE_DENOMINATOR_OFFSET).longValue());

    ammInfoLayoutV4.setTradeFeeNumerator(
        ByteUtils.readUint64(data, TRADE_FEE_NUMERATOR_OFFSET).longValue());

    ammInfoLayoutV4.setTradeFeeDenominator(
        ByteUtils.readUint64(data, TRADE_FEE_DENOMINATOR_OFFSET).longValue());

    ammInfoLayoutV4.setPnlNumerator(ByteUtils.readUint64(data, PNL_NUMERATOR_OFFSET).longValue());

    ammInfoLayoutV4.setPnlDenominator(
        ByteUtils.readUint64(data, PNL_DENOMINATOR_OFFSET).longValue());

    ammInfoLayoutV4.setSwapFeeNumerator(
        ByteUtils.readUint64(data, SWAP_FEE_NUMERATOR_OFFSET).longValue());

    ammInfoLayoutV4.setSwapFeeDenominator(
        ByteUtils.readUint64(data, SWAP_FEE_DENOMINATOR_OFFSET).longValue());

    // OutPutData
    ammInfoLayoutV4.setNeedTakePnlCoin(
        ByteUtils.readUint64(data, NEED_TAKE_PNL_COIN_OFFSET).longValue());

    ammInfoLayoutV4.setNeedTakePnlPc(
        ByteUtils.readUint64(data, NEED_TAKE_PNL_PC_OFFSET).longValue());

    ammInfoLayoutV4.setTotalPnlPc(ByteUtils.readUint64(data, TOTAL_PNL_PC_OFFSET).longValue());

    ammInfoLayoutV4.setTotalPnlCoin(ByteUtils.readUint64(data, TOTAL_PNL_COIN_OFFSET).longValue());

    ammInfoLayoutV4.setPoolTotalDepositPc(
        ByteUtils.readUint64(data, POOL_TOTAL_DEPOSIT_PC_OFFSET).longValue());

    ammInfoLayoutV4.setPoolTotalDepositCoin(
        ByteUtils.readUint64(data, POOL_TOTAL_DEPOSIT_COIN_OFFSET).longValue());

    ammInfoLayoutV4.setSwapCoinInAmount(
        ByteUtils.readUint64(data, SWAP_COIN_IN_AMOUNT_OFFSET).longValue());

    ammInfoLayoutV4.setSwapPcOutAmount(
        ByteUtils.readUint64(data, SWAP_PC_OUT_AMOUNT_OFFSET).longValue());

    ammInfoLayoutV4.setSwapCoin2PcFee(
        ByteUtils.readUint64(data, SWAP_COIN2_PC_FEE_OFFSET).longValue());

    ammInfoLayoutV4.setSwapPcInAmount(
        ByteUtils.readUint64(data, SWAP_PC_IN_AMOUNT_OFFSET).longValue());

    ammInfoLayoutV4.setSwapCoinOutAmount(
        ByteUtils.readUint64(data, SWAP_COIN_OUT_AMOUNT_OFFSET).longValue());

    ammInfoLayoutV4.setSwapPc2CoinFee(
        ByteUtils.readUint64(data, SWAP_PC2_COIN_FEE_OFFSET).longValue());

    // Public Key
    ammInfoLayoutV4.setPoolCoinTokenAccount(
        PublicKey.readPubkey(data, POOL_COIN_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV4.setPoolPcTokenAccount(PublicKey.readPubkey(data, POOL_PC_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV4.setCoinMintAddress(PublicKey.readPubkey(data, COIN_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV4.setPcMintAddress(PublicKey.readPubkey(data, PC_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV4.setLpMintAddress(PublicKey.readPubkey(data, LP_MINT_ADDRESS_OFFSET));

    ammInfoLayoutV4.setAmmOpenOrders(PublicKey.readPubkey(data, AMM_OPEN_ORDERS_OFFSET));

    ammInfoLayoutV4.setSerumMarket(PublicKey.readPubkey(data, SERUM_MARKET_OFFSET));

    ammInfoLayoutV4.setSerumProgramId(PublicKey.readPubkey(data, SERUM_PROGRAM_ID_OFFSET));

    ammInfoLayoutV4.setAmmTargetOrders(PublicKey.readPubkey(data, AMM_TARGET_ORDERS_OFFSET));

    ammInfoLayoutV4.setPoolWithdrawQueue(PublicKey.readPubkey(data, POOL_WITHDRAW_QUEUE_OFFSET));

    ammInfoLayoutV4.setPoolTempLpTokenAccount(
        PublicKey.readPubkey(data, POOL_TEMP_LP_TOKEN_ACCOUNT_OFFSET));

    ammInfoLayoutV4.setAmmOwner(PublicKey.readPubkey(data, AMM_OWNER_OFFSET));

    ammInfoLayoutV4.setPnlOwner(PublicKey.readPubkey(data, PNL_OWNER_OFFSET));

    return ammInfoLayoutV4;
  }
}
