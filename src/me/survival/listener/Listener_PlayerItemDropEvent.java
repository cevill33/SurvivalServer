package me.survival.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.survival.objects.Sword;

public class Listener_PlayerItemDropEvent implements Listener {

	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(e.getItemDrop().getItemStack().hasItemMeta()) {
			if(e.getItemDrop().getItemStack().getItemMeta().hasDisplayName()) {
				if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().startsWith("§f§6")) {
					p.sendMessage(Sword.prefix + "§cDu kannst dein Schwert nicht droppen!");
					p.sendMessage(Sword.prefix + "§aTipp: §7Wenn du es löschen willst gebe §a/sword delete §7ein!");
					e.setCancelled(true);
				}
			}
		}
		
		
		
		
	}
	
	
	
	
}
