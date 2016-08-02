package me.survival.commands;

import me.survival.nation.Nation;
import me.vetoxapi.methods.NationPoints;
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

        if(1 == 1) {
            p.sendMessage(Nation.prefix + "§cDieser Spielmodus wird in den kommend den Tagen veröffentlicht, da es zurzeit noch ein paar schwierigkeiten gibt.");
            return true;
        }

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

        //Info
        if(args[0].equalsIgnoreCase("info")) {
            if(args.length == 1) {
                p.sendMessage(Nation.prefix + "Infos:");
                if(NationPoints.now != null) {
                    p.sendMessage(" §7Punktestand:");
                    p.sendMessage("     §fAincrad: §a" + NationPoints.now.getN1());
                    p.sendMessage("     §7Trivia : §a" + NationPoints.now.getN2());
                } else {
                    p.sendMessage(" §7Die Informationen werden zwischen 18:00 und 19:00 angezeigt.");
                }

                p.sendMessage(" §7Nation ist noch in der Entwicklung...");
                return true;
            } else {
                p.sendMessage(Nation.prefix + "§cSyntax: §7/nation info!");
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
