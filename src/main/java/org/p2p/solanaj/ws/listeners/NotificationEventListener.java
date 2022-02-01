package org.p2p.solanaj.ws.listeners;

import org.p2p.solanaj.rpc.RpcException;

public interface NotificationEventListener {
  void onNotificationEvent(Object data) throws RpcException;
}
