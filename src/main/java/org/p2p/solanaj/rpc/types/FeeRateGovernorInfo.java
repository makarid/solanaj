package org.p2p.solanaj.rpc.types;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeeRateGovernorInfo extends RpcResultObject {
  @Getter
  @ToString
  public static class FeeRateGovernor {
    @Json(name = "burnPercent")
    private int burnPercent;

    @Json(name = "maxLamportsPerSignature")
    private long maxLamportsPerSignature;

    @Json(name = "minLamportsPerSignature")
    private long minLamportsPerSignature;

    @Json(name = "targetLamportsPerSignature")
    private long targetLamportsPerSignature;

    @Json(name = "targetSignaturesPerSlot")
    private long targetSignaturesPerSlot;
  }

  @Getter
  @ToString
  public static class Value {

    @Json(name = "feeRateGovernor")
    private FeeRateGovernor feeRateGovernor;
  }

  @Json(name = "value")
  private Value value;
}
