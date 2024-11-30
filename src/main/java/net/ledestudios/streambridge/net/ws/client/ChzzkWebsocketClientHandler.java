package net.ledestudios.streambridge.net.ws.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.command.ChzzkCommandExecutor;
import net.ledestudios.streambridge.stream.chzzk.chat.command.ChzzkCommandFactory;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkCommand;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageServerboundSendChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;

public class ChzzkWebsocketClientHandler extends WebsocketClientHandler {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private final @NotNull ChzzkChatClient chat;
    private final @NotNull ChzzkCommandFactory factory;

    public ChzzkWebsocketClientHandler(@NotNull ChzzkChatClient chat) {
        this.chat = chat;
        this.factory = new ChzzkCommandFactory();
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        ChzzkCommandExecutor command = factory.getCommand(ChzzkMessageType.Command.CONNECT);
        if (command != null) {
            command.execute(chat);
        }
    }

    @Override
    public void onMessage(String message) {
        try {
            ChzzkCommand cmd = gson.fromJson(message, ChzzkCommand.class);
            ChzzkCommandExecutor command = factory.getCommand(cmd.getCommand());
            if (command != null) {
                 command.execute(chat, message);
            }
        } catch (Exception ex) {
            chat.getListeners().forEach(listener -> listener.onError(ex));
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        chat.getListeners().forEach(listener -> listener.onClosed(code, reason, remote));
        chat.getHealthChecker().stop();
    }

    @Override
    public void onError(Exception ex) {
        chat.getListeners().forEach(listener -> listener.onError(ex));
    }

    @Override
    public void sendMessage(@NotNull String message) {
        ChzzkMessageServerboundSendChat.Body.Extras extras = new ChzzkMessageServerboundSendChat.Body.Extras();
        extras.setStreamingChannelId(chat.getChannel().getChannelId());

        ChzzkMessageServerboundSendChat data = new ChzzkMessageServerboundSendChat();
        data.setCid(chat.getCid());
        data.setSid(chat.getSid());
        data.getBdy().setExtras(gson.toJson(extras));
        data.getBdy().setMsg(message);
        data.getBdy().setMsgTypeCode(ChzzkMessageType.ChatType.TEXT.getRaw());

        chat.send(gson.toJson(data));
    }
}
