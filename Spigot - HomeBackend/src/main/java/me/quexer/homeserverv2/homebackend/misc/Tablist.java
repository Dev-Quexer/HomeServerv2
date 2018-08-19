package me.quexer.homeserverv2.homebackend.misc;


import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.entitys.BackendPlayer;
import me.quexer.homeserverv2.homebackend.enums.Group;
import me.quexer.homeserverv2.homebackend.api.NickAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class Tablist {

    public static Scoreboard sb;
    public static HashMap<UUID, String> t = new HashMap();
    public static boolean setTablist = true;
    public static boolean setChatPrefix = true;


    public static void load() {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewTeam("00001Admin");
        sb.registerNewTeam("00002SrDev");
        sb.registerNewTeam("00003SrMod");
        sb.registerNewTeam("00004SrBuilder");
        sb.registerNewTeam("00005Dev");
        sb.registerNewTeam("00006Mod");
        sb.registerNewTeam("00007Builder");
        sb.registerNewTeam("00008Content");
        sb.registerNewTeam("00009Supporter");
        sb.registerNewTeam("00010YouTuber");
        sb.registerNewTeam("00011PremiumPlus");
        sb.registerNewTeam("00012Premium");
        sb.registerNewTeam("00013Spieler");
        sb.getTeam("00001Admin");
        sb.getTeam("00002SrDev").setPrefix(net.md_5.bungee.api.ChatColor.AQUA + "SrDev " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.AQUA);
        sb.getTeam("00003SrMod");
        sb.getTeam("00004SrBuilder");
        sb.getTeam("00005Dev");
        sb.getTeam("00006Mod");
        sb.getTeam("00007Builder");
        sb.getTeam("00008Content");
        sb.getTeam("00009Supporter");
        sb.getTeam("00010YouTuber");
        sb.getTeam("00011PremiumPlus");
        sb.getTeam("00012Premium");
        sb.getTeam("00013Spieler");
    }

    public static void setPrefix(Player p) {

        new AsyncTask(() -> {
        Group group = HomeBackend.getBackendManager().getOnlinePlayer(p.getUniqueId().toString()).getGroup();
        String team = "";
        p.sendMessage(NickAPI.hasNick(p)+"");
        if (NickAPI.hasNick(p) == true) {
            p.sendMessage(NickAPI.hasNick(p)+"");
            sb.getTeam("00012Premium").setPrefix(net.md_5.bungee.api.ChatColor.GOLD + "Premium " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + ChatColor.GOLD);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00012Premium";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.ADMIN) {
            sb.getTeam("00001Admin").setPrefix(net.md_5.bungee.api.ChatColor.DARK_RED + "Admin " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.DARK_RED);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00001Admin";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.SRDEV) {
            sb.getTeam("00002SrDev").setPrefix(net.md_5.bungee.api.ChatColor.AQUA + "SrDev " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.AQUA);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00002SrDev";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.SRMOD) {
            sb.getTeam("00003SrMod").setPrefix(net.md_5.bungee.api.ChatColor.RED + "SrMod " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.RED);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00003SrMod";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.SRBUILDER) {
            sb.getTeam("00004SrBuilder").setPrefix(net.md_5.bungee.api.ChatColor.DARK_GREEN + "SrB " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.DARK_GREEN);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00004SrBuilder";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.SRDEV) {
            sb.getTeam("00005Dev").setPrefix(net.md_5.bungee.api.ChatColor.AQUA + "Dev " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.AQUA);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00005Dev";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.MOD) {
            sb.getTeam("00006Mod").setPrefix(net.md_5.bungee.api.ChatColor.RED + "Mod " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.RED);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00006Mod";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.BUILDER) {
            sb.getTeam("00007Builder").setPrefix(net.md_5.bungee.api.ChatColor.DARK_GREEN + "Builder " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.DARK_GREEN);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00007Builder";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.CONTENT) {
            sb.getTeam("00008Content").setPrefix(net.md_5.bungee.api.ChatColor.AQUA + "Content " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.AQUA);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00008Content";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.SUPPORTER) {
            sb.getTeam("00009Supporter").setPrefix(net.md_5.bungee.api.ChatColor.BLUE + "Sup " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.BLUE);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00009Supporter";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.YOUTUBER) {
            sb.getTeam("00010YouTuber").setPrefix(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "YT " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.DARK_PURPLE);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00010YouTuber";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.PREMIUMPLUS) {
            sb.getTeam("00011PremiumPlus").setPrefix(net.md_5.bungee.api.ChatColor.YELLOW + "P+ " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.YELLOW);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00011PremiumPlus";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else if (group == Group.PREMIUM) {
            sb.getTeam("00012Premium").setPrefix(net.md_5.bungee.api.ChatColor.GOLD + "Premium " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + net.md_5.bungee.api.ChatColor.GOLD);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00012Premium";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);
        } else {
            sb.getTeam("00013Spieler").setPrefix(net.md_5.bungee.api.ChatColor.GREEN + "Spieler " + net.md_5.bungee.api.ChatColor.DARK_GRAY + "● " + ChatColor.GREEN);
            if (t.containsKey(p.getUniqueId())) {
                sb.getTeam((String)t.get(p.getUniqueId())).removePlayer(p);
                t.remove(p.getUniqueId());
            }

            team = "00013Spieler";
            sb.getTeam(team).addPlayer(p);
            t.put(p.getUniqueId(), team);
            refresh(p);

        }
        });

    }

    public static void refresh(Player p) {
        new AsyncTask(() -> {
        Group group = HomeBackend.getBackendManager().getOnlinePlayer(p.getUniqueId().toString()).getGroup();
        Iterator var3 = Bukkit.getOnlinePlayers().iterator();



        if (NickAPI.hasNick(p)) {

            p.setPlayerListName(Group.PREMIUM.getPrefix() + p.getName());
            p.setDisplayName(Group.PREMIUM.getPrefix() + p.getName());

        } else if (group == Group.ADMIN) {
            p.setPlayerListName(Group.ADMIN.getPrefix() + p.getName());
            p.setDisplayName(Group.ADMIN.getPrefix() + p.getName());
        } else if (group == Group.SRDEV) {
            p.setPlayerListName(Group.SRDEV.getPrefix() + p.getName());
            p.setDisplayName(Group.SRDEV.getPrefix() + p.getName());
        } else if (group == Group.SRMOD) {
            p.setPlayerListName(Group.SRMOD.getPrefix() + p.getName());
            p.setDisplayName(Group.SRMOD.getPrefix() + p.getName());
        } else if (group == Group.SRBUILDER) {
            p.setPlayerListName(Group.SRBUILDER.getPrefix() + p.getName());
            p.setDisplayName(Group.SRBUILDER.getPrefix() + p.getName());
        } else if (group == Group.DEV) {
            p.setPlayerListName(Group.DEV.getPrefix() + p.getName());
            p.setDisplayName(Group.DEV.getPrefix() + p.getName());
        } else if (group == Group.MOD) {
            p.setPlayerListName(Group.MOD.getPrefix() + p.getName());
            p.setDisplayName(Group.MOD.getPrefix() + p.getName());
        } else if (group == Group.BUILDER) {
            p.setPlayerListName(Group.BUILDER.getPrefix() + p.getName());
            p.setDisplayName(Group.BUILDER.getPrefix() + p.getName());
        } else if (group == Group.CONTENT) {
            p.setPlayerListName(Group.CONTENT.getPrefix() + p.getName());
            p.setDisplayName(Group.CONTENT.getPrefix() + p.getName());
        } else if (group == Group.SUPPORTER) {
            p.setPlayerListName(Group.SUPPORTER.getPrefix() + p.getName());
            p.setDisplayName(Group.SUPPORTER.getPrefix() + p.getName());
        } else if (group == Group.YOUTUBER) {
            p.setPlayerListName(Group.YOUTUBER.getPrefix() + p.getName());
            p.setDisplayName(Group.YOUTUBER.getPrefix() + p.getName());
        } else if (group == Group.PREMIUMPLUS) {
            p.setPlayerListName(Group.PREMIUMPLUS.getPrefix() + p.getName());
            p.setDisplayName(Group.PREMIUMPLUS.getPrefix() + p.getName());
        } else if (group == Group.PREMIUM) {
            p.setPlayerListName(Group.PREMIUM.getPrefix() + p.getName());
            p.setDisplayName(Group.PREMIUM.getPrefix() + p.getName());
        } else {
            p.setPlayerListName(Group.SPIELER.getPrefix() + p.getName());
            p.setDisplayName(Group.SPIELER.getPrefix() + p.getName());
        }
            while(var3.hasNext()) {
                Player all = (Player)var3.next();
                all.setScoreboard(sb);
            }
        });

    }

    public static boolean isSetTablist() {
        return setTablist;
    }

    public static void setSetTablist(boolean setTablist) {
        Tablist.setTablist = setTablist;
    }

    public static boolean isSetChatPrefix() {
        return setChatPrefix;
    }

    public static void setSetChatPrefix(boolean setChatPrefix) {
        Tablist.setChatPrefix = setChatPrefix;
    }

}
