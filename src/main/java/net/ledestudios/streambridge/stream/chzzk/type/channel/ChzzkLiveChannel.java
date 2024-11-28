package net.ledestudios.streambridge.stream.chzzk.type.channel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChzzkLiveChannel {

    private String channelId;
    private String channelName;
    private String channelImageUrl;
    private boolean verifiedMark;

}