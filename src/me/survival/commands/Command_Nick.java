package me.survival.commands;

import me.survival.Main;
import me.survival.api.ItemBuilder;
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

import java.util.ArrayList;

/**
 * Created by mariusk on 16.06.2016.
 */
public class Command_Nick implements CommandExecutor {

    public static ArrayList<String> cooldownnick = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {//TODO Command Testen
        Player p = (Player)sender;
        if(!p.hasPermission("vetox.nick")){
            p.sendMessage(Main.prefix + "§7Du benötigst einen Rang um dich nicken zu können!");
            return false;
        }
        Inventory inv = p.getServer().createInventory(null,36,"§aNickNamer");

        for(Nick nick : Nick.nicks) {
            System.out.println(nick.getPlayername());
            ItemStack head = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
            SkullMeta cm = (SkullMeta) head.getItemMeta();
            cm.setOwner(nick.getPlayerskin());
            head.setItemMeta(cm);
            inv.addItem(head);
        }
        inv.setItem(36,new ItemBuilder(Material.BARRIER).setDiplayname("§cReset Skin").build());

        p.openInventory(inv);
        return false;
    }

}
