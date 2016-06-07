package me.survival.elite;

import me.survival.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_EnderChest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {

		Player p = (Player) cs;
		
		if(args.length == 0) {
			if(p.hasPermission("vetox.enderchest")) {
				
				p.openInventory(p.getEnderChest());
				
			} else {
				p.sendMessage(Main.prefix + "§cDu kannst diesen Befehl nur mit §6§lELITE §coder §a§lTITAN §cnutzen!");
			}
			
			
			
			
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/enderchest");
		}
		
		
		
		
		return false;
	}

}
