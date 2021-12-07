package org.makarid.solanaj.rpc.types.config;

import java.util.List;

import org.makarid.solanaj.rpc.types.config.RpcSendTransactionConfig.Encoding;

public class ProgramAccountConfig {

  private Encoding encoding = Encoding.base64;

  private List<Object> filters = null;

  private String commitment = "processed";

  public ProgramAccountConfig(List<Object> filters) {
    this.filters = filters;
  }

  public ProgramAccountConfig(Encoding encoding) {
    this.encoding = encoding;
  }
}
