package me.survival.listener;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import me.survival.Main;
import me.survival.methods.InventoryLock;

public class Listener_PlayerRespawnEvent implements Listener {

	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();

		//if(War.inPlayerWar.containsKey(p.getName())) {
		//	return;
		//}

		VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
		p.setLevel(vP.getLvl());
		p.setExp(0.99f);
		e.setRespawnLocation(Main.spawn);
		ItemStack[] inv = InventoryLock.inv.get(p.getName());
		
		if(inv != null) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main	, new Runnable() {
				
				@Override
				public void run() {
					
					InventoryLock.openInv(p);
					return;
					
				}
			},10);

		}
		
	}
	
	
	
	
	
	
	
	
	
}
