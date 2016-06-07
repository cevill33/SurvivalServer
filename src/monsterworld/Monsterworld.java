package monsterworld;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Monsterworld {

	public static World w = Bukkit.getWorld("Monsterworld");
	
	public static void onStart() {
		w.setFullTime(16000);
		w.setGameRuleValue("doDaylightCycle", "false");
		w.setPVP(false);
		w.setStorm(false);
		w.setThundering(true);
	}
	
	
	public static void onStop() {
		Monsters.deleteMobs();
		
		
	}
	
	
}
