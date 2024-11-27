package chzzk;

import io.github.cdimascio.dotenv.Dotenv;
import net.ledestudios.streambridge.chzzk.Chzzk;
import net.ledestudios.streambridge.chzzk.ChzzkChannel;
import net.ledestudios.streambridge.naver.Naver;
import net.ledestudios.streambridge.chzzk.type.ChzzkFollower;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChzzkChannelTest {

    private final @NotNull String CHANNEL_TO_TEST = "46a5788917ee53a4a46f634d536bd238";

    private final @NotNull Dotenv env = Dotenv.configure().filename(".env").load();

    @Test
    public void testChzzkLiveStatus() {
        Assertions.assertDoesNotThrow(() -> {
            final ChzzkChannel channel = Chzzk.anonymous().getChannel(CHANNEL_TO_TEST);
            System.out.println("LiveStatus: " + channel.getLiveStatus());
        });
    }

    @Test
    public void testChzzkLiveDetail() {
        Assertions.assertDoesNotThrow(() -> {
           final ChzzkChannel channel = Chzzk.anonymous().getChannel(CHANNEL_TO_TEST);
           System.out.println("LiveDetail: " + channel.getLiveDetail());
        });
    }

    @Test
    public void testChzzkFollowerCount() {
        Assertions.assertDoesNotThrow(() -> {
            Naver.loginAsync(env.get("NAVER_ID"), env.get("NAVER_PW")).thenAccept(naver -> {
                final ChzzkChannel channel = Chzzk.login(naver).getChannel(CHANNEL_TO_TEST);
                System.out.println("FollowerCount: " + channel.getFollowerCount());
            }).join();
        });
    }

    @Test
    public void testChzzkFollowers() {
        Assertions.assertDoesNotThrow(() -> {
            Naver.loginAsync(env.get("NAVER_ID"), env.get("NAVER_PW")).thenAccept(naver -> {
                final ChzzkChannel channel = Chzzk.login(naver).getChannel(CHANNEL_TO_TEST);
                List<ChzzkFollower> followers = channel.getFollowers();
                System.out.println("FollowerCount: " + followers.size());
                System.out.println("Followers: " + followers);
            }).join();
        });
    }

}
