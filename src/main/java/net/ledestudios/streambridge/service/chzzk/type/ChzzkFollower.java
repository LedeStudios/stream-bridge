package net.ledestudios.streambridge.service.chzzk.type;

import lombok.Getter;
import lombok.ToString;
import net.ledestudios.streambridge.util.Time;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Optional;

@Getter
@ToString
public class ChzzkFollower {

    private User user;
    private Following following;
    private Following channelFollowing;

    @Getter
    @ToString
    public static class User {
        private String userIdHash;
        private String nickname;
        private String profileImageUrl;
        private boolean verifiedMark;
    }

    @Getter
    @ToString
    public static class Following {
        private boolean following;
        private boolean notification;
        private String followDate;

        @NotNull Optional<ZonedDateTime> getFollowDateAsZonedDateTime() {
            return Optional.ofNullable(Time.parse(followDate));
        }
    }

}