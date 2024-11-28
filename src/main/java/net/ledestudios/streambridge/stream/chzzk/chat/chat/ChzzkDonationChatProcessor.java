package net.ledestudios.streambridge.stream.chzzk.chat.chat;

import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundRecentChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkMissionChat;
import org.jetbrains.annotations.NotNull;

public class ChzzkDonationChatProcessor extends ChzzkChatProcessor {

    @Override
    public void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundChat.@NotNull Body data) {
        ChzzkMissionChat chat = deserialize(new ChzzkMissionChat(), data);
        if (chat.getExtras().getDonationType().equals("MISSION")) {
            client.getListeners().forEach(listener -> listener.onMissionDonationChat(chat));
        } else {
            client.getListeners().forEach(listener -> listener.onDonationChat(chat));
        }
    }

    @Override
    public void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundRecentChat.Body.@NotNull RecentChat data) {
        ChzzkMissionChat chat = deserialize(new ChzzkMissionChat(), data);
        if (chat.getExtras().getDonationType().equals("MISSION")) {
            client.getListeners().forEach(listener -> listener.onMissionDonationChat(chat));
        } else {
            client.getListeners().forEach(listener -> listener.onDonationChat(chat));
        }
    }

}
