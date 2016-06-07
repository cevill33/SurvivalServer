package me.survival.listener;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class Listener_EntityInteractEvent implements Listener {

	
	@EventHandler
	public void onEntityInteract(EntityInteractEvent e) {
		if (e.getBlock().getType() == Material.SOIL && e.getEntity() instanceof LivingEntity) {
			e.setCancelled(true);
		}
		
	}
	
	
}
