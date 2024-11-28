package net.ledestudios.streambridge.stream.chzzk.type.chat;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class ChzzkChat {

    private int msgTypeCode = 0;
    private String userId;
    private String content;
    private ZonedDateTime createTime;
    private Extras extras;
    private @Nullable Profile profile;

    @Getter
    @Setter
    @ToString
    public static class Extras {

        String donationType;
        String osType;

        int payAmount = -1;

        // Subscription
        int month = 0;
        String tierName = "";

        // Mission
        int durationTime;
        String missionDonationId;
        String missionCreatedTime;
        String missionEndTime;
        String missionText;
        String status;
        boolean success;

    }

    @Getter
    @Setter
    @ToString
    public static class Profile {
        private String nickname;
        private String profileImageUrl;
        private String userRoleCode;
        private boolean verifiedMark;

        private ActivityBadge[] activityBadges;
        private StreamingProperty streamingProperty;
    }

    @Getter
    @Setter
    @ToString
    public static class StreamingProperty {
        private Subscription subscription;
    }

    @Getter
    @Setter
    @ToString
    public static class Subscription {
        private int accmulativeMonth;
        private int tier;
        private PartialBadge badge;
    }

    @Getter
    @Setter
    @ToString
    public static class PartialBadge {
        private String imageUrl;
    }

    @Getter
    @Setter
    @ToString
    public static class ActivityBadge {
        private int badgeNo;
        private String badgeId;
        private boolean activated;
    }

}
