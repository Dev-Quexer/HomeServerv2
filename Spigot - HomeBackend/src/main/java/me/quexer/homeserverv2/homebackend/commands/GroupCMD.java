package me.quexer.homeserverv2.homebackend.commands;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.enums.Group;
import me.quexer.homeserverv2.homebackend.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class GroupCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //group info [Player]
        //group set [Player] [Group]

        if(HomeBackend.getBackendManager().getOnlinePlayer(((Player)sender).getUniqueId().toString()).getGroup().getGroupId() > 12 || sender.hasPermission("admin")) {
            if(args.length > 0) {
            switch (args[0]) {
                case "info": {
                    Player player = (Player) sender;
                    UUIDFetcher.getUUID(args[1], uuid -> {
                        HomeBackend.getBackendManager().getOfflinePlayer(uuid.toString(), backendPlayer -> {
                            player.sendMessage(HomeBackend.getPrefix() + "§7Der Spieler §e" + Bukkit.getOfflinePlayer(uuid).getName() + " §7ist in der Gruppe §a" + backendPlayer.getGroup().getFull());
                        });
                    });
                    break;
                }
                case "set": {
                    Player player = (Player) sender;
                    UUIDFetcher.getUUID(args[1], uuid -> {
                        HomeBackend.getBackendManager().getOfflinePlayer(uuid.toString(), backendPlayer -> {
                            backendPlayer.setGroup(Group.valueOf(args[2].toUpperCase()));
                            if (Bukkit.getPlayer(uuid) != null) {
                                HomeBackend.getBackendPlayerManager().updateUser(backendPlayer, backendPlayer1 -> {
                                    Bukkit.getPlayer(uuid).kickPlayer(
                                            "§8§m------------------------------------\n"+
                                            HomeBackend.getPrefix()+"§7Du hast jetzt den Rang §a" + backendPlayer.getGroup().getFull()+"\n"+
                                            "§7Bitte betrete den Server erneut§8!\n"+
                                            "§8§m------------------------------------");
                                });

                            }
                            player.sendMessage(HomeBackend.getPrefix() + "§7Der Spieler §e" + Bukkit.getOfflinePlayer(uuid).getName() + " §7ist jetzt in der Gruppe §a" + backendPlayer.getGroup().getFull());
                        });
                    });
                    break;
                }
                case "list": {
                    sender.sendMessage(HomeBackend.getPrefix()+"§8§m-------------------------");
                    sender.sendMessage(HomeBackend.getPrefix()+"§7Eine §eListe §7mit allen Gruppen §8➜");
                    Arrays.stream(Group.values()).forEach(group -> {
                        sender.sendMessage("§8- §e"+group.toString());
                    });
                    sender.sendMessage(HomeBackend.getPrefix()+"§8§m-------------------------");
                    break;
                }
                default: {
                    sender.sendMessage(HomeBackend.getPrefix() + "§8§m-------------------------");
                    sender.sendMessage(HomeBackend.getPrefix() + "§e/Group info §8[§ePlayer§8]");
                    sender.sendMessage(HomeBackend.getPrefix() + "§e/Group set §8[§ePlayer§8] §8[§aGroup§8]");
                    sender.sendMessage(HomeBackend.getPrefix() + "§e/Group list");
                    sender.sendMessage(HomeBackend.getPrefix() + "§8§m-------------------------");
                    break;
                }
            }
            } else {
                sender.sendMessage(HomeBackend.getPrefix() + "§8§m-------------------------");
                sender.sendMessage(HomeBackend.getPrefix() + "§e/Group info §8[§ePlayer§8]");
                sender.sendMessage(HomeBackend.getPrefix() + "§e/Group set §8[§ePlayer§8] §8[§aGroup§8]");
                sender.sendMessage(HomeBackend.getPrefix() + "§e/Group list");
                sender.sendMessage(HomeBackend.getPrefix() + "§8§m-------------------------");
            }



        }
        return true;
    }
}
