package org.makarid.solanaj.rpc;

import lombok.*;
import org.makarid.solanaj.rpc.types.WeightedEndpoint;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightedCluster {

  List<WeightedEndpoint> endpoints;
}
