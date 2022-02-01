package org.p2p.solanaj.rpc;

import org.p2p.solanaj.rpc.types.WeightedEndpoint;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightedCluster {

  List<WeightedEndpoint> endpoints;
}
