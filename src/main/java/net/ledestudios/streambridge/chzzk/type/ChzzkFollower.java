package net.ledestudios.streambridge.chzzk.type;

import lombok.Getter;
import net.ledestudios.streambridge.util.Time;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Optional;

@Getter
public class ChzzkFollower {

    private User user;
    private Following following;
    private Following channelFollowing;

    @Getter
    public static class User {
        private String userIdHash;
        private String nickname;
        private String profileImageUrl;
        private boolean verifiedMark;
    }

    @Getter
    public static class Following {
        private boolean following;
        private boolean notification;
        private String followDate;

        @NotNull Optional<ZonedDateTime> followDateAsZonedDateTime() {
            return Optional.ofNullable(Time.parse(followDate));
        }
    }

}