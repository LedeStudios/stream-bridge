package net.ledestudios.streambridge.stream.naver;

import net.ledestudios.streambridge.util.Chrome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.concurrent.CompletableFuture;

public class Naver {

    private final @NotNull String id;
    private final @NotNull String password;

    private @Nullable String aut;
    private @Nullable String ses;

    public static @NotNull Naver login(@NotNull String id, @NotNull String password) {
        return new Naver(id, password).login().join();
    }

    public static @NotNull CompletableFuture<Naver> loginAsync(@NotNull String id, @NotNull String password) {
        return new Naver(id, password).login();
    }

    private Naver(@NotNull String id, @NotNull String password) {
        this.id = id;
        this.password = password;
    }

    private CompletableFuture<Naver> login() {
        return CompletableFuture.supplyAsync(() -> {
            WebDriver driver = Chrome.getDriver();
            driver.get("https://nid.naver.com/nidlogin.login");
            try {
                // Write id and pw fields
                if (driver instanceof JavascriptExecutor js) {
                    js.executeScript(String.format("document.getElementById('id').value='%s';", id));
                    js.executeScript(String.format("document.getElementById('pw').value='%s';", password));
                }

                // Click login button
                WebElement loginBtn = driver.findElement(By.id("log.login"));
                loginBtn.click();

                // Find cookies
                Cookie autCookie = driver.manage().getCookieNamed("NID_AUT");
                if (autCookie != null) {
                    aut = autCookie.getValue();
                }

                Cookie sesCookie = driver.manage().getCookieNamed("NID_SES");
                if (sesCookie != null) {
                    ses = sesCookie.getValue();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                driver.quit();
            }
            return this;
        });
    }

    public boolean isAnonymous() {
        return aut == null || ses == null;
    }

    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public @Nullable String getAutToken() {
        return aut;
    }

    public @Nullable String getSesToken() {
        return ses;
    }

}
