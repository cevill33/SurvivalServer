package me.survival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class Listener_ExplosionPrimeEvent implements Listener {

	@EventHandler
	public void onExplode(ExplosionPrimeEvent e) {
		e.setFire(false);
	}
	
	
}
