package net.ledestudios.streambridge.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.ledestudios.streambridge.stream.chzzk.Chzzk;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChzzkHttpService implements HttpService {

    private final @NotNull OkHttpClient client;

    public ChzzkHttpService(@NotNull Chzzk chzzk) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (!chzzk.isAnonymous()) {
            builder.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Cookie", createAuthHeader(chzzk))
                        .build();
                return chain.proceed(request);
            });
        }

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("User-Agent", HttpService.USER_AGENT)
                    .build();
            return chain.proceed(request);
        });

        client = builder.build();
    }

    @Override
    public @NotNull JsonElement get(@NotNull String url) {
        try {
            Request request = new Request.Builder().url(url).get().build();
            return request(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull JsonElement delete(@NotNull String url) {
        try {
            Request request = new Request.Builder().url(url).delete().build();
            return request(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull JsonElement request(@NotNull Request request) throws IOException {
        try(Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    return JsonParser.parseString(body.string()).getAsJsonObject().get("content");
                }
            }
            throw new IOException(response.code() + " " + response.message());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull String createAuthHeader(@NotNull Chzzk chzzk) {
        return String.format("NID_AUT=%s; NID_SES=%s", chzzk.getAutToken(), chzzk.getSesToken());
    }

}
