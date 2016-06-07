package me.survival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class Listener_AchivmentEvent implements Listener {

	
	@EventHandler
	public void onAchive(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	
	
	
}
