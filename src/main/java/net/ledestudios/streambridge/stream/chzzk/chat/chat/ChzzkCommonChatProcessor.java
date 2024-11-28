package net.ledestudios.streambridge.stream.chzzk.chat.chat;

import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundRecentChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkChat;
import org.jetbrains.annotations.NotNull;

public class ChzzkCommonChatProcessor extends ChzzkChatProcessor {

    @Override
    public void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundChat.@NotNull Body data) {
        ChzzkChat chat = deserialize(new ChzzkChat(), data);
        client.getListeners().forEach(listener -> listener.onChat(chat));
    }

    @Override
    public void process(@NotNull ChzzkChatClient client,@NotNull ChzzkMessageClientboundRecentChat.Body.RecentChat data) {
        ChzzkChat chat = deserialize(new ChzzkChat(), data);
        client.getListeners().forEach(listener -> listener.onChat(chat));
    }

}
