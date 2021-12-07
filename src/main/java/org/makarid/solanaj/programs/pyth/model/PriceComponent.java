package org.makarid.solanaj.programs.pyth.model;

import lombok.*;
import org.makarid.solanaj.core.PublicKey;

@Builder
@Getter
@Setter
@ToString
public class PriceComponent {

  private PublicKey publisher;
  private PriceInfo aggregate;
  private PriceInfo latest;
}
