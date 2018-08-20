package me.quexer.bewerbung.freundekopf.listeners;

import me.quexer.bewerbung.freundekopf.Freundekopf;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getPlayer().getItemInHand().getType() == Material.SKULL_ITEM) {
            Freundekopf.getHeadManager().openInvPage(e.getPlayer(), 1);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) {
            return;
        }
        if(event.getCurrentItem() == null) {
            return;
        }
        if(event.getInventory() == null) {
            return;
        }
        if(event.getCurrentItem().hasItemMeta() == false) {
            return;
        }
        if(event.getClickedInventory().getName().equals("§e§lFreundekopf")) {
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§8« §eZurück")) {
                System.out.println(Freundekopf.getHeadManager().getPlayerPageMap().get((Player) event.getWhoClicked()));
                Freundekopf.getHeadManager().openInvPage((Player)event.getWhoClicked(), Freundekopf.getHeadManager().getPlayerPageMap().get(event.getWhoClicked())-1);
                System.out.println(Freundekopf.getHeadManager().getPlayerPageMap().get((Player) event.getWhoClicked()));
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§eWeiter §8»")) {
                System.out.println(Freundekopf.getHeadManager().getPlayerPageMap().get((Player) event.getWhoClicked()));
                Freundekopf.getHeadManager().openInvPage((Player)event.getWhoClicked(), Freundekopf.getHeadManager().getPlayerPageMap().get(event.getWhoClicked())+1);
                System.out.println(Freundekopf.getHeadManager().getPlayerPageMap().get((Player) event.getWhoClicked()));
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

    }

}
