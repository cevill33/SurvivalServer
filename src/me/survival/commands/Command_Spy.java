package me.survival.commands;

import me.survival.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by mariusk on 02.08.2016.
 */
public class Command_Spy implements CommandExecutor {

    public static ArrayList<String> spy = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.hasPermission("vetoxmc.spy")){
            p.sendMessage(Main.prefix + "§cDieser Befehl ist nur für Teammitglieder verfügbar!");
            return false;
        }
        if(spy.contains(p.getName())){
            spy.remove(p.getName());
            p.sendMessage(Main.prefix + "Du siehst nun keine befehle mehr, die von anderen ausgeführt wurden!");
            return false;
        }
        spy.add(p.getName());
        p.sendMessage(Main.prefix + "Du siehst nun befehle die von anderen Spielern ausgeführt werden!");
        return false;
    }
}
