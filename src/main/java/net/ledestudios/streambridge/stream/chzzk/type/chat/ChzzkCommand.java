package net.ledestudios.streambridge.stream.chzzk.type.chat;

import lombok.Getter;
import lombok.ToString;
import net.ledestudios.streambridge.stream.chzzk.chat.message.ChzzkMessageType;

@Getter
@ToString
public class ChzzkCommand {

    private int cmd;

    public ChzzkMessageType.Command getCommand() {
        return ChzzkMessageType.Command.getByRaw(this.cmd);
    }

}
