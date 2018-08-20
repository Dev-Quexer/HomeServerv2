package me.quexer.bewerbung.freundekopf.manager;

import me.quexer.bewerbung.freundekopf.Freundekopf;
import me.quexer.bewerbung.freundekopf.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import sun.security.x509.AttributeNameEnumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadManager {

    private HashMap<Player, Integer> playerPageMap = new HashMap<Player, Integer>();
    private List<String> names = new ArrayList<>();

    public void openInvPage(Player player, int page) {
        if(getPlayerPageMap().containsKey(player)) {
            getPlayerPageMap().remove(player);
        }
        getPlayerPageMap().put(player, page);
        Inventory inventory = Bukkit.createInventory(null, 54, "§e§lFreundekopf");
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        inventory.setItem(4, new ItemBuilder(Material.IRON_FENCE).setName("§7Seite§8: §e"+page).toItemStack());

        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        if(page > 1) {
            inventory.setItem(46, new ItemBuilder(Material.ARROW).setName("§8« §eZurück").toItemStack());
        }
        inventory.setItem(52, new ItemBuilder(Material.ARROW).setName("§eWeiter §8»").toItemStack());

        int take = 0;
        for (int i = 1; i < page; i++) {
            take+=36;
        }

        System.out.println(take);

        for (int i = 0; i < 36; i++) {
            System.out.println(i+take);
            if(names.size() > i+take) {
                System.out.println(i + take);
                inventory.setItem(i + 9, new ItemBuilder(Material.SKULL_ITEM).setDurability((short) 3).setSkullOwner(names.get(i + take)).setName("§e" + names.get(i + take)).toItemStack());
            }
        }


        player.openInventory(inventory);



    }

    public HashMap<Player, Integer> getPlayerPageMap() {
        return playerPageMap;
    }

    public List<String> getNames() {
        return names;
    }
}
