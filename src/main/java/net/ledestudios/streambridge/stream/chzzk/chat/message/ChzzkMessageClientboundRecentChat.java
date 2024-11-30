package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChzzkMessageClientboundRecentChat extends ChzzkMessageBase {

    private Body bdy;

    public ChzzkMessageClientboundRecentChat() {
        super(ChzzkMessageType.Command.RECENT_CHAT);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        private RecentChat[] messageList;
        private int userCount;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RecentChat {
            private String userId;
            private String content;
            private int messageTypeCode;
            private long createTime;
            private String extras;
            private String profile;
        }

    }

}
