package me.survival.elite;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by mariusk on 09.06.2016.
 */
public class  Command_Head implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("vetox.head")) {
            Inventory inv = p.getServer().createInventory(null,44,"§aHeads");
            inv.setItem(0, new ItemBuilder(Material.DIAMOND_ORE).setDiplayname("§6Head").build());
            inv.setItem(1, new ItemBuilder(Material.EMERALD_ORE).setDiplayname("§6Head").build());
            inv.setItem(2, new ItemBuilder(Material.LAPIS_ORE).setDiplayname("§6Head").build());
            inv.setItem(3, new ItemBuilder(Material.GOLD_ORE).setDiplayname("§6Head").build());
            inv.setItem(4, new ItemBuilder(Material.IRON_ORE).setDiplayname("§6Head").build());

            inv.setItem(6, new ItemBuilder(Material.DIAMOND_BLOCK).setDiplayname("§6Head").build());
            inv.setItem(7, new ItemBuilder(Material.EMERALD_BLOCK).setDiplayname("§6Head").build());
            inv.setItem(8, new ItemBuilder(Material.REDSTONE_BLOCK).setDiplayname("§6Head").build());
            //LINE 2
            ItemStack i = new ItemStack(91);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("§6Head");
            i.setItemMeta(im);
            inv.setItem(9, i);
            inv.setItem(10, new ItemBuilder(Material.PUMPKIN).setDiplayname("§6Head").build());
            inv.setItem(11, new ItemBuilder(Material.MELON_BLOCK).setDiplayname("§6Head").build());
            inv.setItem(12, new ItemBuilder(Material.HAY_BLOCK).setDiplayname("§6Head").build());
            //LINE 3
            inv.setItem(13, new ItemBuilder(Material.GLASS,1,(short)13).setDiplayname("§6Head").build());
            inv.setItem(14, new ItemBuilder(Material.GLASS,1,(short)2).setDiplayname("§6Head").build());
            inv.setItem(15, new ItemBuilder(Material.GLASS,1,(short)6).setDiplayname("§6Head").build());
            inv.setItem(16, new ItemBuilder(Material.GLASS,1,(short)9).setDiplayname("§6Head").build());
            inv.setItem(17, new ItemBuilder(Material.GLASS,1,(short)10).setDiplayname("§6Head").build());
            inv.setItem(18, new ItemBuilder(Material.GLASS,1,(short)11).setDiplayname("§6Head").build());
            //inv.setItem(18, new ItemBuilder(Material.GLASS,1,(short)5).setDiplayname("§6Head").build());
            //inv.setItem(18, new ItemBuilder(Material.GLASS,1,(short)4).setDiplayname("§6Head").build());
            //inv.setItem(18, new ItemBuilder(Material.GLASS,1,(short)3).setDiplayname("§6Head").build());
            //LINE 4
            inv.setItem(19, new ItemBuilder(Material.WORKBENCH).setDiplayname("§6Head").build());
            inv.setItem(20, new ItemBuilder(Material.FURNACE).setDiplayname("§6Head").build());
            //LINE 5
            inv.setItem(28, new ItemBuilder(Material.QUARTZ_BLOCK).setDiplayname("§6Head").build());
            inv.setItem(29, new ItemBuilder(Material.QUARTZ_BLOCK,1,(short)1).setDiplayname("§6Head").build());
            inv.setItem(30, new ItemBuilder(Material.GLOWSTONE).setDiplayname("§6Head").build());
            inv.setItem(31, new ItemBuilder(Material.ICE).setDiplayname("§6Head").build());
            //LINE 6
            inv.setItem(37, new ItemBuilder(Material.BEACON).setDiplayname("§6Head").build());
            inv.setItem(38, new ItemBuilder(Material.TNT).setDiplayname("§6Head").build());
            inv.setItem(39, new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§6Head").build());
            inv.setItem(40, new ItemBuilder(Material.LOG).setDiplayname("§6Head").build());
            inv.setItem(41, new ItemBuilder(Material.STONE,1,(short)6).setDiplayname("§6Head").build());
            inv.setItem(42, new ItemBuilder(Material.SEA_LANTERN).setDiplayname("§6Head").build());
            inv.setItem(44, new ItemBuilder(Material.BARRIER).setDiplayname("§cREMOVE HEAD").build());
            //Open inv
            p.openInventory(inv);
        }else{
            p.sendMessage(Main.prefix + "§7Diesen Befehl kannst du nur mit §6Elite §7und §aTitan §7benutzen!");
        }
        return false;
    }


    public static void onKlick(Player p, Material m) {
        Inventory inv = p.getInventory();
        Material ma = inv.getItem(39).getType();
        if(m!=Material.AIR || m!=Material.BARRIER){
            if(ma!=Material.LEATHER_HELMET||ma!=Material.CHAINMAIL_HELMET||ma!=Material.GOLD_HELMET||ma!=Material.IRON_HELMET||ma!=Material.DIAMOND_HELMET) {
                p.getInventory().setItem(39, new ItemBuilder(m).setDiplayname("§6Head").build());
                p.sendMessage(Main.prefix + "Du hast nun einen Block auf!");
            }else{
                p.sendMessage(Main.prefix + "§cBitte setze erst deinen Helm ab!");
            }
        }
        if(m.equals(Material.BARRIER)){
            if(ma!=Material.LEATHER_HELMET||ma!=Material.CHAINMAIL_HELMET||ma!=Material.GOLD_HELMET||ma!=Material.IRON_HELMET||ma!=Material.DIAMOND_HELMET||ma!=Material.AIR) {
                p.getInventory().setItem(39, new ItemStack(Material.AIR));
                p.sendMessage(Main.prefix + "Dein Block wurde erfolgreich entfernt! ");
            }else{
                p.sendMessage(Main.prefix + "Du hast keinen Block auf deinem Kopf!");
            }
        }
    }

    public static boolean onHeadClick(Player p, ItemStack clickmaterial) {
        ItemStack realhead = p.getInventory().getItem(39);
        if(realhead.equals(clickmaterial)) {
            Material ma = realhead.getType();
            if(ma != null || ma != Material.AIR || ma!=Material.LEATHER_HELMET||ma!=Material.CHAINMAIL_HELMET||ma!=Material.GOLD_HELMET||ma!=Material.IRON_HELMET||ma!=Material.DIAMOND_HELMET) {
                return false;
            }
            p.sendMessage(Main.prefix + "§7Du hast deinen Kopf entfernt.");
            p.getInventory().getItem(39).setType(Material.AIR);
            return true;
        }
        return false;
    }
}

