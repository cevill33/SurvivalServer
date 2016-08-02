package me.survival.commands;

import me.survival.Main;
import me.survival.npc.MessageThread;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.survival.methods.Tutorial;

public class Command_Tutorial implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) cs;
		if(args.length == 0) {
			p.teleport(new Location(Bukkit.getWorld("Clan"), -293, 5, 464));
			return true;
		}

		
		p.sendMessage(Main.prefix + "§cSyntax: §7/tutorial!");
		return false;
	}	
	
}