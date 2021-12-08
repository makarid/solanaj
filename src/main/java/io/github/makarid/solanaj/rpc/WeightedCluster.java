package io.github.makarid.solanaj.rpc;

import io.github.makarid.solanaj.rpc.types.WeightedEndpoint;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightedCluster {

  List<WeightedEndpoint> endpoints;
}
