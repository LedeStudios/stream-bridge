package net.ledestudios.streambridge.chzzk.type;

import lombok.Getter;

@Getter
public class ChzzkFollowerHeader {

    public static final int SIZE_PER_PAGE = 10000;

    private int totalCount;
    private int totalPages;

}
