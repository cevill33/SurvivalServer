package me.survival.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.survival.objects.Sword;
import me.survival.shop.VillagerShop;

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
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
