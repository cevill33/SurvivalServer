package me.survival.commands;

import me.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by mariusk on 02.08.2016.
 */
public class Command_Vanish implements CommandExecutor {

    public static ArrayList<String> spectatet = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.hasPermission("vetoxmc.vanish")){
            p.sendMessage(Main.prefix + "§cDieser Befehl ist nur für Teammitglieder verfügbar!");
            return false;
        }
        if(args[0].equalsIgnoreCase("list")){
            if(spectatet.isEmpty()){
                p.sendMessage(Main.prefix + "§7Im moment befindet sich kein Spieler im Vanish.");
                return false;
            }
            String imvanish = "§7";
            for(int i = 0;i<spectatet.size();i++){
                imvanish = imvanish + ", " + spectatet.get(i);
            }
            p.sendMessage(Main.prefix + "Im moment befinden sich diese Spieler im Vanish: \n " + imvanish);
            return false;
        }
        if(spectatet.contains(p.getName())){
            for(Player all : Bukkit.getOnlinePlayers()){
                all.showPlayer(p);
            }
            p.setAllowFlight(false);
            p.sendMessage(Main.prefix + "§7Du bist nun wieder für alle Spieler sichtbar.");
            return false;
        }
        for(Player all : Bukkit.getOnlinePlayers()){
            all.hidePlayer(p);
        }
        p.setAllowFlight(true);
        p.sendMessage(Main.prefix + "§7Du bist nun für alle Spieler unsichtbar.");
        return false;
    }
}
