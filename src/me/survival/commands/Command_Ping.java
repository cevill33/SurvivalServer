package me.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Ping implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			int ping = getPing(p);
			p.sendMessage(Main.prefix + "§7Dein Ping: §3"  + ping + "ms§7.");
			if(ping > 500) {
				p.sendMessage(Main.prefix + "§7Du tust mir leid :(.");
			}
		} else {
			if(args.length == 1) {
				Player target = Bukkit.getPlayerExact(args[0]);
				if(target != null) {
					p.sendMessage(Main.prefix + "§7" + args[0] + "'s Ping: §3" + getPing(target) + "ms§7.");
					return true;
				} else {
					p.sendMessage(Main.prefix + "§cDer Spieler ist nicht online!");
					return true;
				}
				
				
			} else {
				p.sendMessage(Main.prefix + "§cSyntax: §7/ping <Spieler>.");
			}
			
			
		}
		
		
		
		//
		//
		//
		//
		//
		//
		//
		//
		//
		
		
		
		return false;
	}

	
	
	public static int getPing(Player player) {
	    return ((CraftPlayer) player).getHandle().ping;
	}
	
	
	
	
	
}
