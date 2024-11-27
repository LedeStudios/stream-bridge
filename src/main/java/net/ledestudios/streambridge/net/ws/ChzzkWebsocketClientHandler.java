package net.ledestudios.streambridge.net.ws;

import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;

public class ChzzkWebsocketClientHandler extends WebsocketClientHandler {

    private final @NotNull ChzzkChatClient chat;

    public ChzzkWebsocketClientHandler(@NotNull ChzzkChatClient chat) {
        this.chat = chat;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception e) {

    }

}
