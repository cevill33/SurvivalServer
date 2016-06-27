package me.survival.nation;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by Jakob on 31.05.2016.
 */
public class NationManager {

    public static boolean isPlayerInNation(Player p) {
        return false;
    }

    public static void setPlayerInNation(Player p, Nation nation) {

    }
    public static void openCooseInventory(Player p){
        if(isPlayerInNation(p)){
            p.sendMessage(Main.prefix + "§7Du bist bereits in einer Nation!");
            return;
        }
        Inventory inv = p.getServer().createInventory(null,18,"§aNationManager");

        inv.setItem(11,new ItemBuilder(Material.WOOL,1,(short)14).setDiplayname("§cAincrad").setLore(new String[]{"§7Trete der Aincraden Nation bei"}).build());
        inv.setItem(13,new ItemBuilder(Material.BARRIER).setDiplayname("§3Nationen").setLore(new String[]{"§7Trete einer Nation","§7bei um mit deiner Nation","§7gegen andere Nationen zu kämpfen!"}).build());
        inv.setItem(15,new ItemBuilder(Material.WOOL,1,(short)11).setDiplayname("§2Trivia").setLore(new String[]{"§7Trete der Trivianischen Nation "}).build());

        p.openInventory(inv);
    }
    



}
