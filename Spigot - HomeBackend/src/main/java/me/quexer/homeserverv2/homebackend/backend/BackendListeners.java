package me.quexer.homeserverv2.homebackend.backend;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.api.NickAPI;
import me.quexer.homeserverv2.homebackend.entitys.BackendPlayer;
import me.quexer.homeserverv2.homebackend.enums.Group;
import me.quexer.homeserverv2.homebackend.enums.SoundType;
import me.quexer.homeserverv2.homebackend.events.PlayerNickEvent;
import me.quexer.homeserverv2.homebackend.events.PlayerRemoveNickEvent;
import me.quexer.homeserverv2.homebackend.manager.EventManager;
import me.quexer.homeserverv2.homebackend.misc.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BackendListeners {

    private EventManager.EventListener<PlayerJoinEvent> joinEvent;
    private EventManager.EventListener<PlayerQuitEvent> quitEvent;
    private EventManager.EventListener<PlayerNickEvent> nickEvent;
    private EventManager.EventListener<PlayerRemoveNickEvent> removeNickEvent;
    private EventManager.EventListener<AsyncPlayerChatEvent> chatEvent;

    public BackendListeners() {
        setJoinEvent((PlayerJoinEvent event) -> {
            HomeBackend.getBackendPlayerManager().getUser(event.getPlayer(), backendPlayer -> {
                HomeBackend.getInstance().setMetadata(event.getPlayer(), "backendPlayer", backendPlayer);
                if(HomeBackend.isNickOnThisServer()) {
                    if (event.getPlayer().hasPermission("nick.nick")) {
                            if(backendPlayer.isNick()) {
                                NickAPI.setRandomNick(event.getPlayer());
                            }
                    }
                }
                if(Tablist.isSetTablist()) {
                    Tablist.setPrefix(event.getPlayer());
                }
            });
        });
        setQuitEvent((PlayerQuitEvent event) -> {
            BackendPlayer backendPlayer = (BackendPlayer) event.getPlayer().getMetadata("user").get(0).value();
            backendPlayer.setLastPlayed(System.currentTimeMillis());
            HomeBackend.getInstance().removeMetadata(event.getPlayer(), "user");
            HomeBackend.getBackendPlayerManager().updateUser(backendPlayer, backendPlayer1 -> {

            });

        });
        setNickEvent((PlayerNickEvent event) -> {
            event.getPlayer().sendMessage(HomeBackend.getPrefix()+"§7Dein neuer §e§lNickname §7lautet§8: §6"+event.getNick());
            HomeBackend.getSoundManager().play(event.getPlayer(), SoundType.GOOD);
            event.getPlayer().sendMessage(NickAPI.hasNick(event.getPlayer())+"");
            if(Tablist.isSetTablist()) {
                event.getPlayer().sendMessage(NickAPI.hasNick(event.getPlayer())+"");
                Tablist.setPrefix(event.getPlayer());
            }
        });

        setRemoveNickEvent((PlayerRemoveNickEvent event) -> {
            event.getPlayer().sendMessage(HomeBackend.getPrefix()+"§7Dein §e§lNickname §7wurde entfernt§8!");
            HomeBackend.getSoundManager().play(event.getPlayer(), SoundType.BAD);
            if(Tablist.isSetTablist()) {
                Tablist.setPrefix(event.getPlayer());
            }
        });

        setChatEvent((AsyncPlayerChatEvent event) -> {
            if(Tablist.isSetChatPrefix()) {
                if(NickAPI.hasNick(event.getPlayer())) {
                    event.setFormat(Group.PREMIUM.getPrefix() +event.getPlayer().getName()+ " §8➜ §7" + event.getMessage());
                } else {
                    event.setFormat(event.getPlayer().getDisplayName() + " §8➜ §7" + event.getMessage());
                }
            }
            String msg = event.getMessage().replaceAll("%", "%%");
            event.setMessage(msg);
        });


        HomeBackend.getEventManager().registerEvent(PlayerJoinEvent.class, joinEvent);
        HomeBackend.getEventManager().registerEvent(PlayerQuitEvent.class, quitEvent);
        HomeBackend.getEventManager().registerEvent(PlayerNickEvent.class, nickEvent);
        HomeBackend.getEventManager().registerEvent(PlayerRemoveNickEvent.class, removeNickEvent);
        HomeBackend.getEventManager().registerEvent(AsyncPlayerChatEvent.class, chatEvent);
    }

    public EventManager.EventListener<AsyncPlayerChatEvent> getChatEvent() {
        return chatEvent;
    }

    public void setChatEvent(EventManager.EventListener<AsyncPlayerChatEvent> chatEvent) {
        this.chatEvent = chatEvent;
    }

    public EventManager.EventListener<PlayerJoinEvent> getJoinEvent() {
        return joinEvent;
    }

    public void setJoinEvent(EventManager.EventListener<PlayerJoinEvent> joinEvent) {
        this.joinEvent = joinEvent;
    }

    public EventManager.EventListener<PlayerQuitEvent> getQuitEvent() {
        return quitEvent;
    }

    public void setQuitEvent(EventManager.EventListener<PlayerQuitEvent> quitEvent) {
        this.quitEvent = quitEvent;
    }

    public EventManager.EventListener<PlayerNickEvent> getNickEvent() {
        return nickEvent;
    }

    public void setNickEvent(EventManager.EventListener<PlayerNickEvent> nickEvent) {
        this.nickEvent = nickEvent;
    }

    public EventManager.EventListener<PlayerRemoveNickEvent> getRemoveNickEvent() {
        return removeNickEvent;
    }

    public void setRemoveNickEvent(EventManager.EventListener<PlayerRemoveNickEvent> removeNickEvent) {
        this.removeNickEvent = removeNickEvent;
    }
}
