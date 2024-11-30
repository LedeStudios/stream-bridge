package net.ledestudios.streambridge.stream.chzzk.chat.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageServerboundPong;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChzzkPingCommand extends ChzzkCommandExecutor {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void execute(@NotNull ChzzkChatClient chat, @Nullable String message) {
        final ChzzkMessageServerboundPong pong = new ChzzkMessageServerboundPong();
        final String data = gson.toJson(pong);
        chat.send(data);
    }

}
