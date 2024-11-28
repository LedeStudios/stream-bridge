package net.ledestudios.streambridge.stream.chzzk.chat.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ledestudios.streambridge.stream.chzzk.ChzzkChannel;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageServerboundConnect;
import net.ledestudios.streambridge.stream.chzzk.type.user.ChzzkUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChzzkServerboundConnectCommand extends ChzzkCommandExecutor {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void execute(@NotNull ChzzkChatClient chat, @Nullable String message) {
        final ChzzkChannel channel = chat.getChannel();

        final String auth = channel.getChzzk().isAnonymous() ? "SEND" : "READ";
        final ChzzkUser user = chat.getCurrentUser();
        final String uid = user == null ? "" : user.getUserIdHash();

        ChzzkMessageServerboundConnect data = new ChzzkMessageServerboundConnect();
        data.setCid(chat.getCid());
        data.setBdy(new ChzzkMessageServerboundConnect.Body());
        data.getBdy().setAccTkn(chat.getAccTkn());
        data.getBdy().setAuth(auth);
        data.getBdy().setUid(uid);

        final String msg = gson.toJson(data);
        chat.send(msg);
    }

}
