package me.survival.commands;

import me.vetoxapi.api.Caster;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.survival.Main;
import me.survival.objects.Level;

public class Command_Stats implements CommandExecutor {

	public static List<UUID> cooldown = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		Player p = (Player) cs;
		
		if(args.length == 0) {
			sendStats(p, p);
			return true;
		}
		
		if(args.length == 1) {
			Player t = Bukkit.getPlayerExact(args[0]);
			if(t != null) {
				sendStats(p, t);
				return true;
			} else {
				p.sendMessage(Main.prefix + "§cDer Spieler ist nicht online!");
				return true;
			}
		}

		p.sendMessage(Main.prefix + "§cSyntax: §7/stats [Spieler]");
		return false;
	}



	public void sendStats(Player getter, Player p) {
		UUID id = p.getUniqueId();
		String UUID = id.toString();
		VetoxPlayer vP = VetoxPlayer.stats.get(id);
		double coins = vP.getCoins();
		getter.sendMessage(Main.prefix + "§3Stats von " + p.getName() + ":");
		getter.sendMessage("   §8- §3Coins:§7 " + MoneyManager.round(coins));
		getter.sendMessage("   §8- §3Level:§7 " + vP.getLvl());
		getter.sendMessage("   §8- §3Exp:§7 " + round(vP.getExp()) + "/" + Level.getLevel(vP.getLvl()).getXp());
		getter.sendMessage("   §8- §3Spielzeit: §7" + vP.getHours() + "Stunden " + vP.getMinutes() + "Minuten");
		getter.sendMessage("   §8- §3Kills: §7" + vP.getKills());
		getter.sendMessage("   §8- §3EXP- Bonus: §a§l+ §7" + vP.getBoost() * 100 + "%");
		int rangpunkte =  500*vP.getLvl() + vP.getHours()*50 + vP.getMinutes() + vP.getKills()*5 + new Double(coins).intValue()*2;
		getter.sendMessage("   §8- §3RangPunkte: §7" + rangpunkte);
		if(!cooldown.contains(p.getUniqueId())) {
			new DBVetoxPlayer(p.getUniqueId().toString()).setObject("rangpoints", rangpunkte);
			cooldown.add(p.getUniqueId());
		}
		return;
	}
	
	public static double round(double number) {
		return Math.round(number*100.0)/100.0;
	}

}
