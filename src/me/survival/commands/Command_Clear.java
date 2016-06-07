package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Clear implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			if(p.hasPermission("vetox.clear")) {
				p.getInventory().clear();
				p.sendMessage(Main.prefix + "§7Inventar wurde §agecleart§7!");
				return true;
			}
			p.sendMessage(Main.prefix + "§7Dieser Befehl ist nicht für Spieler verfügbar!");
		}
		
		
		return false;
	}

}
