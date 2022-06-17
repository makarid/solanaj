package org.p2p.solanaj.programs.raydium.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.p2p.solanaj.core.PublicKey;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ToString
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"blacklist"})
public class TokenListDto {

  private final String name;

  private final Date timeStamp;

  private final JsonNode version;

  private final List<SplDetails> official;

  private final List<SplDetails> unOfficial;

  @JsonIgnore private final JsonNode unNamed;

  private final HashMap<PublicKey, SplDetails> splMap = new HashMap<>();

  public TokenListDto(
      @JsonProperty("name") String name,
      @JsonProperty("timestamp") Date timeStamp,
      @JsonProperty("version") JsonNode version,
      @JsonProperty("official") List<SplDetails> official,
      @JsonProperty("unOfficial") List<SplDetails> unOfficial,
      @JsonProperty("unNamed") JsonNode unNamed) {
    this.name = name;
    this.timeStamp = timeStamp;
    this.version = version;
    this.official = official;
    this.unOfficial = unOfficial;
    this.unNamed = unNamed;
    official.forEach(splDetails -> splMap.put(splDetails.mintAddress, splDetails));
    unOfficial.forEach(splDetails -> splMap.put(splDetails.mintAddress, splDetails));
  }

  public HashMap<PublicKey, SplDetails> getSpls() {
    return splMap;
  }

    @ToString
    @Getter
    public static class SplDetails {
      private final String symbol;

      private final String name;

      private final PublicKey mintAddress;

      private final int decimals;

      private final JsonNode extensions;

    private final String icon;

    public SplDetails(
        @JsonProperty("symbol") String symbol,
        @JsonProperty("name") String name,
        @JsonProperty("mint") PublicKey mintAddress,
        @JsonProperty("decimals") int decimals,
        @JsonProperty("extensions") JsonNode extensions,
        @JsonProperty("icon") String icon) {
        this.symbol = symbol;
        this.name = name;
        this.mintAddress = mintAddress;
        this.decimals = decimals;
        this.extensions = extensions;
      this.icon = icon;
    }
  }

  //  @ToString
  //  @Getter
  //  public static class LpDto {
  //
  //    private final HashMap<PublicKey, LpDto.LpDetails> lps = new HashMap<>();
  //
  //    @JsonAnySetter
  //    public void setLpDetails(String name, LpDto.LpDetails lpDetails) {
  //      this.lps.put(lpDetails.getMintAddress(), lpDetails);
  //    }
  //
  //    @ToString
  //    @Getter
  //    public static class LpDetails {
  //      private final String symbol;
  //
  //      private final String name;
  //
  //      private final PublicKey mintAddress;
  //
  //      private final PublicKey baseAddress;
  //
  //      private final PublicKey quoteAddress;
  //
  //      private final int decimals;
  //
  //      private final int version;
  //
  //      public LpDetails(
  //          @JsonProperty("symbol") String symbol,
  //          @JsonProperty("name") String name,
  //          @JsonProperty("mint") PublicKey mintAddress,
  //          @JsonProperty("base") PublicKey baseAddress,
  //          @JsonProperty("quote") PublicKey quoteAddress,
  //          @JsonProperty("decimals") int decimals,
  //          @JsonProperty("version") int version) {
  //        this.symbol = symbol;
  //        this.name = name;
  //        this.mintAddress = mintAddress;
  //        this.baseAddress = baseAddress;
  //        this.quoteAddress = quoteAddress;
  //        this.decimals = decimals;
  //        this.version = version;
  //      }
  //    }
  //  }
}
