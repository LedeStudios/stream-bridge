package net.ledestudios.streambridge.chzzk.channel;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@Getter
public class ChzzkLiveStatus {

    protected String liveTitle;
    protected String status;
    protected int concurrentUserCount;
    protected int accumulateCount;
    protected boolean paidPromotion;
    protected boolean adult;
    protected boolean clipActive;
    protected String chatChannelId;
    protected List<String> tags;
    protected String categoryType;
    protected String liveCategory;
    protected String liveCategoryValue;
    protected String livePollingStatusJson;
    protected Object faultStatus;
    protected Object userAdultStatus;
    protected boolean chatActive;
    protected String chatAvailableGroup;
    protected String chatAvailableCondition;
    protected int minFollowerMinute;
    protected boolean chatDonationRankingExposure;

    public boolean isOnline() {
        return status.equalsIgnoreCase("open");
    }

    public @NotNull List<String> getTags() {
        return List.copyOf(tags);
    }

    public @NotNull Optional<String> getCategoryType() {
        return Optional.ofNullable(categoryType);
    }

    public @NotNull Optional<String> getLiveCategory() {
        return Optional.ofNullable(liveCategory);
    }

}