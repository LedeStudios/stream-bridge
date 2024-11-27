package net.ledestudios.streambridge.net.http;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

public interface HttpService {

    @NotNull String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0";

    @NotNull JsonElement get(@NotNull String url);

    @NotNull JsonElement delete(@NotNull String url);

}
