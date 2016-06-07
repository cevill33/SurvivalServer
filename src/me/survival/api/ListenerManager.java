package me.survival.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class ListenerManager {
	
	private  Plugin plugin;
	private Map<String, ListenerBundle> bundles;
	
	public ListenerManager(Plugin plugin) {
		this.bundles = new HashMap<>();
		this.plugin = plugin;
	}
	
	public void register(String name, ListenerBundle bundle) {
		this.bundles.put(name, bundle);
		bundle.get().forEach((listener) -> {
			Bukkit.getPluginManager().registerEvents(listener, this.plugin);
	});
	}
	
	public void unregister(String name) {
		if(this.bundles.containsKey(name)) {
			ListenerBundle bundle = this.bundles.get(name);
			bundle.get().forEach((listener) -> {
				HandlerList.unregisterAll(listener);
			});
		}
		this.bundles.remove(name);
		
	}
	
	
}
