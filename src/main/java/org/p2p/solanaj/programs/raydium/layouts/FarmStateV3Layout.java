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
public class FarmStateV3Layout {

  private long state;

  private long nonce;

  private PublicKey lpVault;

  private PublicKey rewardVaultA;

  private long totalRewardA;

  private long perShareRewardA;

  private long perSlotRewardA;

  private long option;

  private PublicKey rewardVaultB;

  private String blob;

  private long totalRewardB;

  private long perShareRewardB;

  private long perSlotRewardB;

  private long lastSlot;

  private PublicKey publicKey;

  //    u64("state"),
  private static final int STATE_OFFSET = 0;
  //    u64("nonce"),
  private static final int NONCE_OFFSET = STATE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey("lpVault"),
  private static final int LP_VAULT_OFFSET = NONCE_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey("rewardVaultA"),
  private static final int REWARD_VAULT_A_OFFSET = LP_VAULT_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u64("totalRewardA"),
  private static final int TOTAL_REWARD_A_OFFSET =
      REWARD_VAULT_A_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u128("perShareRewardA"),
  private static final int PER_SHARE_REWARDA_OFFSET =
      TOTAL_REWARD_A_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("perSlotRewardA"),
  private static final int PER_SLOT_REWARDA_OFFSET =
      PER_SHARE_REWARDA_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u8("option"),
  private static final int OPTION_OFFSET = PER_SLOT_REWARDA_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey("rewardVaultB"),
  private static final int REWARD_VAULTB_OFFSET = OPTION_OFFSET + 1;
  //    blob(7),
  private static final int BLOB_OFFSET = REWARD_VAULTB_OFFSET + PublicKey.PUBLIC_KEY_LENGTH;
  //    u64("totalRewardB"),
  private static final int TOTAL_REWARDB_OFFSET = BLOB_OFFSET + 7;
  //    u128("perShareRewardB"),
  private static final int PER_SHARE_REWARDB_OFFSET =
      TOTAL_REWARDB_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    u64("perSlotRewardB"),
  private static final int PER_SLOT_REWARDB_OFFSET =
      PER_SHARE_REWARDB_OFFSET + ByteUtils.UINT_64_LENGTH * 2;
  //    u64("lastSlot"),
  private static final int LAST_SLOT_OFFSET = PER_SLOT_REWARDB_OFFSET + ByteUtils.UINT_64_LENGTH;
  //    publicKey(),
  private static final int PUBKEY_OFFSET = LAST_SLOT_OFFSET + ByteUtils.UINT_64_LENGTH;

  public FarmStateV3Layout() {}

  public static FarmStateV3Layout readFarm(byte[] data) {
    FarmStateV3Layout farmStateV3Layout = new FarmStateV3Layout();

    data = Base64.getDecoder().decode(data);

    farmStateV3Layout.setState(ByteUtils.readUint64(data, STATE_OFFSET).longValue());
    farmStateV3Layout.setNonce(ByteUtils.readUint64(data, NONCE_OFFSET).longValue());
    farmStateV3Layout.setLpVault(PublicKey.readPubkey(data, LP_VAULT_OFFSET));
    farmStateV3Layout.setRewardVaultA(PublicKey.readPubkey(data, REWARD_VAULT_A_OFFSET));
    farmStateV3Layout.setTotalRewardA(
        ByteUtils.readUint64(data, TOTAL_REWARD_A_OFFSET).longValue());
    farmStateV3Layout.setPerShareRewardA(
        ByteUtils.readUint128(data, PER_SHARE_REWARDA_OFFSET).longValue());
    farmStateV3Layout.setPerSlotRewardA(
        ByteUtils.readUint64(data, PER_SLOT_REWARDA_OFFSET).longValue());
    farmStateV3Layout.setOption(ByteUtils.getBit(data, OPTION_OFFSET));
    farmStateV3Layout.setRewardVaultB(PublicKey.readPubkey(data, REWARD_VAULTB_OFFSET));
    farmStateV3Layout.setTotalRewardB(ByteUtils.readUint64(data, TOTAL_REWARDB_OFFSET).longValue());
    farmStateV3Layout.setPerShareRewardB(
        ByteUtils.readUint128(data, PER_SHARE_REWARDB_OFFSET).longValue());
    farmStateV3Layout.setLastSlot(ByteUtils.readUint64(data, LAST_SLOT_OFFSET).longValue());
    farmStateV3Layout.setPublicKey(PublicKey.readPubkey(data, PUBKEY_OFFSET));

    return farmStateV3Layout;
  }
}
