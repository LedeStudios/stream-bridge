package net.ledestudios.streambridge.service.chzzk;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.ledestudios.streambridge.net.ChzzkHttpService;
import net.ledestudios.streambridge.service.chzzk.type.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChzzkChannel {

    private final Gson gson = new Gson();

    private final @NotNull ChzzkHttpService http;
    private final @NotNull String channel;

    ChzzkChannel(@NotNull Chzzk chzzk, @NotNull String channel) {
        this.http = new ChzzkHttpService(chzzk);
        this.channel = channel;
    }

    public @NotNull ChzzkLiveDetail getLiveDetail() {
        String url = String.format("https://api.chzzk.naver.com/service/v2/channels/%s/live-detail", channel);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveDetail.class);
    }

    public @NotNull ChzzkLiveStatus getLiveStatus() {
        String url = String.format("https://api.chzzk.naver.com/polling/v2/channels/%s/live-status", channel);
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkLiveStatus.class);
    }

    public int getFollowerCount() {
        return getFollowerHeader().getTotalCount();
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

}
