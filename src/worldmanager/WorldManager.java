package worldmanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;

public class WorldManager {

	static {
		onStart();
	}
	
	public static List<String> protectedworlds = new ArrayList<>();
	public static void makeWorldSave() {
		for(World w : Bukkit.getWorlds()) {
			w.setGameRuleValue("doFireTick", "false");
			w.setGameRuleValue("mobGriefing", "false");
			w.setStorm(false);
			w.setThundering(false);
			
			
			
			
		}
	}
	
	
	
	
	public static void protectWorlds() {
		protectedworlds.add("Clanwar");
		protectedworlds.add("Monsterworld");
		protectedworlds.add("Clan");
	}
	
	public static void onStart() {
		World clan = Bukkit.getWorld("Clan");
		if(clan != null) {
			clan.setPVP(false);
			clan.setDifficulty(Difficulty.PEACEFUL);
			clan.setAmbientSpawnLimit(0);
		}
		
		World farm2 = Bukkit.getWorld("Farmworld2");
		if(farm2  != null) {
			farm2.setPVP(false);
		}
		 
		
	}
	
	
	
	
}
