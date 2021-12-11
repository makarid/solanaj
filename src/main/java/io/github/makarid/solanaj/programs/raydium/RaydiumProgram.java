package io.github.makarid.solanaj.programs.raydium;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.programs.raydium.dto.FarmListDto;
import io.github.makarid.solanaj.programs.raydium.dto.LpListDto;
import io.github.makarid.solanaj.programs.raydium.layouts.FarmV4Layout;
import io.github.makarid.solanaj.programs.raydium.layouts.LiquidityLayoutV4;
import io.github.makarid.solanaj.programs.raydium.layouts.UserStakeLayout;
import io.github.makarid.solanaj.programs.raydium.dto.TokenListDto;
import io.github.makarid.solanaj.programs.serum.model.OpenOrdersAccount;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.rpc.RpcException;
import io.github.makarid.solanaj.rpc.types.AccountInfo;
import io.github.makarid.solanaj.rpc.types.ProgramAccount;
import io.github.makarid.solanaj.rpc.types.TokenResultObjects;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
public class RaydiumProgram {

  private static final RpcClient client = new RpcClient("https://solana-api.projectserum.com");
  private static final ObjectMapper mapper = new ObjectMapper();
  private TokenListDto tokenList;
  private LpListDto lpList;
  private FarmListDto farmList;

  public RaydiumProgram() {
    try {
      tokenList = initTokenList();
      farmList = initFarmList();
      lpList = initLpList();
    } catch (IOException e) {
      log.warning("Raydium Json lists have not been initialized.");
      log.warning(e.getMessage());
    }
  }

  public Map<String, BigDecimal> getStakedAmountInBothCurrencies(
      PublicKey poolMintAddress, PublicKey addressToCheck) throws RpcException, IOException {

    Map<String, BigDecimal> stakedAmountInCurrencies = new HashMap<>();
    TokenListDto.LpDto.LpDetails poolDetails = tokenList.getLp().getLps().get(poolMintAddress);

    FarmListDto.FarmInfo farmInfo =
        farmList.getOfficial().stream()
            .filter(farmInfo1 -> farmInfo1.getLpMint().equals(poolMintAddress))
            .findFirst()
            .get();
    LpListDto.LpInfo lpInfo =
        lpList.getOfficial().stream()
            .filter(lpInfo1 -> lpInfo1.getLpMint().equals(poolMintAddress))
            .findFirst()
            .get();

    AccountInfo ammIdAccount = client.getApi().getAccountInfo(lpInfo.getId());

    LiquidityLayoutV4 liquidityInfoLayoutV4 =
        LiquidityLayoutV4.readData(ammIdAccount.getValue().getData().get(0).getBytes());

    TokenResultObjects.TokenAmountInfo baseVaultAccount =
        client.getApi().getTokenAccountBalance(liquidityInfoLayoutV4.getBaseVault());
    TokenResultObjects.TokenAmountInfo quoteVaultAccount =
        client.getApi().getTokenAccountBalance(liquidityInfoLayoutV4.getQuoteVault());

    TokenResultObjects.TokenAmountInfo lpMintAccount =
        client.getApi().getTokenSupply(liquidityInfoLayoutV4.getLpMint());

    OpenOrdersAccount openOrdersAccount =
        OpenOrdersAccount.readOpenOrdersAccount(
            client
                .getApi()
                .getAccountInfo(liquidityInfoLayoutV4.getOpenOrders())
                .getValue()
                .getData()
                .get(0)
                .getBytes());

    BigDecimal poolTotalBase =
        BigDecimal.valueOf(baseVaultAccount.getUiAmount())
            .add(
                BigDecimal.valueOf(
                    openOrdersAccount.getBaseTokenTotal(), baseVaultAccount.getDecimals()))
            .subtract(
                BigDecimal.valueOf(
                    liquidityInfoLayoutV4.getBaseNeedTakePnl(), baseVaultAccount.getDecimals()));
    BigDecimal poolTotalQuote =
        BigDecimal.valueOf(quoteVaultAccount.getUiAmount())
            .add(
                BigDecimal.valueOf(
                    openOrdersAccount.getQuoteTokenTotal(), quoteVaultAccount.getDecimals()))
            .subtract(
                BigDecimal.valueOf(
                    liquidityInfoLayoutV4.getQuoteNeedTakePnl(), quoteVaultAccount.getDecimals()));

    //            log.info("PoolTotalBase: "+poolTotalBase);
    //            log.info("PoolTotalQuote: "+poolTotalQuote);
    //            log.info("Base Vault: "+ baseVaultAccount.toString());
    //            log.info("Quote Vault: "+ quoteVaultAccount.toString());
    //            log.info("LP Mint: "+ lpMintAccount.toString());

    BigDecimal baseUnit =
        poolTotalBase.divide(
            BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);
    BigDecimal quoteUnit =
        poolTotalQuote.divide(
            BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);

    BigDecimal totalLpStaked =
        getTotalStakedLp(
            farmInfo.getProgramId(), addressToCheck, farmInfo.getId(), poolDetails.getDecimals());

    stakedAmountInCurrencies.put(
        tokenList.getSpl().getSpls().get(liquidityInfoLayoutV4.getBaseMint()).getSymbol(),
        baseUnit.multiply(totalLpStaked));
    stakedAmountInCurrencies.put(
        tokenList.getSpl().getSpls().get(liquidityInfoLayoutV4.getQuoteMint()).getSymbol(),
        quoteUnit.multiply(totalLpStaked));

    return stakedAmountInCurrencies;
  }

  public BigDecimal getTotalStakedLp(
      PublicKey stakeID, PublicKey addressToCheck, PublicKey farmId, int decimals)
      throws RpcException {
    BigDecimal stakedAmount = BigDecimal.ZERO;

    // Pare ola ta accounts apo to sigkekrimeno address
    List<ProgramAccount> accounts =
        client.getApi().getProgramAccounts(stakeID, 40, addressToCheck.toString());

    for (ProgramAccount account : accounts) {
      UserStakeLayout userStakeLayout =
          UserStakeLayout.readData(account.getAccount().getData().getBytes());
      if (userStakeLayout.getPoolId().equals(farmId)) {
        stakedAmount = BigDecimal.valueOf(userStakeLayout.getDepositBalance(), decimals);
      }
    }

    return stakedAmount;
  }
  // How to get the pending reward from farms
  //   if (isFusion) {
  //    if (userInfo) {
  //      userInfo = cloneDeep(userInfo)
  //              const { rewardDebt, rewardDebtB, depositBalance } = userInfo
  //      let d = 0
  //      // @ts-ignore
  //      if (newFarmInfo.version === 5) {
  //        d = 1e15
  //      } else {
  //        d = 1e9
  //      }
  //              const pendingReward = depositBalance.wei
  //              .multipliedBy(getBigNumber(perShare))
  //              .dividedBy(d)
  //              .minus(rewardDebt.wei)
  //              const pendingRewardB = depositBalance.wei
  //              .multipliedBy(getBigNumber(perShareB))
  //              .dividedBy(d)
  //              .minus(rewardDebtB.wei)
  //      userInfo.pendingReward = new TokenAmount(pendingReward, rewardDebt.decimals)
  //      userInfo.pendingRewardB = new TokenAmount(pendingRewardB, rewardDebtB.decimals)
  //    } else {
  //      userInfo = {
  //              // @ts-ignore
  //              depositBalance: new TokenAmount(0, farmInfo.lp.decimals),
  //              // @ts-ignore
  //              pendingReward: new TokenAmount(0, farmInfo.reward.decimals),
  //              // @ts-ignore
  //              pendingRewardB: new TokenAmount(0, farmInfo.rewardB?.decimals)
  //              }
  //    }
  //  }
  //          if (!isFusion) {
  //    if (userInfo) {
  //      userInfo = cloneDeep(userInfo)
  //              const { rewardDebt, depositBalance } = userInfo
  //              const pendingReward = depositBalance.wei
  //              .multipliedBy(getBigNumber(rewardPerShareNet))
  //              .dividedBy(1e9)
  //              .minus(rewardDebt.wei)
  //      userInfo.pendingReward = new TokenAmount(pendingReward, rewardDebt.decimals)
  //    } else {
  //      userInfo = {
  //              // @ts-ignore
  //              depositBalance: new TokenAmount(0, farmInfo.lp.decimals),
  //              // @ts-ignore
  //              pendingReward: new TokenAmount(0, farmInfo.reward.decimals)
  //              }
  //    }
  //  }

  public BigDecimal getPendingRewards(
      PublicKey stakeID, PublicKey addressToCheck, PublicKey farmId, int decimals)
      throws RpcException {
    BigDecimal pendingRewards = BigDecimal.ZERO;

    // Pare ola ta accounts apo to sigkekrimeno address
    List<ProgramAccount> accounts =
        client.getApi().getProgramAccounts(stakeID, 40, addressToCheck.toString());
    FarmV4Layout farmV4Layout =
        FarmV4Layout.readFarm(
            client.getApi().getAccountInfo(farmId).getValue().getData().get(0).getBytes());

    for (ProgramAccount account : accounts) {
      UserStakeLayout userStakeLayout =
          UserStakeLayout.readData(account.getAccount().getData().getBytes());
      if (userStakeLayout.getPoolId().equals(farmId)) {
        pendingRewards =
            BigDecimal.valueOf(userStakeLayout.getDepositBalance(), decimals)
                .multiply(BigDecimal.valueOf(farmV4Layout.getPerShareRewardB()))
                .divide(BigDecimal.valueOf(1e9), MathContext.DECIMAL32)
                .subtract(BigDecimal.valueOf(userStakeLayout.getRewardDebt()));
        System.out.println(pendingRewards);
      }
    }

    return pendingRewards;
  }

  private TokenListDto initTokenList() throws IOException {
    return mapper.readValue(
        getResponse("https://sdk.raydium.io/token/raydium.mainnet.json"), TokenListDto.class);
  }

  private LpListDto initLpList() throws IOException {
    return mapper.readValue(
        getResponse("https://sdk.raydium.io/liquidity/mainnet.json"), LpListDto.class);
  }

  private FarmListDto initFarmList() throws IOException {
    return mapper.readValue(
        getResponse("https://sdk.raydium.io/farm/mainnet.json"), FarmListDto.class);
  }

  private String getResponse(String url) throws IOException {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder().url(url).build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public TokenListDto getTokenList() {
    return tokenList;
  }

  public LpListDto getLpList() {
    return lpList;
  }

  public FarmListDto getFarmList() {
    return farmList;
  }
}
