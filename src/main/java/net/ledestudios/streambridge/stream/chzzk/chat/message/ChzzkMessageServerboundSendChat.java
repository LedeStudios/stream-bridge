package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChzzkMessageServerboundSendChat extends ChzzkMessageBase {

    private Body bdy = new Body();
    private String sid;
    private int tid = 3;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        private String extras;
        private String msg;
        private long msgTime = System.currentTimeMillis();
        private int msgTypeCode = ChzzkMessageType.ChatType.TEXT.getRaw();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Extras {
            private String chatType = "STREAMING";
            private String osType = "PC";
            private String streamingChannelId = "";
            private String emojis = "";
        }

    }

}
