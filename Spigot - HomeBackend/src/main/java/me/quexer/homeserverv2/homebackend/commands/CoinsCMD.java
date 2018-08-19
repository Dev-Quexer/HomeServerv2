package me.quexer.homeserverv2.homebackend.commands;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.misc.AsyncTask;
import me.quexer.homeserverv2.homebackend.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoinsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        new AsyncTask(() -> {
            commandSender.sendMessage(HomeBackend.getCoinsPrefix() + "§7Du hast §e" + HomeBackend.getBackendManager().getOnlinePlayer(Bukkit.getPlayer(commandSender.getName()).getUniqueId().toString()).getCoins() + " §7Coins§8.");
        });
             return true;
    }

}
