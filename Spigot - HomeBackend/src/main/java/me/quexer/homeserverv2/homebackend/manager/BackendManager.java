package me.quexer.homeserverv2.homebackend.manager;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.entitys.BackendPlayer;
import org.bukkit.Bukkit;

import java.util.function.Consumer;

public class BackendManager {

    public BackendPlayer getOnlinePlayer(String UUID) {
        return (BackendPlayer) Bukkit.getPlayer(java.util.UUID.fromString(UUID)).getMetadata("backendPlayer").get(0).value();
    }

    public void getOfflinePlayer(String UUID, Consumer<BackendPlayer> consumer) {
        HomeBackend.getBackendPlayerManager().getUser(Bukkit.getOfflinePlayer(java.util.UUID.fromString(UUID)), backendPlayer -> {
            consumer.accept(backendPlayer);
        });
    }

}
