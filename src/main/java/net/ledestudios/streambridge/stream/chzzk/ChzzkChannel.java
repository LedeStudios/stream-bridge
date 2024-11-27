package net.ledestudios.streambridge.stream.chzzk;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.ledestudios.streambridge.net.http.ChzzkHttpService;
import net.ledestudios.streambridge.net.ws.ChzzkWebsocketClientHandler;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.type.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChzzkChannel {

    private final Gson gson = new Gson();

    private final @NotNull ChzzkHttpService http;
    private final @NotNull String channel;

    ChzzkChannel(@NotNull Chzzk chzzk, @NotNull String channel) {
        this.http = new ChzzkHttpService(chzzk);
        this.channel = channel;
    }

    public @NotNull CompletableFuture<ChzzkLiveDetail> getLiveDetailAsync() {
        return CompletableFuture.supplyAsync(this::getLiveDetail);
    }

    public @NotNull ChzzkLiveDetail getLiveDetail() {
        String url = String.format("https://api.chzzk.naver.com/service/v2/channels/%s/live-detail", channel);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveDetail.class);
    }

    public @NotNull CompletableFuture<ChzzkLiveStatus> getLiveStatusAsync() {
        return CompletableFuture.supplyAsync(this::getLiveStatus);
    }

    public @NotNull ChzzkLiveStatus getLiveStatus() {
        String url = String.format("https://api.chzzk.naver.com/polling/v2/channels/%s/live-status", channel);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveStatus.class);
    }

    public @NotNull CompletableFuture<String> getChatAccessTokenAsync() {
        return CompletableFuture.supplyAsync(this::getChatAccessToken);
    }

    public @NotNull String getChatAccessToken() {
        return String.format(
                "https://comm-api.game.naver.com/nng_main/v1/chats/access-token?channelId=%s&chatType=STREAMING",
                getLiveStatus().getChatChannelId());
    }

    public @NotNull String getChatWebsocketUrl() {
        int serverId = 0;
        for (char i : channel.toCharArray()) {
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
                channel, ChzzkFollowerHeader.SIZE_PER_PAGE);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkFollowerHeader.class);
    }

    private @NotNull ChzzkFollowerPart getFollowerPart(int page) {
        String url = String.format(
                "https://api.chzzk.naver.com/manage/v1/channels/%s/followers?page=%d&size=%d",
                channel, page, ChzzkFollowerHeader.SIZE_PER_PAGE);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkFollowerPart.class);
    }

    public @NotNull CompletableFuture<ChzzkChatClient> openChatAsync() {
        return CompletableFuture.supplyAsync(this::openChat);
    }

    public @NotNull ChzzkChatClient openChat() {
        ChzzkChatClient chat = ChzzkChatClient.open(this);
        chat.setWebsocketClientHandler(new ChzzkWebsocketClientHandler(chat));
        return chat;
    }

}
