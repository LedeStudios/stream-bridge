package net.ledestudios.streambridge.net.ws;

import org.java_websocket.handshake.ServerHandshake;

public abstract class WebsocketClientHandler {

    public abstract void onOpen(ServerHandshake handshake);

    public abstract void onMessage(String message);

    public abstract void onClose(int code, String reason, boolean remote);

    public abstract void onError(Exception e);

}
