package me.survival.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.mongodb.QuestPlayer;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.ActionBar;

public class Command_Spawn implements CommandExecutor{

	private Main main;

	public Command_Spawn(Main main) {
		this.main = main;
	}
	
	
	private static HashMap<String, Location> map = new HashMap<>();
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if((Integer) new DBVetoxPlayer(p.getUniqueId().toString()).getObject("maintutorial") == 0 || new QuestPlayer(p.getUniqueId().toString()).getDocument() == null) {
			p.sendMessage(Main.prefix + "§cDu musst zuerst die anfangs Quest machen --> /tutorial!");
			return true;
		}
		
		
		
		
		if(map.containsKey(p.getName())) {
			
			return true;
		}
		List<Integer> high = new LinkedList<>();
		high.add(6);
		if(args.length == 0) {
			if(p.hasPermission("vetox.fastspawnteleport")) {
				high.clear();
				high.add(4);
			}
			
			
				
				
			p.sendMessage(Main.prefix + "§7Bewege dich für §6" + (high.get(0) -1) + " §7Sekunden nicht!");
			int lvl = VetoxPlayer.stats.get(p.getUniqueId()).getLvl();
			if(lvl == 3) {
				if(!p.hasPermission("vetox.fastspawnteleport"))
				p.sendMessage(Main.prefix + "§7Mit §6Elite §7oder §aTitan §7braucht die Teleportation nur 3 Sekunden!");
			}
			map.put(p.getName(), p.getLocation());
			final int[] ID = new int[1];
			ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
				@Override
				public void run() {
					if(high.get(0) == 0) {
						try{
						p.teleport(Main.spawn);
						ActionBar.sendActionBar(p, "§3Du wurdest zum §fSpawn §3teleportiert!");
						Bukkit.getScheduler().cancelTask(ID[0]);
						map.remove(p.getName());
						return; } catch(Exception ex) {
							Main.spawn.getChunk().load();
							p.sendMessage(Main.prefix + "§cError: Der Spawn konnte nicht geladen werden.");
							p.sendMessage(Main.prefix + "§cGebe /spawn bitte erneut ein!");
							return;
						}
					}
					
					
					if(p.getLocation().getBlockX() != map.get(p.getName()).getBlockX() || p.getLocation().getBlockZ() != map.get(p.getName()).getBlockZ()) {
						Bukkit.getScheduler().cancelTask(ID[0]);
						ActionBar.sendActionBar(p, "§4Teleportation abgebrochen!");
						map.remove(p.getName());
						return;
					}
					
					final int next = high.get(0) -1;
					high.clear();
					high.add(next);
					ActionBar.sendActionBar(p,"§a" +  high.get(0));
					
					
					}
				}, 20, 20);
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/spawn!");
		}
		return false;
	}

}
