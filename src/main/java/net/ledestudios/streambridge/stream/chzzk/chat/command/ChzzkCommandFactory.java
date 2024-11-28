package net.ledestudios.streambridge.stream.chzzk.chat.command;

import com.google.common.collect.Maps;
import net.ledestudios.streambridge.exception.InvalidTypeException;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ChzzkCommandFactory {

    private final @NotNull Map<ChzzkMessageType.Command, ChzzkCommandExecutor> commands = Maps.newHashMap();

    public ChzzkCommandFactory() {
        commands.put(ChzzkMessageType.Command.CONNECT, new ChzzkServerboundConnectCommand());
        commands.put(ChzzkMessageType.Command.CONNECTED, new ChzzkClientboundConnectedCommand());
        commands.put(ChzzkMessageType.Command.PING, new ChzzkPingCommand());
        commands.put(ChzzkMessageType.Command.RECENT_CHAT, new ChzzkClientboundRecentChatCommand());
        commands.put(ChzzkMessageType.Command.CHAT, new ChzzkClientboundChatCommand());
        commands.put(ChzzkMessageType.Command.DONATION, new ChzzkClientboundChatCommand());
    }

    public @Nullable ChzzkCommandExecutor getCommand(@NotNull ChzzkMessageType.Command cmd) {
        return commands.get(cmd);
    }

}
