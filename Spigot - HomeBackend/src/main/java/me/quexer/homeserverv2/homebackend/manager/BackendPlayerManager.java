package me.quexer.homeserverv2.homebackend.manager;

import com.mongodb.client.model.Filters;
import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.builder.FireworkBuilder;
import me.quexer.homeserverv2.homebackend.builder.TitleBuilder;
import me.quexer.homeserverv2.homebackend.entitys.BackendPlayer;
import me.quexer.homeserverv2.homebackend.enums.Group;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

public class BackendPlayerManager {


    public void getUser(OfflinePlayer player, Consumer<BackendPlayer> consumer) {
        if(player.isOnline() == player.getPlayer().hasMetadata("user")) {
            consumer.accept((BackendPlayer) player.getPlayer().getMetadata("backendPlayer").get(0).value());
            return;
        }

        HomeBackend.getMongoManager().getCollection("BackendPlayer").find(Filters.eq("uuid", player.getUniqueId().toString())).first((document, throwable) -> {
            if(document == null) {
                BackendPlayer user = new BackendPlayer();
                user.setCoins(1000);
                user.setElo(100);
                user.setLastPlayed(System.currentTimeMillis());
                user.setLevel(1);
                user.setNick(false);
                user.setNextDailyReward(System.currentTimeMillis());
                user.setUuid(player.getUniqueId().toString());
                user.setXp(0);
                user.setKeys(1);
                user.setDetectivePässe(1);
                user.setTraitorPässe(1);
                user.setGroup(Group.SPIELER);

                consumer.accept(user);
                HomeBackend.getInstance().setMetadata(player.getPlayer(), "backendPlayer", user);

                document = document = HomeBackend.getGson().fromJson(HomeBackend.getGson().toJson(user), Document.class);

                HomeBackend.getMongoManager().getCollection("BackendPlayer").insertOne(document, (aVoid, throwable1) -> {
                    player.getPlayer().sendMessage(HomeBackend.getPrefix()+"§7Du wurdest §aerfolgreich §7in der Datenbank erstellt§8.");
                });

                return;
            } else {
                BackendPlayer user = HomeBackend.getGson().fromJson(document.toJson(), BackendPlayer.class);
                consumer.accept(user);
                HomeBackend.getInstance().setMetadata(player.getPlayer(), "backendPlayer", user);
                return;
            }
        });
    }

    public void updateUser(BackendPlayer backendPlayer, Consumer<BackendPlayer> consumer) {

        Document document = HomeBackend.getGson().fromJson(HomeBackend.getGson().toJson(backendPlayer), Document.class);

        HomeBackend.getMongoManager().getCollection("BackendPlayer")
                .replaceOne(Filters.eq("uuid", backendPlayer.getUuid()), document, (result, t) -> {

                    OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(backendPlayer.getUuid()));
                    if(player != null && player.isOnline()) {
                        HomeBackend.getInstance().setMetadata(player.getPlayer(), "backendPlayer", backendPlayer);
                    }

                    consumer.accept(backendPlayer);
                });
    }

    public void addXp(BackendPlayer backendPlayer, int xp) {
        backendPlayer.setXp(backendPlayer.getXp()+xp);
        while (backendPlayer.getXp() >= 5000) {
            backendPlayer.setLevel(backendPlayer.getLevel()+1);
            backendPlayer.setXp(backendPlayer.getXp()-5000);
            long newCoins = 1850+new Random().nextInt(500);
            backendPlayer.setCoins(backendPlayer.getCoins()+newCoins);
            Player player = Bukkit.getPlayer(UUID.fromString(backendPlayer.getUuid()));
            if(player != null) {
                player.sendMessage("");
                player.sendMessage(HomeBackend.getPrefix() + "§7Du hast §e" + newCoins + " §6Coins §7erhalten");
                player.sendMessage(HomeBackend.getPrefix()+"§7Du hast das Level §e" + backendPlayer.getLevel() + " §8[§a" + backendPlayer.getXp() + "§8/§a5000§8]");
                player.sendMessage("");
                new FireworkBuilder(player.getLocation(), FireworkEffect.Type.BALL, Color.YELLOW, 100);
                TitleBuilder.sendTitle(player, 10, 80, 40, "§b§bLEVEL UP§8!", "§7Du bist jetzt Level §b" + backendPlayer.getLevel());
            }
        }
    }


}
