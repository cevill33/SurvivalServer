package me.survival.listener;

import me.survival.magic.magics.Pheonix;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.survival.Main;
import me.survival.magic.magics.Bow1;

public class Listener_ProjectileHitEvent implements Listener {
	
	
	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		
		if(e.getEntity() instanceof Arrow){
			Arrow arrow = (Arrow) e.getEntity();
			if(Bow1.arrwos.contains(arrow)) {
				arrow.remove();
				return;
			}

			if(Pheonix.arrows.contains(arrow)) {
				arrow.remove();
			}
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					arrow.remove();

				}
			},20*3);
			
			
			
			
		}
		

		
		
	}
	

}
