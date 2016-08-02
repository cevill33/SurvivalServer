package me.survival.listener;

import me.survival.commands.Command_Horse;
import me.survival.commands.Command_Vanish;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.survival.elite.Command_Song;

public class Listener_PlayerQuitEvent implements Listener {
	
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();

		//Song:
		if(Command_Song.song.containsKey(p.getName())) {
			Command_Song.song.get(p.getName()).destroy();
			Command_Song.song.remove(p.getName());
		}

		//TODO: Check if it works!
		//Horse:
		Horse horse = Command_Horse.ridemap.get(p.getName());
		if(horse != null) {
			horse.remove();
			Command_Horse.ridemap.remove(p.getName());
		}
		if(Listener_PlayerInteractEvent.chair.get(p)!=null){
			Listener_PlayerInteractEvent.chair.get(p).remove();
			Listener_PlayerInteractEvent.chair.put(p,null);
		}
		if(Command_Vanish.spectatet.contains(p.getName())){
			Command_Vanish.spectatet.remove(p.getName());
			p.setAllowFlight(false);
		}
		
		
	}

}
