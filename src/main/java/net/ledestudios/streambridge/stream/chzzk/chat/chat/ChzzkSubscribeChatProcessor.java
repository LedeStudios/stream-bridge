package net.ledestudios.streambridge.stream.chzzk.chat.chat;

import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundRecentChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkSubscribeChat;
import org.jetbrains.annotations.NotNull;

public class ChzzkSubscribeChatProcessor extends ChzzkChatProcessor {

    @Override
    public void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundChat.@NotNull Body data) {
        ChzzkSubscribeChat chat = deserialize(new ChzzkSubscribeChat(), data);
        client.getListeners().forEach(listener -> listener.onSubscriptionChat(chat));
    }

    @Override
    public void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundRecentChat.Body.@NotNull RecentChat data) {
        ChzzkSubscribeChat chat = deserialize(new ChzzkSubscribeChat(), data);
        client.getListeners().forEach(listener -> listener.onSubscriptionChat(chat));
    }

}
