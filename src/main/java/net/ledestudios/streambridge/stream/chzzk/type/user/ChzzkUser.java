package net.ledestudios.streambridge.stream.chzzk.type.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChzzkUser {

    private boolean hasProfile;
    private String userIdHash;
    private String nickname;
    private String profileImageUrl;
    private Object[] penalties;
    private boolean officialNotiAgree;
    private String officialNotiAgreeUpdatedDate;
    private boolean verifiedMark;
    private boolean loggedIn;

}
