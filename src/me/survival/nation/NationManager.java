package me.survival.nation;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by Jakob on 31.05.2016.
 */
public class NationManager {

    public static boolean isPlayerInNation(Player p,Nation nation) {
        return false;
    }

    public static void setPlayerInNation(Player p, Nation nation) {

    }

    public static void openCooseInventory(Player p){
        if(isPlayerInNation(p,Nation.N1) || isPlayerInNation(p,Nation.N2)){
            p.sendMessage(Main.prefix + "§7Du bist bereits in einer Nation!");
            return;
        }
        Inventory inv = p.getServer().createInventory(null,18,"§aNationManager");

        inv.setItem(11,new ItemBuilder(Material.WOOL,1,(short)14).setDiplayname(Nation.N1.getName()).setLore(new String[]{"§7Trete der " + Nation.N1.getName() + " Nation bei"}).build());
        inv.setItem(13,new ItemBuilder(Material.BARRIER).setDiplayname("§3Nationen").setLore(new String[]{"§7Trete einer Nation","§7bei um mit deiner Nation","§7gegen andere Nationen zu kämpfen!"}).build());
        inv.setItem(15,new ItemBuilder(Material.WOOL,1,(short)11).setDiplayname(Nation.N2.getName()).setLore(new String[]{"§7Trete der " + Nation.N2.getName() + " Nation bei"}).build());

        p.openInventory(inv);
    }

}
