package io.github.makarid.solanaj.ws.listeners;

import io.github.makarid.solanaj.rpc.RpcException;

public interface NotificationEventListener {
  void onNotificationEvent(Object data) throws RpcException;
}
