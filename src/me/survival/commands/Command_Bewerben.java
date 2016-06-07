package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Bewerben implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {

		Player p = (Player) cs;
		
		if(args.length == 0) {
			
			p.sendMessage(" ");
			p.sendMessage(Main.prefix + "§3Bewerben:");
			p.sendMessage(" §4Du kannst dich zurzeit nicht bewerben.");
			p.sendMessage(" §7Ausnahmen Developer (Da muss man sich aber im Ts melden).");
			p.sendMessage(" §7Du kannst dich aber nach einer Spielzeit von 5 Stunden bei einem");
			p.sendMessage(" §7Teammitglied melden um etwas als Builder vorzubauen!");
			p.sendMessage(" §4Falls du um einen Rang bettelst wirst du gebannt!");
			
			return true;
			
			
			
			
		}
		
		
		p.sendMessage(Main.prefix + "§cSyntax: §7/bewerben!");
		
		
		
		
		
		return false;
	}

}
