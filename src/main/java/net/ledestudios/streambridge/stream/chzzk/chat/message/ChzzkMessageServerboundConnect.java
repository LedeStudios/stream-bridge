package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChzzkMessageServerboundConnect extends ChzzkMessageBase {

    private Body bdy;
    private int tid = 1;

    public ChzzkMessageServerboundConnect() {
        super(ChzzkMessageType.Command.CONNECT);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String accTkn;
        private String auth;
        private int devType = 2001;
        private String uid;
    }

}
