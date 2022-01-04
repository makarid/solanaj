package io.github.makarid.solanaj.programs.serum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import io.github.makarid.solanaj.core.PublicKey;

/** Class that represents a Serum order. */
@Builder
@Getter
@Setter
public class Order {

  private long price;
  private long quantity;
  private long clientOrderId;
  private float floatPrice;
  private float floatQuantity;
  private PublicKey owner;

  // used in newOrderv3. no constructor, only setters/getters
  private long maxQuoteQuantity;
  private long clientId;
  private OrderTypeLayout orderTypeLayout;
  private SelfTradeBehaviorLayout selfTradeBehaviorLayout;
  private boolean buy;

  @Override
  public String toString() {
    return "Order{"
        + "price="
        + price
        + ", quantity="
        + quantity
        + ", clientOrderId="
        + clientOrderId
        + ", floatPrice="
        + floatPrice
        + ", floatQuantity="
        + floatQuantity
        + ", owner="
        + owner
        + ", maxQuoteQuantity="
        + maxQuoteQuantity
        + ", clientId="
        + clientId
        + ", orderTypeLayout="
        + orderTypeLayout
        + ", selfTradeBehaviorLayout="
        + selfTradeBehaviorLayout
        + ", buy="
        + buy
        + '}';
  }
}