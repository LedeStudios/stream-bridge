package net.ledestudios.streambridge.stream.chzzk.type.chat;

public class ChzzkDonationChat extends ChzzkChat {

    public int getPayAmount() {
        return getExtras().getPayAmount();
    }

}
