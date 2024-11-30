package net.ledestudios.streambridge.stream.chzzk;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Getter;
import net.ledestudios.streambridge.net.http.ChzzkHttpService;
import net.ledestudios.streambridge.net.ws.client.ChzzkWebsocketClientHandler;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.type.channel.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChzzkChannelManager {

    private final Gson gson = new Gson();

    private final @NotNull ChzzkHttpService http;

    @Getter
    private final @NotNull String channelId;

    @Getter
    private final @NotNull Chzzk chzzk;

    ChzzkChannelManager(@NotNull Chzzk chzzk, @NotNull String channel) {
        this.http = new ChzzkHttpService(chzzk);
        this.chzzk = chzzk;
        this.channelId = channel;
    }

    public @NotNull CompletableFuture<ChzzkLiveDetail> getLiveDetailAsync() {
        return CompletableFuture.supplyAsync(this::getLiveDetail);
    }

    public @NotNull ChzzkLiveDetail getLiveDetail() {
        String url = String.format("https://api.chzzk.naver.com/service/v2/channels/%s/live-detail", channelId);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveDetail.class);
    }

    public @NotNull CompletableFuture<ChzzkLiveStatus> getLiveStatusAsync() {
        return CompletableFuture.supplyAsync(this::getLiveStatus);
    }

    public @NotNull ChzzkLiveStatus getLiveStatus() {
        String url = String.format("https://api.chzzk.naver.com/polling/v2/channels/%s/live-status", channelId);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveStatus.class);
    }

    public @NotNull CompletableFuture<ChzzkChatAccessToken> getChatAccessTokenAsync() {
        return CompletableFuture.supplyAsync(this::getChatAccessToken);
    }

    public @NotNull ChzzkChatAccessToken getChatAccessToken() {
        String chatId = getLiveStatus().getChatChannelId();
        return getChatAccessToken(chatId);
    }

    public @NotNull CompletableFuture<ChzzkChatAccessToken> getChatAccessTokenAsync(@NotNull String chatId) {
        return CompletableFuture.supplyAsync(() -> getChatAccessToken(chatId));
    }

    public @NotNull ChzzkChatAccessToken getChatAccessToken(@NotNull String chatId) {
        String url = String.format(
                "https://comm-api.game.naver.com/nng_main/v1/chats/access-token?channelId=%s&chatType=STREAMING", chatId);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkChatAccessToken.class);
    }

    public @NotNull String getChatWebsocketUrl() {
        int serverId = 0;
        for (char i : channelId.toCharArray()) {
            serverId += Character.getNumericValue(i);
        }
        serverId = Math.abs(serverId) % 9 + 1;
        return String.format("wss://kr-ss%d.chat.naver.com/chat", serverId);
    }

    public @NotNull CompletableFuture<Integer> getFollowerCountAsync() {
        return CompletableFuture.supplyAsync(this::getFollowerCount);
    }

    public int getFollowerCount() {
        return getFollowerHeader().getTotalCount();
    }

    public @NotNull CompletableFuture<List<ChzzkFollower>> getFollowersAsync() {
        return CompletableFuture.supplyAsync(this::getFollowers);
    }

    public @NotNull List<ChzzkFollower> getFollowers() {
        ChzzkFollowerHeader header = getFollowerHeader();
        List<ChzzkFollower> followers = Lists.newArrayListWithCapacity(header.getTotalCount());
        for (int page = 0; page < header.getTotalPages(); page++) {
            ChzzkFollowerPart part = getFollowerPart(page);
            followers.addAll(part.getData());
        }
        return followers;
    }

    private @NotNull ChzzkFollowerHeader getFollowerHeader() {
        String url = String.format(
                "https://api.chzzk.naver.com/manage/v1/channels/%s/followers?page=0&size=%d",
                channelId, ChzzkFollowerHeader.SIZE_PER_PAGE);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkFollowerHeader.class);
    }

    private @NotNull ChzzkFollowerPart getFollowerPart(int page) {
        String url = String.format(
                "https://api.chzzk.naver.com/manage/v1/channels/%s/followers?page=%d&size=%d",
                channelId, page, ChzzkFollowerHeader.SIZE_PER_PAGE);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkFollowerPart.class);
    }

    public @NotNull CompletableFuture<ChzzkChatClient> createChatClientAsync() {
        return CompletableFuture.supplyAsync(this::createChatClient);
    }

    public @NotNull ChzzkChatClient createChatClient() {
        ChzzkChatClient client = ChzzkChatClient.create(this);
        client.setWebsocketClientHandler(new ChzzkWebsocketClientHandler(client));
        return client;
    }

}
