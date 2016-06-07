package me.survival.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import me.survival.commands.Command_Horse;

public class Listener_InventoryOpenEvent implements Listener{
	
	@EventHandler
	public void onOpenInv(InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		if(Command_Horse.ridemap.containsKey(p.getName())) {
			e.setCancelled(true);
			p.closeInventory();
		}
	}

}
