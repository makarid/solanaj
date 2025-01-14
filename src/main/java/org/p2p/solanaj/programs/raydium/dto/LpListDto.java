package org.p2p.solanaj.programs.raydium.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.p2p.solanaj.core.PublicKey;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ToString
@Getter
public class LpListDto {

  private final String name;

  private final Date timestamp;

  private final JsonNode version;

  private final List<LpInfo> official;

  private final List<LpInfo> unOfficial;

  private final HashMap<PublicKey, LpInfo> lpsMap = new HashMap<>();

  public LpListDto(
      @JsonProperty("name") String name,
      @JsonProperty("timestamp") Date timestamp,
      @JsonProperty("version") JsonNode version,
      @JsonProperty("official") List<LpInfo> official,
      @JsonProperty("unOfficial") List<LpInfo> unOfficial) {
    this.name = name;
    this.timestamp = timestamp;
    this.version = version;
    this.official = official;
    this.unOfficial = unOfficial;
    official.forEach(lpInfo -> lpsMap.put(lpInfo.lpMint, lpInfo));
    unOfficial.forEach(lpInfo -> lpsMap.put(lpInfo.lpMint, lpInfo));
  }

  public HashMap<PublicKey, LpInfo> getLps() {
    return lpsMap;
  }

  @ToString
  @Getter
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(value = {"modelDataAccount"})
  public static class LpInfo {
    private final PublicKey id;

    private final PublicKey baseMint;

    private final PublicKey quoteMint;

    private final PublicKey lpMint;

    private final int version;

    private final PublicKey programId;

    private final PublicKey authority;

    private final PublicKey openOrders;

    private final PublicKey targetOrders;

    private final PublicKey baseVault;

    private final PublicKey quoteVault;

    private final PublicKey withdrawQueue;

    private final PublicKey lpVault;

    private final int marketVersion;

    private final PublicKey marketProgramId;

    private final PublicKey marketId;

    private final PublicKey marketAuthority;

    private final PublicKey marketVaultSigner;

    private final PublicKey marketBaseVault;

    private final PublicKey marketQuoteVault;

    private final PublicKey marketBids;

    private final PublicKey marketAsks;

    private final PublicKey marketEventQueue;

    private final int baseDecimals;

    private final int quoteDecimals;

    private final int lpDecimals;

    public LpInfo(
        @JsonProperty("id") PublicKey id,
        @JsonProperty("baseMint") PublicKey baseMint,
        @JsonProperty("quoteMint") PublicKey quoteMint,
        @JsonProperty("lpMint") PublicKey lpMint,
        @JsonProperty("version") int version,
        @JsonProperty("programId") PublicKey programId,
        @JsonProperty("authority") PublicKey authority,
        @JsonProperty("openOrders") PublicKey openOrders,
        @JsonProperty("targetOrders") PublicKey targetOrders,
        @JsonProperty("baseVault") PublicKey baseVault,
        @JsonProperty("quoteVault") PublicKey quoteVault,
        @JsonProperty("withdrawQueue") PublicKey withdrawQueue,
        @JsonProperty("tempLpVault") PublicKey lpVault,
        @JsonProperty("marketVersion") int marketVersion,
        @JsonProperty("marketProgramId") PublicKey marketProgramId,
        @JsonProperty("marketId") PublicKey marketId,
        @JsonProperty("marketAuthority") PublicKey marketAuthority,
        @JsonProperty("marketVaultSigner") PublicKey marketVaultSigner,
        @JsonProperty("marketBaseVault") PublicKey marketBaseVault,
        @JsonProperty("marketQuoteVault") PublicKey marketQuoteVault,
        @JsonProperty("marketBids") PublicKey marketBids,
        @JsonProperty("marketAsks") PublicKey marketAsks,
        @JsonProperty("marketEventQueue") PublicKey marketEventQueue,
        @JsonProperty("baseDecimals") int baseDecimals,
        @JsonProperty("quoteDecimals") int quoteDecimals,
        @JsonProperty("lpDecimals") int lpDecimals) {
      this.id = id;
      this.baseMint = baseMint;
      this.quoteMint = quoteMint;
      this.lpMint = lpMint;
      this.version = version;
      this.programId = programId;
      this.authority = authority;
      this.openOrders = openOrders;
      this.targetOrders = targetOrders;
      this.baseVault = baseVault;
      this.quoteVault = quoteVault;
      this.withdrawQueue = withdrawQueue;
      this.lpVault = lpVault;
      this.marketVersion = marketVersion;
      this.marketProgramId = marketProgramId;
      this.marketId = marketId;
      this.marketAuthority = marketAuthority;
      this.marketVaultSigner = marketVaultSigner;
      this.marketBaseVault = marketBaseVault;
      this.marketQuoteVault = marketQuoteVault;
      this.marketBids = marketBids;
      this.marketAsks = marketAsks;
      this.marketEventQueue = marketEventQueue;
      this.baseDecimals = baseDecimals;
      this.quoteDecimals = quoteDecimals;
      this.lpDecimals = lpDecimals;
    }
  }
}
