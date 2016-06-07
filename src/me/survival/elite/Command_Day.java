package me.survival.elite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;


public class Command_Day implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {

		Player p = (Player) cs;
		
		if(args.length == 0) {
		 	
			if(p.hasPermission("vetox.day")) {
				
				if(!p.getWorld().getName().equals("Monsterworld")) {
					
					p.getWorld().setTime(4000);
					p.sendMessage(Main.prefix + "§7Du hast die Zeit geändert!");
					
					
				} else {
					p.sendMessage(Main.prefix + "§cIn dieser Welt ist der COMMAND verboten!");
				}
				
				
				
				
				
			} else {
				
				p.sendMessage(Main.prefix + "§cDu kannst diesen Befehl nur mit §6§lELITE §coder §a§lTITAN §cnutzen!");
				
			}
			
			
			
			
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/day!");
		}
		
		
		
		
		
		
		
		
		return false;
	}

	
	
	
	
	
	
	
	
}
