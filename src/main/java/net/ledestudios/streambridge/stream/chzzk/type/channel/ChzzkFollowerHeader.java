package net.ledestudios.streambridge.stream.chzzk.type.channel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChzzkFollowerHeader {

    public static final int SIZE_PER_PAGE = 10000;

    private int totalCount;
    private int totalPages;

}
