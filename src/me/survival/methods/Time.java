package me.survival.methods;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.objects.Level;

import java.util.UUID;

public class Time {

	public static void startClock(Main main) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					UUID UUID = all.getUniqueId();
					VetoxPlayer vP = VetoxPlayer.stats.get(UUID);
					int minutes = vP.getMinutes();
					int hours = vP.getHours();
					
					if(minutes == 59) {
						vP.setMinutes(0);
						vP.setHours(hours + 1);
						Level.addXp(all, 10);
						
						continue;
					}
					
					vP.setMinutes(minutes + 1);
					
				}
				
				
				
				
				
				
			}
		}, 20*60, 20*60);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
