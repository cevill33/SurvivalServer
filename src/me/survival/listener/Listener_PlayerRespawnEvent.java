package me.survival.listener;

import me.survival.magic.MagicManager;
import me.survival.methods.NickNamer;
import me.survival.nation.Nation;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import me.survival.Main;
import me.survival.methods.InventoryLock;

import java.util.UUID;

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
					if(NickNamer.changeing.contains(p.getName())) {
						NickNamer.changeing.remove(p.getName());
						ItemStack[] inven = InventoryLock.inv.get(p.getName());
						if(inven != null) {
							p.getInventory().setArmorContents(InventoryLock.armor.get(p.getName()));
							p.getInventory().setContents(inven);
							InventoryLock.inv.remove(p.getName());
							p.closeInventory();
							p.setLevel(VetoxPlayer.stats.get(p.getUniqueId()).getLvl());
							p.setExp(0.99f);
							MagicManager.mana.put(p.getName(), 100);
							return;
						}
						p.closeInventory();
						p.setLevel(VetoxPlayer.stats.get(p.getUniqueId()).getLvl());
						p.setExp(0.99f);
						MagicManager.mana.put(p.getName(), 100);
						return;
					}
					if(Nation.respawn.contains(p.getName())) {
						Nation.respawn.remove(p.getName());
						return;
					}
					InventoryLock.openInv(p);
					return;
					
				}
			},10);

		}
		
	}
	
	
	
	
	
	
	
	
	
}
