package me.quexer.homeserverv2.homebackend.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface ItemListener {
    void interact(Player player, Inventory inv, ClickType clickType, ItemStack itemStack);
}
