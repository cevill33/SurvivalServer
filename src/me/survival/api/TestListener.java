package me.survival.api;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.survival.Main;


public class TestListener {

	Listener listener;
	
	public TestListener(Listener l, Main main) {
		Bukkit.getServer().getPluginManager().registerEvents(l, main);
		this.listener = l;
	}
	
	
}
