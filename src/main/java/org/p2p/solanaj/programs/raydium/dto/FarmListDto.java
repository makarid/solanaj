package org.p2p.solanaj.programs.raydium.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.p2p.solanaj.core.PublicKey;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ToString
@Getter
public class FarmListDto {

  private final String name;

  private final Date timestamp;

  private final JsonNode version;

  private final List<FarmInfo> official;

  public FarmListDto(
      @JsonProperty("name") String name,
      @JsonProperty("timestamp") Date timestamp,
      @JsonProperty("version") JsonNode version,
      @JsonProperty("official") List<FarmInfo> official) {
    this.name = name;
    this.timestamp = timestamp;
    this.version = version;
    this.official = official;
  }

  public FarmInfo getFarmInfoByFarmId(PublicKey farmId) throws IOException {
    Optional<FarmInfo> farm =
        official.stream().filter(farmInfo -> farmInfo.getId().equals(farmId)).findFirst();

    if (farm.isPresent()) {
      return farm.get();
    } else {
      throw new IOException("FarmId doesn't exists in farm.json list.");
    }
  }

  @ToString
  @Getter
  public static class FarmInfo {
    private final PublicKey id;

    private final PublicKey lpMint;

    private final List<PublicKey> rewardMints;

    private final int version;

    private final PublicKey programId;

    private final PublicKey authority;

    private final PublicKey lpVault;

    private final List<PublicKey> rewardVaults;

    private final boolean upcoming;

    public FarmInfo(
        @JsonProperty("id") PublicKey id,
        @JsonProperty("lpMint") PublicKey lpMint,
        @JsonProperty("rewardMints") List<PublicKey> rewardMints,
        @JsonProperty("version") int version,
        @JsonProperty("programId") PublicKey programId,
        @JsonProperty("authority") PublicKey authority,
        @JsonProperty("lpVault") PublicKey lpVault,
        @JsonProperty("rewardVaults") List<PublicKey> rewardVaults,
        @JsonProperty("upcoming") boolean upcoming) {
      this.id = id;
      this.lpMint = lpMint;
      this.rewardMints = rewardMints;
      this.version = version;
      this.programId = programId;
      this.authority = authority;
      this.lpVault = lpVault;
      this.rewardVaults = rewardVaults;
      this.upcoming = upcoming;
    }
  }
}
