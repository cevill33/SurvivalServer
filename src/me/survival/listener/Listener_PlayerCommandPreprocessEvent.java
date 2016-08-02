package me.survival.listener;

import me.survival.commands.Command_Spy;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.survival.api.Title;

public class Listener_PlayerCommandPreprocessEvent implements Listener {

	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().toLowerCase();
		if(cmd.startsWith("/pl") || cmd.startsWith("/?") || cmd.startsWith("/buk")) {
			Title title = new Title("", "§4Die meißten Plugins sind selbst programmiert!");
			title.send(e.getPlayer());
			e.setCancelled(true);
		}
		if(cmd.startsWith("/msg")||cmd.startsWith("/tell")||cmd.startsWith("/rules")||cmd.startsWith("/regeln")||cmd.startsWith("/r")||cmd.startsWith("/bewerben")){
			for(int i = 0;i< Command_Spy.spy.size();i++){
				Bukkit.getPlayer(Command_Spy.spy.get(i)).sendMessage("§7" + e.getPlayer() + "§8 ->  §7" + cmd);
			}
		}
	}
}
