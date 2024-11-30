package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChzzkMessageClientboundConnected extends ChzzkMessageBase {

    private Body bdy = new Body();
    private int retCode;
    private String retMsg;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String sid;
    }

}
