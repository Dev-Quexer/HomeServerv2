package me.quexer.homeserverv2.homebackend.misc;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import org.bukkit.Bukkit;

public class AsyncTask {

    private Runnable run;

    public AsyncTask(Runnable run) {
        this.run = run;
        Bukkit.getScheduler().runTaskAsynchronously(HomeBackend.getPlugin(HomeBackend.class), run);
    }
}
