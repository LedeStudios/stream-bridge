package net.ledestudios.streambridge.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public static final @NotNull DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final @NotNull ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

    public static @Nullable ZonedDateTime parse(@Nullable String date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.parse(date, FORMATTER).atZone(ZONE_ID);
    }

}
