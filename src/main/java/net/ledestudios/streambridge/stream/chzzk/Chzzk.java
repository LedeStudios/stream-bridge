package net.ledestudios.streambridge.stream.chzzk;

import net.ledestudios.streambridge.stream.naver.Naver;
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

    public @NotNull ChzzkChannelManager getChannelManager(@NotNull String channelId) {
        return new ChzzkChannelManager(this, channelId);
    }

    public @NotNull ChzzkUserManager getUserManager() {
        return new ChzzkUserManager(this);
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
