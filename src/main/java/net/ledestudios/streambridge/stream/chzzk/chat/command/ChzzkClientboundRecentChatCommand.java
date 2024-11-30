package net.ledestudios.streambridge.stream.chzzk.chat.command;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.chat.ChzzkChatProcessor;
import net.ledestudios.streambridge.stream.chzzk.chat.chat.ChzzkChatProcessorFactory;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundRecentChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChzzkClientboundRecentChatCommand extends ChzzkCommandExecutor {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private final @NotNull ChzzkChatProcessorFactory factory = new ChzzkChatProcessorFactory();

    @Override
    public void execute(@NotNull ChzzkChatClient chat, @Nullable String message) {
        if (message == null) {
            return;
        }

        ChzzkMessageClientboundRecentChat data = gson.fromJson(message, ChzzkMessageClientboundRecentChat.class);
        for (ChzzkMessageClientboundRecentChat.Body.RecentChat recentChat : data.getBdy().getMessageList()) {
            if (recentChat.getUserId().equals("@OPEN")) {
                continue;
            }

            ChzzkMessageType.ChatType type = ChzzkMessageType.ChatType.getByRaw(recentChat.getMessageTypeCode());
            ChzzkChatProcessor processor = factory.getProcessor(type);
            if (processor != null) {
                processor.process(chat, recentChat);
            }
        }
    }

}
