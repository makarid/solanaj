package io.github.makarid.solanaj.programs.mango.manager;

import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.programs.mango.model.MangoPerpAccount;
import io.github.makarid.solanaj.programs.mango.model.MarginAccount;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.rpc.RpcException;
import io.github.makarid.solanaj.rpc.types.AccountInfo;
import io.github.makarid.solanaj.programs.mango.model.MangoGroup;
import io.github.makarid.solanaj.programs.mango.model.MangoPerpGroup;
import lombok.RequiredArgsConstructor;

import java.util.Base64;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class MangoManager {

  private final RpcClient client;
  private static final Logger LOGGER = Logger.getLogger(MangoManager.class.getName());
  private static final PublicKey BTC_ETH_SOL_SRM_USDC_MANGO_GROUP =
      new PublicKey("2oogpTYm1sp6LPZAWD3bp2wsFpnV2kXL1s52yyFhW5vp");

  public MangoGroup getDefaultMangoGroup() {
    return getMangoGroup(BTC_ETH_SOL_SRM_USDC_MANGO_GROUP);
  }

  public MangoGroup getMangoGroup(final PublicKey publicKey) {
    byte[] mangoGroupData = getAccountData(publicKey);
    return MangoGroup.readMangoGroup(mangoGroupData);
  }

  public MarginAccount getMarginAccount(final PublicKey publicKey, final PublicKey dexProgramId) {
    // Decode Margin Account
    byte[] marginAccountData = getAccountData(publicKey);
    final MarginAccount marginAccount =
        MarginAccount.readMarginAccount(publicKey, marginAccountData);

    // Populate marginAccount with Open Orders
    marginAccount.loadOpenOrders(dexProgramId);

    return marginAccount;
  }

  private byte[] getAccountData(final PublicKey publicKey) {
    AccountInfo accountInfo = null;

    try {
      accountInfo = client.getApi().getAccountInfo(publicKey);
    } catch (RpcException e) {
      LOGGER.warning(e.getMessage());
    }

    if (accountInfo == null) {
      return null;
    }

    return Base64.getDecoder().decode(accountInfo.getValue().getData().get(0));
  }

  public MangoPerpGroup getMangoPerpGroup(final PublicKey publicKey) {
    byte[] mangoPerpGroupData = getAccountData(publicKey);
    return MangoPerpGroup.readMangoPerpGroup(publicKey, mangoPerpGroupData);
  }

  public MangoPerpAccount getMangoPerpAccount(final PublicKey publicKey) {
    byte[] mangoPerpAccountData = getAccountData(publicKey);
    return MangoPerpAccount.readMangoPerpAccount(publicKey, mangoPerpAccountData);
  }
}
