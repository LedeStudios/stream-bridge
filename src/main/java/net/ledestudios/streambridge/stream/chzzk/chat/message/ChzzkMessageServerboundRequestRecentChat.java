package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChzzkMessageServerboundRequestRecentChat extends ChzzkMessageBase {

    private Body bdy = new Body();
    private String sid;
    private int tid = 2;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private int recentMessageCount;
    }

}
