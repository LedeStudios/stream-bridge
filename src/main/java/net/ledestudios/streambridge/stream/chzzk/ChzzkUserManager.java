package net.ledestudios.streambridge.stream.chzzk;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Getter;
import net.ledestudios.streambridge.net.http.ChzzkHttpService;
import net.ledestudios.streambridge.stream.chzzk.type.user.ChzzkUser;
import org.jetbrains.annotations.NotNull;

public class ChzzkUserManager {

    private final Gson gson = new Gson();
    private final @NotNull ChzzkHttpService http;

    @Getter
    private final @NotNull Chzzk chzzk;

    ChzzkUserManager(@NotNull Chzzk chzzk) {
        this.http = new ChzzkHttpService(chzzk);
        this.chzzk = chzzk;
    }

    public @NotNull ChzzkUser getCurrentUser() {
        if (chzzk.isAnonymous()) {
            throw new RuntimeException("Must be logged in to Naver.");
        }

        String url = "https://comm-api.game.naver.com/nng_main/v1/user/getUserStatus";
        JsonElement json = http.get(url);
        return gson.fromJson(json, ChzzkUser.class);
    }

}
