package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Heal implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(p.hasPermission("vetox.heal")) {
			if(args.length == 0) {
			
				p.setHealth(20);
				p.setFoodLevel(25);
				p.sendMessage(Main.prefix + "§7Du wurdest §3geheilt§7!");
				return true;
			}
		} else {
			p.sendMessage(Main.prefix + "§cDieser Befehl ist nur für Teammitglieder verfügbar!");
		}
		
		
		
		return false;
	}

}
