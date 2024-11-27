package net.ledestudios.streambridge.stream.chzzk.chat;

import lombok.Getter;
import lombok.Setter;
import net.ledestudios.streambridge.net.ws.WebsocketClientHandler;
import net.ledestudios.streambridge.stream.chzzk.ChzzkChannel;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class ChzzkChatClient extends WebSocketClient {

    @Getter
    private final @NotNull ChzzkChannel channel;

    @Setter
    private @Nullable WebsocketClientHandler websocketClientHandler;

    public static @NotNull ChzzkChatClient open(@NotNull ChzzkChannel channel) {
        return new ChzzkChatClient(channel).open();
    }

    private ChzzkChatClient(@NotNull ChzzkChannel channel) {
        super(URI.create(channel.getChatWebsocketUrl()));
        this.channel = channel;
    }

    public @NotNull CompletableFuture<ChzzkChatClient> openAsync() {
        return CompletableFuture.supplyAsync(this::open);
    }

    public @NotNull ChzzkChatClient open() {
        try {
            connectBlocking();
            return this;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull CompletableFuture<Void> closeAsync() {
        return CompletableFuture.runAsync(this::close);
    }

    public void close() {
        try {
            closeBlocking();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        if (websocketClientHandler != null) {
            websocketClientHandler.onOpen(serverHandshake);
        }
    }

    @Override
    public void onMessage(String message) {
        if (websocketClientHandler != null) {
            websocketClientHandler.onMessage(message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (websocketClientHandler != null) {
            websocketClientHandler.onClose(code, reason, remote);
        }
    }

    @Override
    public void onError(Exception e) {
        if (websocketClientHandler != null) {
            websocketClientHandler.onError(e);
        }
    }

}
