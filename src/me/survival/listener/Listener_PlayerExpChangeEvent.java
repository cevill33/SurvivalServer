package me.survival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import me.survival.objects.Level;

public class Listener_PlayerExpChangeEvent implements Listener{

	@EventHandler
	public static void onXp(PlayerExpChangeEvent e) {
		e.setAmount(0);
		Level.addXp(e.getPlayer(), e.getAmount());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
