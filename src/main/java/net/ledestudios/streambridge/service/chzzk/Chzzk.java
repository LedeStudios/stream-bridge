package net.ledestudios.streambridge.service.chzzk;

import net.ledestudios.streambridge.service.naver.Naver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Chzzk {

    private final @Nullable String aut;
    private final @Nullable String ses;

    public static @NotNull Chzzk anonymous() {
        return new Chzzk(null, null);
    }

    public static @NotNull Chzzk login(@NotNull String aut, @NotNull String ses) {
        return new Chzzk(aut, ses);
    }

    public static @NotNull Chzzk login(@NotNull Naver naver) {
        if (naver.isAnonymous()) {
            throw new IllegalArgumentException("Must be logged in to Naver.");
        }
        return new Chzzk(naver.getAutToken(), naver.getSesToken());
    }

    private Chzzk(@Nullable String aut, @Nullable String ses) {
        this.aut = aut;
        this.ses = ses;
    }

    public @NotNull ChzzkChannel getChannel(@NotNull String channelId) {
        return new ChzzkChannel(this, channelId);
    }

    public boolean isAnonymous() {
        return aut == null || ses == null;
    }

    public @Nullable String getAutToken() {
        return aut;
    }

    public @Nullable String getSesToken() {
        return ses;
    }

}
