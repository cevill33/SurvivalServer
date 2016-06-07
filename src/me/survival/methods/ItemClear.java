package me.survival.methods;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import me.survival.Main;

public class ItemClear {

	
	public static void onEnable() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(Main.prefix + "§7Herumliegenden Items werden in 30 Sekunden entfernt!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
					@Override
					public void run() {
						
						
						int i = 0;
						for(World worlds : Bukkit.getWorlds()) {
							for(Entity e : worlds.getEntities()) {
								if(e instanceof Item) {
									e.remove();
									i++;
								}
							}
						}
						Bukkit.broadcastMessage(Main.prefix + "§a" + i + " §7Items wurden entfernt!");
						
						
					}
				},20*30);
			}
		}, 20*60*9, 20*60*9);
		
		
		
		
		
		
		
		
	}
	
	
	
}
