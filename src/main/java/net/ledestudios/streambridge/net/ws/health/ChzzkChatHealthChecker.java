package net.ledestudios.streambridge.net.ws.health;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageServerboundPing;
import org.jetbrains.annotations.NotNull;

public class ChzzkChatHealthChecker extends HealthChecker {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private final @NotNull ChzzkChatClient chat;

    @Setter
    private long lastReceivedMessageTime;

    private long lastSendPingTime;

    public ChzzkChatHealthChecker(@NotNull ChzzkChatClient chat) {
        this.chat = chat;
        this.lastReceivedMessageTime = System.currentTimeMillis();
        this.lastSendPingTime = lastReceivedMessageTime;
    }

    @Override
    public void check() {
        long current = System.currentTimeMillis();
        if (current - lastSendPingTime >= 60000 || // 1 minutes from last ping time
                current - lastReceivedMessageTime >= 20000 // 20 seconds later from last message
        ) {
            if(chat.isOpen()) {
                String json = gson.toJson(new ChzzkMessageServerboundPing());
                chat.send(json);
            }

            this.lastReceivedMessageTime = System.currentTimeMillis();
            this.lastSendPingTime = lastReceivedMessageTime;
        }
    }

}
