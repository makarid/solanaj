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
public class StakeInfoLayoutV4 {

  //    u64('state'),
  private long state;
  private static int STATE_OFFSET = 0;
  //    u64('nonce'),
  private long nonce;
  private static int NONCE_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey('poolLpTokenAccount'),
  private PublicKey poolLpTokenAccount;
  private static int POOL_LP_TOKEN_ACCOUNT_OFFSET = NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey('poolRewardTokenAccount'),
  private PublicKey poolRewardTokenAccount;
  private static int POOL_REWARD_TOKEN_ACCOUNT_OFFSET =
      POOL_LP_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u64('totalReward'),
  private long totalReward;
  private static int TOTAL_REWARD_OFFSET =
      POOL_REWARD_TOKEN_ACCOUNT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u128('perShare'),
  private long perShare;
  private static int PER_SHARE_OFFSET = TOTAL_REWARD_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64('perBlock'),
  private long perBlock;
  private static int PER_BLOCK_OFFSET = PER_SHARE_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u8('option'),
  private int option;
  private static int OPTION_OFFSET = PER_BLOCK_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey('poolRewardTokenAccountB'),
  private PublicKey poolRewardTokenAccountB;
  private static int POOL_REWARD_TOKEN_ACCOUNT_B_OFFSET = OPTION_OFFSET + 1;
  //    blob(7),
  private String blob;
  private static int BLOB_OFFSET = POOL_REWARD_TOKEN_ACCOUNT_B_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u64('totalRewardB'),
  private long totalRewardB;
  private static int TOTAL_REWARD_B_OFFSET = BLOB_OFFSET + 7;
  //    u128('perShareB'),
  private long perShareB;
  private static int PER_SHARE_B_OFFSET = TOTAL_REWARD_B_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64('perBlockB'),
  private long perBlockB;
  private static int PER_BLOCK_B_OFFSET = PER_SHARE_B_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64('lastBlock'),
  private long lastBlock;
  private static int LAST_BLOCK_OFFSET = PER_BLOCK_B_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey('owner')
  private PublicKey owner;
  private static int OWNER_OFFSET = LAST_BLOCK_OFFSET + ByteUtils.UINT_64_LENGTH;

  public static StakeInfoLayoutV4 readData(byte[] data) {
    StakeInfoLayoutV4 stakeInfoLayout = new StakeInfoLayoutV4();

    data = Base64.getDecoder().decode(data);

    stakeInfoLayout.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());
    stakeInfoLayout.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());
    stakeInfoLayout.setPoolLpTokenAccount(PublicKey.readPubkey(data, POOL_LP_TOKEN_ACCOUNT_OFFSET));
    stakeInfoLayout.setPoolRewardTokenAccount(
        PublicKey.readPubkey(data, POOL_REWARD_TOKEN_ACCOUNT_OFFSET));
    stakeInfoLayout.setTotalReward(ByteUtils.readUint64(data, TOTAL_REWARD_OFFSET).longValue());
    stakeInfoLayout.setPerShare(ByteUtils.readUint128(data, PER_SHARE_OFFSET).longValue());
    stakeInfoLayout.setPerBlock(ByteUtils.readUint64(data, PER_BLOCK_OFFSET).longValue());
    stakeInfoLayout.setOption(ByteUtils.getBit(data, OPTION_OFFSET));
    stakeInfoLayout.setPoolRewardTokenAccountB(
        PublicKey.readPubkey(data, POOL_REWARD_TOKEN_ACCOUNT_B_OFFSET));
    stakeInfoLayout.setTotalRewardB(ByteUtils.readUint64(data, TOTAL_REWARD_B_OFFSET).longValue());
    stakeInfoLayout.setPerShareB(ByteUtils.readUint128(data, PER_SHARE_B_OFFSET).longValue());
    stakeInfoLayout.setPerBlockB(ByteUtils.readUint64(data, PER_BLOCK_B_OFFSET).longValue());
    stakeInfoLayout.setLastBlock(ByteUtils.readUint64(data, LAST_BLOCK_OFFSET).longValue());
    stakeInfoLayout.setOwner(PublicKey.readPubkey(data, OWNER_OFFSET));

    return stakeInfoLayout;
  }
}
