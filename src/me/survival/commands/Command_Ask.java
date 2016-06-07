package me.survival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.ClickableChat;



public class Command_Ask implements CommandExecutor {
	
	public static List<String> asking = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player) cs;
		
			if(args.length > 0) {
							
			if(!asking.contains(p.getName())) {
			
			asking.add(p.getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					if(asking.contains(p.getName())) {
						asking.remove(p.getName());
					}
					
				}
			},20*60);
			
			
			String msg = "";
			
			
			
			for(int i = 0; i < args.length; i++) {
				msg = msg + " " + args[i];
			}
			
		
			int i = 0;
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("vetox.support")) {
					all.sendMessage("§6[§7Ask§6] §7" + p.getName() + "  §aFrage:§7  " + msg);
					ClickableChat.send(all, "§aFrage annehmen:  ", "§f§lJA", "", "/answer answer " + p.getName());
					i++;
					
				}
			}
			
			if(i < 1) {
				p.sendMessage("§6[§7Ask§6] §cEs ist zurzeit kein Teammitglied online, der dir weiterhelfen kann!");
			}
			
			
			
			
			
			
			
			} else {
				p.sendMessage("§6[§7Ask§6] §cDu kannst nur jede Minute den online Support nutzen!");
			}
			}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

	
	
	
	
	
}
