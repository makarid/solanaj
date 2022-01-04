package io.github.makarid.solanaj.programs.raydium;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.programs.raydium.dto.FarmListDto;
import io.github.makarid.solanaj.programs.raydium.dto.LpListDto;
import io.github.makarid.solanaj.programs.raydium.layouts.LiquidityLayoutV4;
import io.github.makarid.solanaj.programs.raydium.layouts.StakeInfoLayout;
import io.github.makarid.solanaj.programs.raydium.layouts.StakeInfoLayoutV4;
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
import java.util.*;

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

    Optional<FarmListDto.FarmInfo> farmInfo =
        farmList.getOfficial().stream()
            .filter(farmInfo1 -> farmInfo1.getLpMint().equals(poolMintAddress))
            .findFirst();

    Optional<LpListDto.LpInfo> lpInfo =
        lpList.getOfficial().stream()
            .filter(lpInfo1 -> lpInfo1.getLpMint().equals(poolMintAddress))
            .findFirst();

    if (farmInfo.isPresent() && lpInfo.isPresent()) {
      AccountInfo ammIdAccount = client.getApi().getAccountInfo(lpInfo.get().getId());

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
                      liquidityInfoLayoutV4.getQuoteNeedTakePnl(),
                      quoteVaultAccount.getDecimals()));

      BigDecimal baseUnit =
          poolTotalBase.divide(
              BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);

      BigDecimal quoteUnit =
          poolTotalQuote.divide(
              BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);

      BigDecimal totalLpStaked =
          getTotalStakedLp(
              farmInfo.get().getProgramId(),
              addressToCheck,
              farmInfo.get().getId(),
              poolDetails.getDecimals());

      stakedAmountInCurrencies.put(
          adaptRaydiumCurrencies(
              tokenList.getSpl().getSpls().get(liquidityInfoLayoutV4.getBaseMint()).getSymbol()),
          baseUnit.multiply(totalLpStaked));
      stakedAmountInCurrencies.put(
          adaptRaydiumCurrencies(
              tokenList.getSpl().getSpls().get(liquidityInfoLayoutV4.getQuoteMint()).getSymbol()),
          quoteUnit.multiply(totalLpStaked));

      return stakedAmountInCurrencies;
    } else {
      throw new IOException("FarmInfo or LpInfo is not present.");
    }
  }

  public String adaptRaydiumCurrencies(String raydiumCurrency) {
    switch (raydiumCurrency) {
      case "WSOL":
        return "SOL";
      case "weSUSHI":
        return "SUSHI";
      default:
        return raydiumCurrency;
    }
  }

  public BigDecimal getTotalStakedLp(
      PublicKey stakeId, PublicKey addressToCheck, PublicKey farmId, int decimals)
      throws RpcException {
    BigDecimal stakedAmount = BigDecimal.ZERO;

    // Pare ola ta accounts apo to sigkekrimeno address
    List<ProgramAccount> accounts =
        client.getApi().getProgramAccounts(stakeId, 40, addressToCheck.toString());

    for (ProgramAccount account : accounts) {
      UserStakeLayout userStakeLayout =
          UserStakeLayout.readData(account.getAccount().getData().getBytes());
      if (userStakeLayout.getPoolId().equals(farmId)) {
        stakedAmount = BigDecimal.valueOf(userStakeLayout.getDepositBalance(), decimals);
      }
    }

    return stakedAmount;
  }

  /*
   reference implementation: https://github.com/raydium-io/raydium-ui/blob/master/src/pages/farms.vue#L542
   Comments:
   if perSlotRewardA on Farm_Stake_Layout is NOT 0 then it is a double reward currency
   rewardVaultA  and rewardVaultB are spl token accounts. if perSlotRewardA  is not 0,
   means the  corresponding rewardVaultA (spl token account)'s mint(currency) will reward to the farmer.
   so do perSlotRewardB.
  */

  public HashMap<String, BigDecimal> getPendingRewards(
      PublicKey stakeID, PublicKey addressToCheck, PublicKey farmId)
      throws RpcException, IOException {
    HashMap<String, BigDecimal> rewardsMap = new HashMap<>();

    BigDecimal pendingRewards;

    // Pare ola ta accounts apo to sigkekrimeno address
    List<ProgramAccount> accounts =
        client.getApi().getProgramAccounts(stakeID, 40, addressToCheck.toString());

    FarmListDto.FarmInfo farmInfo = farmList.getFarmInfoByFarmId(farmId);
    int version = farmInfo.getVersion();
    TokenListDto.SplDto.SplDetails rewardSplDetails =
        tokenList.getSpl().getSpls().get(farmInfo.getRewardMints().get(0));

    BigDecimal d = (version == 5) ? BigDecimal.valueOf(1e15) : BigDecimal.valueOf(1e9);

    for (ProgramAccount account : accounts) {
      UserStakeLayout userStakeLayout =
          UserStakeLayout.readData(account.getAccount().getData().getBytes());

      if (userStakeLayout.getPoolId().equals(farmId)) {

        BigDecimal deposit = BigDecimal.valueOf(userStakeLayout.getDepositBalance());
        BigDecimal rewardDebt = BigDecimal.valueOf(userStakeLayout.getRewardDebt());
        BigDecimal rewardDebtB = BigDecimal.valueOf(userStakeLayout.getRewardDebtB());

        if (version == 5) {
          StakeInfoLayoutV4 stakeInfoLayout4 =
              StakeInfoLayoutV4.readData(
                  client.getApi().getAccountInfo(farmId).getValue().getData().get(0).getBytes());

          BigDecimal pendingRewardsB;
          BigDecimal perShare = BigDecimal.valueOf(stakeInfoLayout4.getPerShare());
          BigDecimal perShareB = BigDecimal.valueOf(stakeInfoLayout4.getPerShareB());

          TokenListDto.SplDto.SplDetails rewardBSplDetails =
              tokenList.getSpl().getSpls().get(farmInfo.getRewardMints().get(1));

          pendingRewards =
              deposit.multiply(perShare).divide(d, MathContext.DECIMAL32).subtract(rewardDebt);

          pendingRewardsB =
              deposit.multiply(perShareB).divide(d, MathContext.DECIMAL32).subtract(rewardDebtB);

          if (rewardDebt.compareTo(BigDecimal.ZERO) != 0
              && rewardDebtB.compareTo(BigDecimal.ZERO) != 0) {
            rewardsMap.put(
                rewardSplDetails.getSymbol(),
                BigDecimal.valueOf(pendingRewards.longValue(), rewardSplDetails.getDecimals()));
            rewardsMap.put(
                rewardBSplDetails.getSymbol(),
                BigDecimal.valueOf(pendingRewardsB.longValue(), rewardBSplDetails.getDecimals()));
          } else {
            if (rewardDebt.compareTo(BigDecimal.ZERO) == 0) {
              rewardsMap.put(
                  rewardBSplDetails.getSymbol(),
                  BigDecimal.valueOf(pendingRewardsB.longValue(), rewardBSplDetails.getDecimals()));
            } else {
              rewardsMap.put(
                  rewardSplDetails.getSymbol(),
                  BigDecimal.valueOf(pendingRewards.longValue(), rewardSplDetails.getDecimals()));
            }
          }

        } else {
          StakeInfoLayout stakeInfoLayout =
              StakeInfoLayout.readData(
                  client.getApi().getAccountInfo(farmId).getValue().getData().get(0).getBytes());

          BigDecimal perShareNet = BigDecimal.valueOf(stakeInfoLayout.getRewardPerShareNet());

          pendingRewards =
              deposit.multiply(perShareNet).divide(d, MathContext.DECIMAL32).subtract(rewardDebt);

          rewardsMap.put(
              rewardSplDetails.getSymbol(),
              BigDecimal.valueOf(pendingRewards.longValue(), rewardSplDetails.getDecimals()));
        }
        return rewardsMap;
      }
    }

    throw new IOException("An error occurred because no rewards found for pool.");
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
      return Objects.requireNonNull(response.body()).string();
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
