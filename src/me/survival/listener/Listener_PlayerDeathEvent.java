package me.survival.listener;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.survival.Main;
import me.survival.methods.InventoryLock;

import java.util.UUID;

public class Listener_PlayerDeathEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		UUID id = p.getUniqueId();
		VetoxPlayer vP = VetoxPlayer.stats.get(id);
		vP.setDeaths(vP.getDeaths() + 1);
		InventoryLock.inv.put(p.getName(), p.getInventory().getContents());
		InventoryLock.armor.put(p.getName(), p.getInventory().getArmorContents());
		e.setDeathMessage("");
		e.getDrops().clear();
		e.setDroppedExp(0);
		
		if(p.getKiller() instanceof Player) {
			Player killer = p.getKiller();
			killer.sendMessage(Main.prefix + "§7Du hast den Spieler §a" + p.getDisplayName() + " §7gekillt!");
			VetoxPlayer vK = VetoxPlayer.stats.get(killer.getUniqueId());
			vK.setKills(vK.getKills() + 1);
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
