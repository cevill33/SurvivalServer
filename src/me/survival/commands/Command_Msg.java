package me.survival.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_Msg implements CommandExecutor {

	public static HashMap<String, String> hmmsg = new HashMap<>(); 

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("tell") || cmd.getName().equalsIgnoreCase("m")) {
			Player p = (Player) cs;
			if(args.length < 2) {
				p.sendMessage(Main.prefix + "§cDer Befehl lautet: §3/msg §8<§3Spieler§8> §8<§3text§8> §8<§3text§8> ...");
				return true;
				
			}
			
			Player target = Bukkit.getPlayerExact(args[0]);
			if(target == null) {
				p.sendMessage(Main.prefix + "§cDieser Spieler ist zurzeit nicht online!");
				return true;
			}
			
			String msg = "";
			for(int i = 1; i < args.length; i++ ) {
				msg = msg + " " + args[i];
				
			}
			
				
			
			p.sendMessage("§eDu §5-> §e" + args[0] + ": §6" + msg);
			target.sendMessage("§e" + p.getName() +  "§5 -> §e" + "Dir" + ": §6" + msg);
			hmmsg.put(p.getName(), target.getName());	
			hmmsg.put(target.getName(), p.getName());
		
		}
		
		
		return false;
	}

	
	
	
}
