package me.survival.listener;

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
		
		
		//Noch nie davor gespielt:
		if(!p.hasPlayedBefore()) {
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 40, 2);
			Bukkit.broadcastMessage(Main.prefix + "§3Der Spieler§a " + p.getName() + " §3ist neu auf dem Server!");
			ItemStack spitzhacke = new ItemBuilder(Material.STONE_PICKAXE).build();
			ItemStack food = new ItemStack(Material.COOKED_FISH, 64);
			ItemStack food2 = new ItemStack(Material.COOKED_FISH, 64);
			ItemStack helm = new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.AQUA).build();
			ItemStack harnisch = new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.AQUA).build();
			ItemStack hose = new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.AQUA).build();
			ItemStack schuhe = new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.AQUA).build();
			
			
			
			p.getInventory().setHelmet(helm);
			p.getInventory().setChestplate(harnisch);
			p.getInventory().setLeggings(hose);
			p.getInventory().setBoots(schuhe);
			p.getInventory().addItem(Sword.getSword(p));
			p.getInventory().addItem(MagicManager.stick);
			p.getInventory().addItem(spitzhacke);
			p.getInventory().addItem(food);
			p.getInventory().addItem(food2);
			p.setLevel(1);
			p.setExp(0.99f);
			p.teleport(new Location(Bukkit.getWorld("Clan"), 0.5, 6, 0.5));
			
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

		int level = vP.getLvl();
		p.setLevel(level);
		
		if((Integer) dV.getObject("maintutorial") == 0) {
			p.teleport(new Location(Bukkit.getWorld("Clan"), 0.5, 6, 0.5));
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
