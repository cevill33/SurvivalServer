package me.survival.commands;

import java.util.ArrayList;
import java.util.List;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_SetHome implements CommandExecutor {

	List<String> cool = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) cs;
		
		if(args.length == 0) {

			if(!p.getWorld().getName().equals("Mainworld")) {
				p.sendMessage(Main.prefix + "§cDu kannst dein Home nur in der Hauptwelt Elbros setzten.");
				return true;
			}


			if(cool.contains(p.getName())) {
				p.sendMessage(Main.prefix + "§cDu kannst dein Home nur alle 30 Sekunden setzen!");
				return true;
			}
			cool.add(p.getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					
					cool.remove(p.getName());
					
				}
			},20* 30);

			new DBVetoxPlayer(p.getUniqueId().toString()).setLocation("home", p.getLocation());
			p.sendMessage(Main.prefix + "§7Du hast dein Home erfolgreich gesetzt!");
			
			
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/sethome!");
			p.sendMessage(Main.prefix + "§7Mit diesem Befehl setzt du dir dein Home welches du über den Ballon erreichen kannst!");
		}
		
		
		
		
		
		
		return false;
	}

	
	
	
	
	
	
}
