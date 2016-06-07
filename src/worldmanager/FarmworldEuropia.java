package worldmanager;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.Title;
import me.survival.api.WorldManager;
import me.survival.objects.Level;

public class FarmworldEuropia {


	
	
	public static void onStart() {
		World w = null;
		for(World worlds : Bukkit.getWorlds()) {
			if(worlds.getName().startsWith("Farmworld1A_")) {
				w = worlds;
			}
		}
		
		
		if(WorldManager.isWorldOnServer(w.getName())) {
			w.setAutoSave(true);
			w.setFullTime(6000);
			w.setGameRuleValue("doDaylightCycle", "false");
			w.setGameRuleValue("doFireTick", "false");
			w.setPVP(true);
			w.setStorm(false);
			w.setThundering(false);
			
			WorldBorder b = w.getWorldBorder();
			b.setCenter(w.getSpawnLocation());
			b.setSize(3000);
			b.setDamageAmount(0.5);
			b.setDamageBuffer(3);
			
			
		}
		
	}
	
	public static void onFarmReset() {
		
		
		
		
	}
	
	
	public static void teleport(Player p) {
		p.teleport(new Location(Bukkit.getWorld("Farmworld1A_1"), -30, 300, 250));
		p.sendMessage(Main.prefix + "§3Reise:");
		p.sendMessage("   §7Kontinent: §fEuropia(Farmworld1A)");
		p.sendMessage("   §7Typ: §fFarmwelt");
		p.sendMessage("   §7PvP: §aAktiviert");
		new Title("§bEuropia", "§7PvP: §aAktiviert").send(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 35, 11));
		Level.addXp(p, 1);
				
		
	}

	
	
	
	
	
}
