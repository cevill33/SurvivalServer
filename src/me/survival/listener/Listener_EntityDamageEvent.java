package me.survival.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.survival.commands.Command_Horse;
import org.bukkit.event.entity.EntityDamageEvent;

public class Listener_EntityDamageEvent implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {


		if(e.getDamager() instanceof Player) {
			
			Entity entity = e.getEntity();
			
			if(e.getEntityType().equals(EntityType.HORSE)) {
				Horse h = (Horse) entity;
				if(Command_Horse.ridemap.containsValue(h)) {
					e.setCancelled(true);
				}
			}

		}
	}
	
	

}
