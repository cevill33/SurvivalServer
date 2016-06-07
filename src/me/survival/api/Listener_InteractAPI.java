package me.survival.api;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;


public class Listener_InteractAPI implements Listener {

	
	

	@EventHandler
	public void onInteractAPI(PlayerInteractEvent e) {
		if(e.getClickedBlock() != null) {
		if(e.getClickedBlock().getState() instanceof Sign){
			Sign s = (Sign) e.getClickedBlock().getState();
			Bukkit.getPluginManager().callEvent(new SignKlickEvent(e.getPlayer(), s));
			
		}
	}
	}	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
