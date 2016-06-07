package me.survival.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

public class ListenerBundle {

	private List<Listener> listeners;
	
	public ListenerBundle() {
		this.listeners = new ArrayList<Listener>();
	}
	
	public void add(Listener l) {
		this.listeners.add(l);
	}
	
	public List<Listener> get() {
		return listeners;
	}
	
	
}
