package net.ledestudios.streambridge.stream.chzzk.chat;

import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkDonationChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkMissionChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkSubscribeChat;
import org.jetbrains.annotations.NotNull;

public interface ChzzkChatListener {

    default void onConnected(@NotNull ChzzkChatClient chat) {}

    default void onClosed(int code, @NotNull String reason, boolean remote) {}

    default void onError(@NotNull Exception ex) {}

    default void onChat(@NotNull ChzzkChat chat) {}

    default void onDonationChat(@NotNull ChzzkDonationChat chat) {}

    default void onMissionDonationChat(@NotNull ChzzkMissionChat chat) {}

    default void onSubscriptionChat(@NotNull ChzzkSubscribeChat chat) {}

}
