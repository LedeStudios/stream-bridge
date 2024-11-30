package net.ledestudios.streambridge.stream.chzzk.chat.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChzzkMessageBase {

    private String cid;
    private final String svcid = "game";
    private final String ver = "2";

    private final int cmd;

    public ChzzkMessageBase() {
        this.cmd = ChzzkMessageType.Command.UNKNOWN.getRaw();
    }

    public ChzzkMessageBase(ChzzkMessageType.Command cmd) {
        this.cmd = cmd.getRaw();
    }

}
