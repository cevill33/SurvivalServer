package me.survival.listener;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

import me.survival.Main;
import me.survival.commands.Command_Horse;

public class Listener_VehileExitEvent implements Listener {

	
	@EventHandler
	public void onLeaveVehile(VehicleExitEvent e) {
		System.out.println("Exited");
		if(e.getExited() instanceof Player) {
			Player p = (Player) e.getExited();
			if(e.getVehicle() instanceof Horse) {
				 if(Command_Horse.ridemap.containsKey(p.getName())) {
					 e.getVehicle().remove();
					 p.sendMessage(Main.prefix + "§7Du hast das Reittier verlassen!");
					 Command_Horse.ridemap.remove(p.getName());
				 }
				
				
			}
			
			
			
		}
	}
	
	
	
	
	
	
	
	
}
