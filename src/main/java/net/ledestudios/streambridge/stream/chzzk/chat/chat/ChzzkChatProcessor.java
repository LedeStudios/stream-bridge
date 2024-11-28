package net.ledestudios.streambridge.stream.chzzk.chat.chat;

import com.google.gson.Gson;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundChat;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageClientboundRecentChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkChat;
import net.ledestudios.streambridge.util.Time;
import org.jetbrains.annotations.NotNull;

public abstract class ChzzkChatProcessor {

    private final @NotNull Gson gson = new Gson();

    public abstract void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundChat.Body data);

    public abstract void process(@NotNull ChzzkChatClient client, @NotNull ChzzkMessageClientboundRecentChat.Body.RecentChat data);

    protected final <T extends ChzzkChat> @NotNull T deserialize(@NotNull T chat, @NotNull ChzzkMessageClientboundChat.Body data) {
        chat.setContent(data.getMsg());
        chat.setMsgTypeCode(data.getMsgTypeCode());
        chat.setCreateTime(Time.parse(data.getCtime()));
        chat.setExtras(gson.fromJson(data.getExtras(), ChzzkChat.Extras.class));
        chat.setProfile(gson.fromJson(data.getProfile(), ChzzkChat.Profile.class));
        chat.setUserId(data.getUid());
        return chat;
    }

    protected final <T extends ChzzkChat> @NotNull T deserialize(@NotNull T chat, @NotNull ChzzkMessageClientboundRecentChat.Body.RecentChat data) {
        chat.setContent(data.getContent());
        chat.setMsgTypeCode(data.getMessageTypeCode());
        chat.setCreateTime(Time.parse(data.getCreateTime()));
        chat.setExtras(gson.fromJson(data.getExtras(), ChzzkChat.Extras.class));
        chat.setProfile(gson.fromJson(data.getProfile(), ChzzkChat.Profile.class));
        chat.setUserId(data.getUserId());
        return chat;
    }

}
