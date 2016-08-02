package me.survival.listener;

import me.survival.commands.Command_Vanish;
import me.survival.methods.Kit;
import me.survival.nation.Nation;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.api.Title;
import me.survival.magic.MagicManager;
import me.survival.methods.InventoryLock;
import me.survival.objects.Sword;

import java.util.UUID;

public class Listener_PlayerJoinEvent implements Listener {

	
	public Listener_PlayerJoinEvent(Main main) {
		
	}
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
		Title title = new Title("§aWilkommen auf", "§3VetoxMc.de", 3, 18,6);
		title.setTimingsToTicks();
		title.send(p);
		for(Player all : Bukkit.getOnlinePlayers()){
			p.showPlayer(all);
		}
		for(int i = 0;i<Command_Vanish.spectatet.size();i++){
			p.hidePlayer(Bukkit.getPlayer(Command_Vanish.spectatet.get(i)));
		}
		
		//Noch nie davor gespielt...:
		if(!p.hasPlayedBefore()) {
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 40, 2);
			Bukkit.broadcastMessage(Main.prefix + "§3Der Spieler§a " + p.getName() + " §3ist neu auf dem Server!");
			Kit.standartKit(p);
			p.setLevel(1);
			p.setExp(0.99f);
			p.teleport(new Location(Bukkit.getWorld("Clan"), -293, 5, 464));
			return;
		}
		

		DBVetoxPlayer dV = new DBVetoxPlayer(id.toString());
		VetoxPlayer vP = VetoxPlayer.stats.get(id);
		p.teleport(dV.getLocation("lastlocation"));

		
		if(p.hasPermission("vetox.boost.50")) {
			vP.setBoost(vP.getBoost() + 0.5);
		}
		if(p.hasPermission("vetox.boost.100")) {
			vP.setBoost(vP.getBoost() + 1);
		}

		if(vP.getNation() != null) {
			//Nation.sendNationName(p, Nation.findByString(vP.getNation()));
		}

		int level = vP.getLvl();
		p.setLevel(level);
		
		if((Integer) dV.getObject("maintutorial") == 0) {
			p.teleport(new Location(Bukkit.getWorld("Clan"), -293, 5, 464));
		}

		if(level == 1) { p.sendMessage(Main.prefix + "§5Wenn du dich nicht auskennst: §a/hilfe§5!"); }
		if(level == 3) { p.sendMessage(Main.prefix + "§5Wenn du dich nicht auskennst: §a/hilfe§5!"); }	
		if(level == 8) { p.sendMessage(Main.prefix + "§5Wenn du dich nicht auskennst: §a/hilfe§5!"); }	
		p.setExp(0.99f);
		
		
		ItemStack[] inv = InventoryLock.inv.get(p.getName());
		if(inv != null) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main	, new Runnable() {
				
				@Override
				public void run() {
					
					InventoryLock.openInv(p);
					return;
					
				}
			},10);

		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

}
