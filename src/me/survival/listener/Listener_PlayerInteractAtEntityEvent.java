package me.survival.listener;

import chunkgs.ChunkManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.survival.objects.Sword;
import me.survival.shop.VillagerShop;
import worldmanager.GsAllowedWorld;

public class Listener_PlayerInteractAtEntityEvent implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		if(entity.getCustomName() == null) return;
		String name = entity.getCustomName();

		
		if(name.equals("§7Schmied")) {
			e.setCancelled(true);
			Sword.openInventory(p);
		}
		
		if(name.startsWith("§a§lShop§7: ")) {
			e.setCancelled(true);
			System.out.println(name.substring(12));
			VillagerShop.openInventory(p, name.substring(12));
			
		}
		//Amorstand
		if(entity.getType().equals(EntityType.ARMOR_STAND) || entity.getType().equals(EntityType.MINECART_CHEST) || entity.getType().equals(EntityType.MINECART)){
			if(!GsAllowedWorld.worlds.containsKey(p.getWorld().getName()) && !worldmanager.WorldManager.protectedworlds.contains(p.getWorld().getName()))e.setCancelled(true);

			if(!ChunkManager.isForHimBuildable(p.getUniqueId().toString(), entity.getLocation())) e.setCancelled(true);
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
