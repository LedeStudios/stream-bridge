package net.ledestudios.streambridge.stream.chzzk.chat;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import net.ledestudios.streambridge.net.ws.client.WebsocketClientHandler;
import net.ledestudios.streambridge.net.ws.health.ChzzkChatHealthChecker;
import net.ledestudios.streambridge.net.ws.health.HealthChecker;
import net.ledestudios.streambridge.stream.chzzk.ChzzkChannelManager;
import net.ledestudios.streambridge.stream.chzzk.type.user.ChzzkUser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ChzzkChatClient extends WebSocketClient {

    @Getter
    private final @NotNull ChzzkChannelManager channel;

    @Getter
    private final @NotNull Set<ChzzkChatListener> listeners;

    @Getter
    private final @Nullable ChzzkUser currentUser;

    @Setter
    private @Nullable WebsocketClientHandler websocketClientHandler;

    @Getter
    @Setter
    private @Nullable String sid;

    @Getter
    private final @NotNull String cid;

    @Getter
    private final @NotNull String accTkn;

    @Getter
    private final @NotNull HealthChecker healthChecker;

    public static @NotNull ChzzkChatClient create(@NotNull ChzzkChannelManager channel) {
        return new ChzzkChatClient(channel);
    }

    private ChzzkChatClient(@NotNull ChzzkChannelManager channel) {
        super(URI.create(channel.getChatWebsocketUrl()));
        this.channel = channel;
        this.listeners = Sets.newConcurrentHashSet();
        this.healthChecker = new ChzzkChatHealthChecker(this);

        if (!channel.getChzzk().isAnonymous()) {
            this.currentUser = channel.getChzzk().getUserManager().getCurrentUser();
        } else {
            this.currentUser = null;
        }

        this.cid = channel.getLiveStatus().getChatChannelId();
        this.accTkn = channel.getChatAccessToken(cid).getAccessToken();
    }

    public @NotNull ChzzkChatClient addListener(@NotNull ChzzkChatListener... listeners) {
        this.listeners.addAll(Sets.newHashSet(listeners));
        return this;
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

    public void sendMessage(@NotNull String message) {
        if (websocketClientHandler != null) {
            websocketClientHandler.sendMessage(message);
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
