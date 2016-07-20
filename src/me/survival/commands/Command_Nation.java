package me.survival.commands;

import me.survival.nation.Nation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Time;

/**
 * Created by Jakob on 03.07.2016.
 */
public class Command_Nation implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
        Player p = (Player) cs;
        if(args.length == 0) {
            sendHelp(p);
            return true;
        }

        //Fight
        if(args[0].equalsIgnoreCase("fight")) {
            if(args.length == 1) {
                Nation.joinFight(p);
                return true;
            } else {
                p.sendMessage(Nation.prefix + "§cSyntax: §7/nation fight!");
                return true;
            }
        }



        sendHelp(p);
        return false;
    }

    public void sendHelp(Player p) {
        p.sendMessage(Nation.prefix + "§7Befehle:");
        p.sendMessage("  §a- §f/nation help");
        p.sendMessage("  §a- §f/nation info");
        p.sendMessage("  §a- §f/nation fight");
    }

}
