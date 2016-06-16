package me.survival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.survival.api.Title;

public class Listner_PlayerCommandPreprocessEvent implements Listener {

	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().toLowerCase();
		if(cmd.startsWith("/pl") || cmd.startsWith("/?") || cmd.startsWith("/buk")) {
			Title title = new Title("", "§4Das meißten Plugins sind selbst programmiert!");
			title.send(e.getPlayer());
			e.setCancelled(true);
		}
	}
	
	
	
	
}
