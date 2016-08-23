package me.survival.listener;

import me.survival.usershop.UserShop;
import org.bukkit.Location;
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
		Location l = s.getLocation();
		if(s.getLine(0).equals("§3[§eBallon§3]")) {
			TravelBallon.openInventory(p, s.getLine(3));
			return;
		}
		if(s.getLine(0).equals("§7[§8Shop§7]")){
			if(p.isSneaking()) {
				if (UserShop.isOwner(l, p)) {
					String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + l.getWorld().getName();
					UserShop.openAdminMenue(p,loc);
				}
			}else{
				UserShop.openShop(p,l);
			}
			return;
		}
		
		
		
		
	}
	
	

}
