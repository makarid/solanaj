package org.p2p.solanaj.programs.raydium.layouts;

import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.utils.ByteUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@Getter
@Setter
@ToString
public class StakeInfoLayout {

  //    u64('state'),
  //    u64('nonce'),
  //    publicKey('poolLpTokenAccount'),
  //    publicKey('poolRewardTokenAccount'),
  //    publicKey('owner'),
  //    publicKey('feeOwner'),
  //    u64('feeY'),
  //    u64('feeX'),
  //    u64('totalReward'),
  //    u128('rewardPerShareNet'),
  //    u64('lastBlock'),
  //    u64('rewardPerBlock')

  private long state;
  private static int STATE_OFFSET = 0;
  private long nonce;
  private static int NONCE_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  private PublicKey poolLpTokenAccount;
  private static int POOL_LP_TOKEN_ACCOUNT_OFFSET = NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  private PublicKey poolRewardTokenAccount;
  private static int POOL_REWARD_TOKEN_ACCOUNT_OFFSET =
      POOL_LP_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  private PublicKey owner;
  private static int OWNER_OFFSET = POOL_REWARD_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  private PublicKey feeOwner;
  private static int FEE_OWNER_OFFSET = OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  private long feeY;
  private static int FEE_Y_OFFSET = FEE_OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  private long feeX;
  private static int FEE_X_OFFSET = FEE_Y_OFFSET + ByteUtils.UINT_64_LENGTH;
  private long totalReward;
  private static int TOTAL_REWARD_OFFSET = FEE_X_OFFSET + ByteUtils.UINT_64_LENGTH;
  private long rewardPerShareNet;
  private static int REWARD_PER_SHARE_NET_OFFSET = TOTAL_REWARD_OFFSET + ByteUtils.UINT_64_LENGTH;
  private long lastBlock;
  private static int LAST_BLOCK_OFFSET = REWARD_PER_SHARE_NET_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  private long rewardPerBlock;
  private static int REWARD_PER_BLOCK_OFFSET = LAST_BLOCK_OFFSET + ByteUtils.UINT_64_LENGTH;

  public static StakeInfoLayout readData(byte[] data) {
    StakeInfoLayout stakeInfoLayout = new StakeInfoLayout();

    data = Base64.getDecoder().decode(data);

    stakeInfoLayout.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());
    stakeInfoLayout.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());
    stakeInfoLayout.setPoolLpTokenAccount(PublicKey.readPubkey(data, POOL_LP_TOKEN_ACCOUNT_OFFSET));
    stakeInfoLayout.setPoolRewardTokenAccount(
        PublicKey.readPubkey(data, POOL_REWARD_TOKEN_ACCOUNT_OFFSET));
    stakeInfoLayout.setOwner(PublicKey.readPubkey(data, OWNER_OFFSET));
    stakeInfoLayout.setFeeOwner(PublicKey.readPubkey(data, FEE_OWNER_OFFSET));
    stakeInfoLayout.setFeeY(ByteUtils.readUint64(data, FEE_Y_OFFSET).longValue());
    stakeInfoLayout.setFeeX(ByteUtils.readUint64(data, FEE_X_OFFSET).longValue());
    stakeInfoLayout.setTotalReward(ByteUtils.readUint64(data, TOTAL_REWARD_OFFSET).longValue());
    stakeInfoLayout.setRewardPerShareNet(
        ByteUtils.readUint128(data, REWARD_PER_SHARE_NET_OFFSET).longValue());
    stakeInfoLayout.setLastBlock(ByteUtils.readUint64(data, LAST_BLOCK_OFFSET).longValue());
    stakeInfoLayout.setRewardPerBlock(
        ByteUtils.readUint64(data, REWARD_PER_BLOCK_OFFSET).longValue());

    return stakeInfoLayout;
  }
}
