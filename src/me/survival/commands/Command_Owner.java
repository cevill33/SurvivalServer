package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Owner implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		@SuppressWarnings("unused")
		Player p = (Player) cs;
		if(args.length == 0) {
			return true;
		}
		
		
		
		
		
		return false;
	}

}
