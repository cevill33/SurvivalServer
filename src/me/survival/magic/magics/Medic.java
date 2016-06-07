package me.survival.magic.magics;


import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.magic.MagicManager;

public class Medic {

	public static int cooldown = 80;
	
	@SuppressWarnings({ "deprecation"})
	public static void fire(Player p) {
		
		final int[] ID = new int[1];
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:

		p.sendMessage(Main.prefix + "§7Ein Heilpacket ist unterwegst.");
		Location loc = p.getTargetBlock((Set<Material>) null, 100).getLocation();
		loc.setY(255);
		
		FallingBlock block = p.getWorld().spawnFallingBlock(loc, 35, (byte) 14);
		
		
		
		ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				i++;
				
				if(i > 17) {
					Bukkit.getScheduler().cancelTask(ID[0]);
					p.sendMessage(Main.prefix + "§7Heilung beendet!");
					block.getLocation().getBlock().setType(Material.AIR);
					return;
				}
				
				
				
				for(Entity e : block.getNearbyEntities(3, 3, 3)) {
					
					if(e instanceof Player ) {
						Player players = (Player) e;
						double healt = players.getHealth();
						players.playEffect(block.getLocation(), Effect.HEART, 1);
						players.playSound(p.getLocation(), Sound.CAT_PURR, 1, 1);
						if(healt < 20) {
							players.setHealth(healt + 1);
						}
						
						
					}
					
				}
				
			}
		}, 20, 20);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
