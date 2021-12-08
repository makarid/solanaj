package io.github.makarid.solanaj.programs.raydium;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.layouts.LiquidityLayoutV4;
import io.github.makarid.solanaj.layouts.UserStakeLayout;
import io.github.makarid.solanaj.programs.raydium.dto.TokenListDto;
import io.github.makarid.solanaj.programs.serum.model.OpenOrdersAccount;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.rpc.RpcException;
import io.github.makarid.solanaj.rpc.types.AccountInfo;
import io.github.makarid.solanaj.rpc.types.ProgramAccount;
import io.github.makarid.solanaj.rpc.types.TokenResultObjects;
import lombok.extern.java.Log;
import okhttp3.Call;
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

  public static Map<String, BigDecimal> getStakedAmountInBothCurrencies(
      PublicKey ammIdAddress,
      PublicKey stakedId,
      PublicKey addressToCheck,
      PublicKey farmId,
      TokenListDto tokenListDto)
      throws RpcException, IOException {

    Map<String, BigDecimal> stakedAmountInCurrencies = new HashMap<>();

    AccountInfo ammIdAccount = client.getApi().getAccountInfo(ammIdAddress);

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

    //        log.info("PoolTotalBase: "+poolTotalBase);
    //        log.info("PoolTotalQuote: "+poolTotalQuote);
    //        log.info("Base Vault: "+ baseVaultAccount.toString());
    //        log.info("Quote Vault: "+ quoteVaultAccount.toString());
    //        log.info("LP Mint: "+ lpMintAccount.toString());

    BigDecimal baseUnit =
        poolTotalBase.divide(
            BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);
    BigDecimal quoteUnit =
        poolTotalQuote.divide(
            BigDecimal.valueOf(lpMintAccount.getUiAmount()), MathContext.DECIMAL32);
    //        log.info("BaseUnit: "+ baseUnit);
    //        log.info("QuoteUnit: "+ quoteUnit);
    BigDecimal totalLpStaked =
        getTotalStakedLp(
            stakedId,
            addressToCheck,
            farmId,
            tokenListDto.getLp().getLps().get(liquidityInfoLayoutV4.getLpMint()).getDecimals());

    stakedAmountInCurrencies.put(
        tokenListDto.getSpl().getSpls().get(liquidityInfoLayoutV4.getBaseMint()).getSymbol(),
        baseUnit.multiply(totalLpStaked));
    stakedAmountInCurrencies.put(
        tokenListDto.getSpl().getSpls().get(liquidityInfoLayoutV4.getQuoteMint()).getSymbol(),
        quoteUnit.multiply(totalLpStaked));

    return stakedAmountInCurrencies;
  }

  private static BigDecimal getTotalStakedLp(
      PublicKey stakeID, PublicKey addressToCheck, PublicKey farmId, int decimals)
      throws RpcException {
    BigDecimal stakedAmount = null;

    // Pare ola ta accounts apo to sigkekrimeno address
    List<ProgramAccount> accounts =
        client.getApi().getProgramAccounts(stakeID, 40, addressToCheck.toString());

    for (ProgramAccount account : accounts) {
      UserStakeLayout userStakeLayout =
          UserStakeLayout.readData(account.getAccount().getData().getBytes());
      if (userStakeLayout.getPoolId().equals(farmId)) {
        //                System.out.println(userStakeLayout.toString());
        stakedAmount = BigDecimal.valueOf(userStakeLayout.getDepositBalance(), decimals);
      }
    }

    return stakedAmount;
  }

  public static TokenListDto getTokenLists() throws IOException {
    OkHttpClient client = new OkHttpClient();

    Request request =
        new Request.Builder().url("https://sdk.raydium.io/token/raydium.mainnet.json").build();

    Call call = client.newCall(request);
    Response response = call.execute();

    return mapper.readValue(response.body().string(), TokenListDto.class);
  }
}
