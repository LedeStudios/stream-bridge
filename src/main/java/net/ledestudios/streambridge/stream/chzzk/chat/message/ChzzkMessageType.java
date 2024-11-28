package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class ChzzkMessageType {

    @Getter
    @AllArgsConstructor
    public enum Command {

        UNKNOWN(-1),

        CLOSE(-2),

        PING(0),
        PONG(10000),
        CONNECT(100),
        CONNECTED(10100),
        REQUEST_RECENT_CHAT(5101),
        RECENT_CHAT(15101),
        EVENT(93006),
        CHAT(93101),
        DONATION(93102),
        KICK(94005),
        BLOCK(94006),
        BLIND(94008),
        NOTICE(94010),
        PENALTY(94015),
        SEND_CHAT(3101),
        ;

        private final int raw;

        public static @NotNull Command getByRaw(int raw) {
            for (Command value : values()) {
                if (value.getRaw() == raw) {
                    return value;
                }
            }
            return UNKNOWN;
        }

    }

    @Getter
    @AllArgsConstructor
    public enum ChatType {

        UNKNOWN(-1),
        TEXT(1),
        IMAGE(2),
        STICKER(3),
        VIDEO(4),
        RICH(5),
        DONATION(10),
        SUBSCRIPTION(11),
        SYSTEM_MESSAGE(30),
        ;

        private final int raw;

        public static @NotNull ChatType getByRaw(int raw) {
            for (ChatType value : values()) {
                if (value.getRaw() == raw) {
                    return value;
                }
            }
            return UNKNOWN;
        }

    }

}
