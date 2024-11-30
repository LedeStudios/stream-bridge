package net.ledestudios.streambridge.net.ws.client;

import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;

public abstract class WebsocketClientHandler {

    public abstract void onOpen(ServerHandshake handshake);

    public abstract void onMessage(String message);

    public abstract void onClose(int code, String reason, boolean remote);

    public abstract void onError(Exception e);

    public abstract void sendMessage(@NotNull String message);

}
