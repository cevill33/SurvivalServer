package me.survival.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {

		Player p = (Player) cs;
		
		if(args.length == 0) {
			p.sendMessage(Main.prefix + "§cSyntax: §7/gm 0|1|2|3");
			return true;
		}
		
		if(args[0].equals("0")) {
			if(p.hasPermission("vetox.gamemode.0")) {
				p.sendMessage(Main.prefix + "§7Du bist nun GM: §f" + args[0]);
				p.setGameMode(GameMode.SURVIVAL);
				
			}
			return true;
		}
		
		if(args[0].equals("1")) {
			if(p.hasPermission("vetox.gamemode.1")) {
				p.sendMessage(Main.prefix + "§7Du bist nun GM: §f" + args[0]);
				p.setGameMode(GameMode.CREATIVE);
				
			}
			return true;
		}
		
		if(args[0].equals("2")) {
			if(p.hasPermission("vetox.gamemode.2")) {
				p.sendMessage(Main.prefix + "§7Du bist nun GM: §f" + args[0]);
				p.setGameMode(GameMode.ADVENTURE);
				
			}
			return true;
		}
		
		if(args[0].equals("3")) {
			if(p.hasPermission("vetox.gamemode.3")) {
				p.sendMessage(Main.prefix + "§7Du bist nun GM: §f" + args[0]);
				p.setGameMode(GameMode.SPECTATOR);
				
			}
			return true;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
