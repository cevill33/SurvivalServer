package me.survival.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CaveAchiveEvent extends Event {

	public static HandlerList handlers = new HandlerList();
	
	Player p;
	
	public CaveAchiveEvent(Player p) {
		this.p = p;
	}

	@Override
	public HandlerList getHandlers() {
		return CaveAchiveEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return CaveAchiveEvent.handlers;
	}
	
	public Player getPlayer() {
		return p;
	}

}
