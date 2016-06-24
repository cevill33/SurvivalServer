package me.survival.commands;

import me.survival.methods.NickNamer;
import me.survival.objects.Nick;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by mariusk on 16.06.2016.
 */
public class Command_Nick implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {//TODO Command Testen
        Player p = (Player)sender;
        Inventory inv = p.getServer().createInventory(null,18,"§aNickNamer");

        for(Nick nick : Nick.nicks) {
            ItemStack head = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
            SkullMeta cm = (SkullMeta) head.getItemMeta();
            cm.setOwner(nick.getPlayerskin());
            head.setItemMeta(cm);
            inv.addItem(head);
        }



        p.openInventory(inv);
        return false;
    }

}
