package net.ledestudios.streambridge.stream.chzzk.chat.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.net.ws.health.ChzzkChatHealthChecker;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.chat.ChzzkChatProcessor;
import net.ledestudios.streambridge.stream.chzzk.chat.chat.ChzzkChatProcessorFactory;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChzzkClientboundChatCommand extends ChzzkCommandExecutor {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private final @NotNull ChzzkChatProcessorFactory factory = new ChzzkChatProcessorFactory();

    @Override
    public void execute(@NotNull ChzzkChatClient chat, @Nullable String message) {
        if (message == null) {
            return;
        }

        ChzzkChatHealthChecker checker = ((ChzzkChatHealthChecker) chat.getHealthChecker());
        checker.setLastReceivedMessageTime(System.currentTimeMillis());

        ChzzkMessageClientboundChat data = gson.fromJson(message, ChzzkMessageClientboundChat.class);
        for (ChzzkMessageClientboundChat.Body body : data.getBdy()) {
            if (body.getUid().equals("@OPEN")) {
                continue;
            }

            ChzzkMessageType.ChatType type = ChzzkMessageType.ChatType.getByRaw(body.getMsgTypeCode());
            ChzzkChatProcessor processor = factory.getProcessor(type);
            if (processor != null) {
                processor.process(chat, body);
            }
        }
    }

}
