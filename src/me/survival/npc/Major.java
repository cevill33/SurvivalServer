package me.survival.npc;

import me.survival.api.ItemBuilder;
import me.survival.nation.Nation;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by mariusk on 16.06.2016.
 */
public class Major  {

    public static void openNationCooseGui(Player p){
        Inventory inv = p.getServer().createInventory(null,27,"§aWähle deine Nation/Rasse");
        inv.setItem(12, new ItemBuilder(Material.PAPER).setDiplayname(Nation.N1.getName()).build());
        inv.setItem(14, new ItemBuilder(Material.PAPER).setDiplayname(Nation.N2.getName()).build());
        inv.setItem(26, new ItemBuilder(Material.BARRIER).setDiplayname(Nation.N2.getName()).build());

        p.openInventory(inv);
    }
    public static void setMajor(Player p){

    }
    public static void getMajor(Player p){

    }
}
