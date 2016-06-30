package me.survival.commands;

import me.survival.Main;
import me.survival.api.ClickableChat;
import me.survival.objects.LocationReport;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by mariusk on 30.06.2016.
 */
public class Command_Report_Location implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        switch(cmd.getName()){
            case "reportchunk":
                if (args.length == 0) {
                    p.sendMessage(Main.prefix + "§cDer Befehl lautet: §3/reportchunk <Grund>");
                    return true;
                }
                String grund = "";
                for (int i = 0; i < args.length; i++) {
                    grund = grund + args[i] + " ";
                }
                Location loc = p.getLocation();
                LocationReport locr = new LocationReport(loc, grund);
                p.sendMessage(Main.prefix + "§7Diese Region wurde erfolgreich mit dem Grund: " + grund + "Reported!");
                for (Player o : Bukkit.getOnlinePlayers()) {
                    //TODO Abfrage ob Supporter online sind und wenn denen diese Location senden.

                    VetoxPlayer vP = (VetoxPlayer)VetoxPlayer.stats.get(p.getUniqueId());


                    ClickableChat.send(o, Main.prefix, "§cEs wurde eine Location für " + grund + " reportet!", "§7Clicke um zu der Stelle zu gelangen", "/tp " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
                }
                break;
            case "reportedchunks":
                p.sendMessage(Main.prefix + "§cReportete Regionen:");
                for(int i = 0;i<LocationReport.locationreports.size();i++) {
                    LocationReport lr = LocationReport.locationreports.get(i);
                    Location lrloc = LocationReport.getLocation(lr);
                    ClickableChat.send(p, "§3" + i + ":", "§cLocation für " + LocationReport.getReason(lr) + " reportet!", "§7Clicke um zu der Stelle zu gelangen", "/tp " + lrloc.getBlockX() + " " + lrloc.getBlockY() + " " + lrloc.getBlockZ());
                }
                break;
        }
        return false;
    }
}
