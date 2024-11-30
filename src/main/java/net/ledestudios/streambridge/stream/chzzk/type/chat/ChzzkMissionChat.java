package net.ledestudios.streambridge.stream.chzzk.type.chat;

public class ChzzkMissionChat extends ChzzkDonationChat {

    public int getDurationTime() {
        return getExtras().getDurationTime();
    }

    public String getMissionDonationId() {
        return getExtras().getMissionDonationId();
    }

    public String getMissionCreatedTime() {
        return getExtras().getMissionCreatedTime();
    }

    public String getMissionEndTime() {
        return getExtras().getMissionEndTime();
    }

    public String getMissionText() {
        return getExtras().getMissionText();
    }

    public String getMissionStatus() {
        return getExtras().getStatus();
    }

    public boolean isMissionSucceed() {
        return getExtras().isSuccess();
    }

}
