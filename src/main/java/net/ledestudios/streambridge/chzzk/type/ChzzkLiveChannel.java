package net.ledestudios.streambridge.chzzk.type;

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