package me.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_R  extends Command_Msg implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		
		Player p = (Player) cs;
		
		
		if(args.length == 0) {
			p.sendMessage(Main.prefix + "§cDer Befehl lautet: §3/r §8<§3text§8> §8<§3text§8> ...");
			return true;
		}
		
		String t = hmmsg.get(p.getName());
		if(t != null) {
			Player target = Bukkit.getPlayerExact(t);
			if(target != null) {
				
				String msg = "";
				for(int i = 0; i < args.length; i++ ) {
					msg = msg + " " + args[i];
				}
				
				p.sendMessage("§eDu §5-> §e" + t + ": §6" + msg);
				target.sendMessage("§e" + p.getName() +  "§5 -> §e" + "Dir" + ": §6" + msg);
				hmmsg.put(p.getName(), target.getName());	
				hmmsg.put(target.getName(), p.getName());
				
				
			} else {
				p.sendMessage(Main.prefix + "§7Der Spieler §c" + t + " §7ist zurzeit §coffline§7!");
			}
		} else {
			p.sendMessage(Main.prefix + "§cDu hast noch keine Nachricht geschrieben bzw. bekommen!");
		}
		
		
		
		
		
		
		
		
		return false;
	}

	
	
}
