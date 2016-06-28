package me.survival.listener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener_WheatProtect implements Listener {

	
	
	  @EventHandler
	  public void soilChangePlayer(PlayerInteractEvent event) {
	    if ((event.getAction() == Action.PHYSICAL) && (event.getClickedBlock().getType() == Material.SOIL))
	      event.setCancelled(true);
	  }

	  @EventHandler
	  public void soilChangeEntity(EntityInteractEvent event) {
	    if ((event.getEntityType() != EntityType.PLAYER) && (event.getBlock().getType() == Material.SOIL))
	      event.setCancelled(true);
	  }

	
	
	
}
