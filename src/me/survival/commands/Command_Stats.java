package me.survival.commands;

import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.UUID;
import me.survival.Main;
import me.survival.objects.Level;

public class Command_Stats implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		Player p = (Player) cs;
		
		if(args.length == 0) {
			UUID id = p.getUniqueId();
			String UUID = id.toString();
			VetoxPlayer vP = VetoxPlayer.stats.get(id);
			p.sendMessage(Main.prefix + "§3Deine Stats: ");
			p.sendMessage("   §8- §3Coins:§7 " + MoneyManager.round(vP.getCoins()));
			p.sendMessage("   §8- §3Level:§7 " + vP.getLvl());
			p.sendMessage("   §8- §3Exp:§7 " + round(vP.getExp()) + "/" + Level.getLevel(vP.getLvl()).getXp());
			p.sendMessage("   §8- §3Spielzeit: §7" + vP.getHours() + "Stunden " + vP.getMinutes() + "Minuten");
			p.sendMessage("   §8- §3Kills: §7" + vP.getKills());
			p.sendMessage("   §8- §3EXP- Bonus: §a§l+ §7" + vP.getBoost() * 100 + "%");
			return true;
			
		}
		
		if(args.length == 1) {
			
			Player t = Bukkit.getPlayerExact(args[0]);
			
			if(t != null) {

				UUID id = t.getUniqueId();
				String UUID = id.toString();
				VetoxPlayer vP = VetoxPlayer.stats.get(id);
				p.sendMessage(Main.prefix + "§3Deine Stats: ");
				p.sendMessage("   §8- §3Coins:§7 " + MoneyManager.round(vP.getCoins()));
				p.sendMessage("   §8- §3Level:§7 " + vP.getLvl());
				p.sendMessage("   §8- §3Exp:§7 " + round(vP.getExp()) + "/" + Level.getLevel(vP.getLvl()).getXp());
				p.sendMessage("   §8- §3Spielzeit: §7" + vP.getHours() + "Stunden " + vP.getMinutes() + "Minuten");
				p.sendMessage("   §8- §3Kills: §7" + vP.getKills());
				p.sendMessage("   §8- §3EXP- Bonus: §a§l+ §7" + vP.getBoost() * 100 + "%");
				return true;
				
				
				
			} else {
				p.sendMessage(Main.prefix + "§cDer Spieler ist nicht online!");
				return true;
			}
			
			
			
			
		}
		
		
		p.sendMessage(Main.prefix + "§cSyntax: §7/stats [Spieler]");
		
		
		
		
		
		
		
		
		return false;
	}
	
	public static double round(double number) {
		return Math.round(number*100.0)/100.0;
	}

}
