package worldmanager;

import org.bukkit.Bukkit;
import org.bukkit.World;

import me.survival.Main;

public class Mainworld {

	public static void onStart() {
		
		World w = Bukkit.getWorld("Mainworld");
		w.setPVP(false);
		w.setTime(6000);
		w.setThundering(false);
		w.setStorm(false);
		w.setGameRuleValue("doDaylightCycle", "true");
		w.setGameRuleValue("doFireTick", "false");
		w.setGameRuleValue("mobGriefing", "false");
		w.setSpawnLocation(Main.spawn.getBlockX(), Main.spawn.getBlockY(), Main.spawn.getBlockZ());
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
