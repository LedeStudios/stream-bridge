package net.ledestudios.streambridge.stream.chzzk.chat.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.exception.ChatConnectionFailedException;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundConnected;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChzzkClientboundConnectedCommand extends ChzzkCommandExecutor {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void execute(@NotNull ChzzkChatClient chat, @Nullable String message) {
        if (message == null) {
            return;
        }

        ChzzkMessageClientboundConnected data = gson.fromJson(message, ChzzkMessageClientboundConnected.class);
        if (data.getRetCode() != 0) {
            throw new ChatConnectionFailedException(data.getRetCode(), data.getRetMsg());
        }

        chat.setSid(data.getBdy().getSid());
        chat.getListeners().forEach(listener -> listener.onConnected(chat));
        chat.getHealthChecker().start();
    }

}
