package io.github.makarid.solanaj.programs.serum.model;

/** Enum representing if your Serum {@link Order} is a buy or sell */
public enum SideLayout {
  BUY(0),
  SELL(1);

  private final int value;

  SideLayout(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
