package net.ledestudios.streambridge.stream.chzzk.type.chat;

import lombok.*;
import org.jetbrains.annotations.NotNull;
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

    public boolean hasProfile() {
        return profile != null;
    }

    public @NotNull String getUsername() {
        return profile != null ? profile.getNickname() : "";
    }

    @Getter
    @Setter
    @ToString
    public static class Extras {

        private String donationType;
        private String osType;

        private int payAmount = -1;

        // Subscription
        private int month = 0;
        private String tierName = "";

        // Mission
        private int durationTime;
        private String missionDonationId;
        private String missionCreatedTime;
        private String missionEndTime;
        private String missionText;
        private String status;
        private boolean success;

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
