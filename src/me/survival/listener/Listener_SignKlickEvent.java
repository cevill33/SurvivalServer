package me.survival.listener;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.survival.api.SignKlickEvent;
import me.survival.objects.TravelBallon;

public class Listener_SignKlickEvent implements Listener {
	
	@EventHandler
	public void onSign(SignKlickEvent e) {
		Sign s = e.getSign();
		Player p = e.getPlayer();
		if(s.getLine(0).equals("§3[§eBallon§3]")) {
			TravelBallon.openInventory(p, s.getLine(3));
			return;
		}
		
		
		
		
		
	}
	
	

}
