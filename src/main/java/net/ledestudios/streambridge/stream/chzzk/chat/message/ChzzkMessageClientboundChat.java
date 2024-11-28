package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChzzkMessageClientboundChat extends ChzzkMessageBase {

    public Body[] bdy;

    public ChzzkMessageClientboundChat() {
        super(ChzzkMessageType.Command.CHAT);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String uid;
        private String msg;
        private int msgTypeCode;
        private long ctime;
        private String extras;
        private String profile;
        private String msgStatusType;
    }

}
