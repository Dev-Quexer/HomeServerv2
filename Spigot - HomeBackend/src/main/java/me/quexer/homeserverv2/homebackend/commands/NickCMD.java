package me.quexer.homeserverv2.homebackend.commands;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.api.NickAPI;
import me.quexer.homeserverv2.homebackend.enums.SoundType;
import me.quexer.homeserverv2.homebackend.misc.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class NickCMD implements CommandExecutor {

    public ArrayList<Player> used = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {


        Player p = (Player) s;
        HomeBackend.getBackendManager().getOfflinePlayer(p.getUniqueId().toString(), backendPlayer -> {
        if(backendPlayer.getGroup().getGroupId() >= 3) {
            if(HomeBackend.isNickOnThisServer()) {
                if (!used.contains(p)) {
                    p.sendMessage(NickAPI.hasNick(p)+"");
                    if (NickAPI.hasNick(p)) {
                        p.sendMessage(NickAPI.hasNick(p)+"");
                        NickAPI.removeNick(p);
                        p.sendMessage(NickAPI.hasNick(p)+"");
                        //Tablist.setPrefix(p);
                        used.add(p);

                        Bukkit.getScheduler().runTaskLaterAsynchronously(HomeBackend.getInstance(), () -> used.remove(p), 20 * 3);

                    } else {
                        p.sendMessage(NickAPI.hasNick(p)+"");
                        NickAPI.setRandomNick(p);
                        p.sendMessage(NickAPI.hasNick(p)+"");
                        //NickAPI.changeSkin(((CraftPlayer)p), p.getName());
                        //Tablist.setPrefix(p);

                        used.add(p);

                        Bukkit.getScheduler().runTaskLaterAsynchronously(HomeBackend.getInstance(), () -> used.remove(p), 20 * 3);

                    }

                } else {
                    p.sendMessage(HomeBackend.getPrefix() + "§cWarte einen Moment§8, §cbefor du diesen §eCommand §cbenutzt");
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 2F, 0.3F);
                }
            } else {
                p.sendMessage(HomeBackend.getPrefix()+"§cAuf diesem Server ist das Nicken nicht erlaubt§8!");
                HomeBackend.getSoundManager().play(p, SoundType.BAD);
            }

        } else {
            p.sendMessage(HomeBackend.getPrefix()+"§cDazu hast du keine Rechte!");
        }


        });




        return true;
    }


}
