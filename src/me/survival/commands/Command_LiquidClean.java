package me.survival.commands;

import chunkgs.ChunkManager;
import me.survival.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Jakob on 14.04.2016.
 */
public class Command_LiquidClean implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
        Player p = (Player) cs;


        if(args.length == 0) {

            if(ChunkManager.isForHimBuildable(p.getUniqueId().toString(), p.getLocation())) {
                Block b = p.getLocation().getBlock();
                if(b.getType().equals(Material.LAVA) || b.getType().equals(Material.WATER) || b.getType().equals(Material.STATIONARY_WATER)  || b.getType().equals(Material.STATIONARY_LAVA)) {

                    p.sendMessage(Main.prefix + "§fDu hast den Block §b" + b.getType() + " §fentfernt!");
                    b.setType(Material.AIR);

                } else {
                    p.sendMessage(Main.prefix + "§cDu stehst auf keiner Flüssigkeit!");
                }

            } else {
                p.sendMessage(Main.prefix  + "§cDiesen Command kannst du nur auf deinem Gs benutzen!");
            }





        } else {
            p.sendMessage(Main.prefix + "§cSyntax: §7/liquidclean!");
        }


        return false;
    }
}
