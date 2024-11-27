package net.ledestudios.streambridge.chzzk;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.ledestudios.streambridge.chzzk.type.ChzzkFollower;
import net.ledestudios.streambridge.chzzk.type.ChzzkLiveDetail;
import net.ledestudios.streambridge.chzzk.type.ChzzkLiveStatus;
import net.ledestudios.streambridge.net.ChzzkHttpService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChzzkChannel {

    private final Gson gson = new Gson();

    private final @NotNull ChzzkHttpService http;
    private final @NotNull String channel;

    public ChzzkChannel(@NotNull Chzzk chzzk, @NotNull String channel) {
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

    public @NotNull List<ChzzkFollower> getFollowers() {
        return List.of();
    }

}
