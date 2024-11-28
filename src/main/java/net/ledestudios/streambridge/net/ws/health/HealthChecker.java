package net.ledestudios.streambridge.net.ws.health;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class HealthChecker {

    private ScheduledExecutorService executor;

    public final void start() {
        stop();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::check, 0, 1, TimeUnit.SECONDS);
    }

    public final void stop() {
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
    }

    public abstract void check();

}
