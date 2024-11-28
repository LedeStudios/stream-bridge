package net.ledestudios.streambridge.stream.chzzk.chat.chat;

import com.google.common.collect.Maps;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ChzzkChatProcessorFactory {

    private final @NotNull Map<ChzzkMessageType.ChatType, ChzzkChatProcessor> processors = Maps.newHashMap();

    public ChzzkChatProcessorFactory() {
        processors.put(ChzzkMessageType.ChatType.TEXT, new ChzzkCommonChatProcessor());
        processors.put(ChzzkMessageType.ChatType.DONATION, new ChzzkDonationChatProcessor());
        processors.put(ChzzkMessageType.ChatType.SUBSCRIPTION, new ChzzkSubscribeChatProcessor());
    }

    public @Nullable ChzzkChatProcessor getProcessor(@NotNull ChzzkMessageType.ChatType type) {
        return processors.get(type);
    }

}
