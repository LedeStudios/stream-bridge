package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.Getter;

@Getter
public class ChzzkMessageServerboundPing {

    private final int cmd = ChzzkMessageType.Command.PING.getRaw();
    private final String ver = "2";

}
