package org.p2p.solanaj.programs.raydium.layouts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.utils.ByteUtils;

import java.util.Base64;

@ToString
@Getter
@Setter
public class UserStakeLayout {

  //    u64('state'),
  private long state;
  private static int STATE_OFFSET = 0;
  //    publicKey('poolId'),
  private PublicKey poolId;
  private static int POOL_ID_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey('stakerOwner'),
  private PublicKey stakerOwner;
  private static int STAKER_OWNER_OFFSET = POOL_ID_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u64('depositBalance'),
  private long depositBalance;
  private static int DEPOSIT_BALANCE_OFFSET = STAKER_OWNER_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u128('rewardDebt')
  private long rewardDebt;
  private static int REWARD_DEBT_OFFSET = DEPOSIT_BALANCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  // u128('rewardDebtB')
  private long rewardDebtB;
  private static int REWARD_DEBT_B_OFFSET = REWARD_DEBT_OFFSET + ByteUtils.UINT_64_LENGTH * 2;

  public static UserStakeLayout readData(byte[] data) {
    UserStakeLayout userStakeLayout = new UserStakeLayout();

    data = Base64.getDecoder().decode(data);

    userStakeLayout.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());
    userStakeLayout.setPoolId(PublicKey.readPubkey(data, POOL_ID_OFFSET));
    userStakeLayout.setStakerOwner(PublicKey.readPubkey(data, STAKER_OWNER_OFFSET));
    userStakeLayout.setDepositBalance(
        ByteUtils.readUint64(data, DEPOSIT_BALANCE_OFFSET).longValue());
    userStakeLayout.setRewardDebt(ByteUtils.readUint128(data, REWARD_DEBT_OFFSET).longValue());
    userStakeLayout.setRewardDebtB(ByteUtils.readUint128(data, REWARD_DEBT_B_OFFSET).longValue());

    return userStakeLayout;
  }
}
