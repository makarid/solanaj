package io.github.makarid.solanaj.rpc;

public class RpcException extends Exception {
  private static final long serialVersionUID = 8315999767009642193L;

  public RpcException(String message) {
    super(message);
  }
}
