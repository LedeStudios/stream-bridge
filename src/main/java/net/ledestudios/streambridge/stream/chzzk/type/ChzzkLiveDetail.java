package net.ledestudios.streambridge.stream.chzzk.type;

import lombok.Getter;
import lombok.ToString;
import net.ledestudios.streambridge.util.Resolution;
import net.ledestudios.streambridge.util.Time;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Optional;

@Getter
@ToString
public class ChzzkLiveDetail extends ChzzkLiveStatus {

    private int liveId;
    private String liveImageUrl;
    private String defaultThumbnailImageUrl;
    private String openDate;
    private String closeDate;
    private ChzzkLiveChannel channel;

    public @NotNull String getLiveImageUrl(@NotNull Resolution resolution) {
        return liveImageUrl.replace("{type}", resolution.getRawAsString());
    }

    public @NotNull Optional<String> getDefaultThumbnailImageUrl() {
        return Optional.ofNullable(defaultThumbnailImageUrl);
    }

    public @NotNull Optional<ZonedDateTime> getOpenDate() {
        if (openDate == null) {
            return Optional.empty();
        }
        ZonedDateTime date = Time.parse(openDate);
        return Optional.of(date);
    }

    public @NotNull Optional<ZonedDateTime> getCloseDate() {
        if (closeDate == null) {
            return Optional.empty();
        }
        ZonedDateTime date = Time.parse(closeDate);
        return Optional.of(date);
    }

}