package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.objects.Rules;

public class Command_Rules implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		
		if(args.length == 0) {
			p.sendMessage(Main.prefix + "§3Regeln: ");
			for(Rules r : Rules.values()) {
				p.sendMessage("  §3" + r.getId() + "§7) §f" + r.getName());
			}
			
			
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/regeln!");
		}
		
		
		return false;
	}
	
}
