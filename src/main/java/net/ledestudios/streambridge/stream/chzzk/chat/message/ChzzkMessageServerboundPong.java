package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.Getter;

@Getter
public class ChzzkMessageServerboundPong {

    private final int cmd = ChzzkMessageType.Command.PONG.getRaw();
    private final String ver = "2";

}
