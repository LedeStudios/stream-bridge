package net.ledestudios.streambridge.stream.chzzk.type.chat;

public class ChzzkSubscribeChat extends ChzzkChat {

    public int getSubscriptionMonth() {
        return getExtras().getMonth();
    }

    public String getSubscriptionTierName() {
        return getExtras().getTierName();
    }

}
