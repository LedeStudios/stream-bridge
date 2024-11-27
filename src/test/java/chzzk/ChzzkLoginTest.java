package chzzk;

import io.github.cdimascio.dotenv.Dotenv;
import net.ledestudios.streambridge.chzzk.Chzzk;
import net.ledestudios.streambridge.chzzk.Naver;
import net.ledestudios.streambridge.util.Chrome;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChzzkLoginTest {

    private final @NotNull Dotenv env =  Dotenv.configure().filename(".env").load();

    public ChzzkLoginTest() {
        Chrome.setDriverProperty(env.get("CHROME_DRIVER"));
    }

    @Test
    public void testNaverLoginSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            Naver.login(env.get("NAVER_ID"), env.get("NAVER_PW"));
        });
    }

    @Test
    public void testChzzkLoginWithNaverSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            Naver.loginAsync(env.get("NAVER_ID"), env.get("NAVER_PW")).thenAccept(Chzzk::login).join();
        });
    }

}
