package chzzk;

import io.github.cdimascio.dotenv.Dotenv;
import net.ledestudios.streambridge.stream.chzzk.Chzzk;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatClient;
import net.ledestudios.streambridge.stream.chzzk.chat.ChzzkChatListener;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkDonationChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkMissionChat;
import net.ledestudios.streambridge.stream.chzzk.type.chat.ChzzkSubscribeChat;
import net.ledestudios.streambridge.stream.naver.Naver;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

public class ChzzkChatTest {

    private final @NotNull String CHANNEL_TO_TEST = "3497a9a7221cc3ee5d3f95991d9f95e9";

    private final @NotNull Dotenv env = Dotenv.configure().filename(".env").load();

    @Test
    public void testSendChat() {
        Naver.loginAsync(env.get("NAVER_ID"), env.get("NAVER_PW")).thenAccept(naver -> {
            Chzzk chzzk = Chzzk.login(naver);
            ChzzkChatClient chat = chzzk.getChannel("").createChatClient();
            // chat.sendMessage("ㅋㅋㅋㅋㅋㅋㅋㅋ");
        });
    }

    @Test
    public void testChzzkChatConnect() {
        Naver.loginAsync(env.get("NAVER_ID"), env.get("NAVER_PW")).thenAccept(naver -> {
            Chzzk chzzk = Chzzk.login(naver);
            ChzzkChatClient chat = chzzk.getChannel(CHANNEL_TO_TEST).createChatClient();
            chat.addListener(new ChzzkChatListener() {

                @Override
                public void onConnected(@NotNull ChzzkChatClient chat) {
                    System.out.println("Connected: " + CHANNEL_TO_TEST);
                }

                @Override
                public void onClosed(int code, @NotNull String reason, boolean remote) {
                    System.out.println("Disconnected: " + CHANNEL_TO_TEST);
                }

                @Override
                public void onChat(@NotNull ChzzkChat chat) {
                    System.out.printf("[CommonChat] User: %s, Msg: %s \n",
                            chat.getUserId(), chat.getContent());
                }

                @Override
                public void onDonationChat(@NotNull ChzzkDonationChat chat) {
                    System.out.printf("[DonationChat] User: %s, Msg: %s, Amount: %d \n",
                            chat.getUserId(), chat.getContent(), chat.getExtras().getPayAmount());
                }

                @Override
                public void onMissionDonationChat(@NotNull ChzzkMissionChat chat) {
                    System.out.printf("[MissionChat] User: %s, Msg: %s, Amount: %d, Completed: %s\n",
                            chat.getUserId(), chat.getContent(), chat.getExtras().getPayAmount(), chat.getExtras().isSuccess());
                }

                @Override
                public void onSubscriptionChat(@NotNull ChzzkSubscribeChat chat) {
                    System.out.printf("[SubscriptionChat] User: %s, Msg: %s, Month: %d \n",
                            chat.getUserId(), chat.getContent(), chat.getExtras().getMonth());
                }

                @Override
                public void onError(@NotNull Exception ex) {
                    ex.printStackTrace();
                }
            });
            chat.open();
            System.out.println("Start Chat Client");
        });

        while (true) {

        }
    }

}
