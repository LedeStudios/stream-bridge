package net.ledestudios.streambridge.stream.chzzk.chat.command;

import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ChzzkCommandExecutor {

    public final void execute(@NotNull ChzzkChatClient chat) {
        this.execute(chat, null);
    }

    public abstract void execute(@NotNull ChzzkChatClient chat, @Nullable String message);

}
