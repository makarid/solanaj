package org.makarid.solanaj.ws.listeners;

import org.makarid.solanaj.rpc.RpcException;

public interface NotificationEventListener {
  void onNotificationEvent(Object data) throws RpcException;
}
