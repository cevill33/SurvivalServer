package me.survival.api;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SignKlickEvent extends Event {

	public static HandlerList handlers = new HandlerList();
	private Player player;
	private Sign s;
	
	public SignKlickEvent(Player player, Sign s) {
		this.player = player;
		this.s = s;
	}

	@Override
	public HandlerList getHandlers() {
		return SignKlickEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return SignKlickEvent.handlers;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Sign getSign() {
		return this.s;
	}
	
	

}
