package me.survival.commands;

import me.survival.methods.NickNamer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by mariusk on 16.06.2016.
 */
public class Command_Nick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        Inventory inv = p.getServer().createInventory(null,9,"§aNickNamer");

        p.openInventory(inv);
        return false;
    }
}
