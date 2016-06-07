package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;

public class RegionLvl5 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 5";
	public static List<Integer> monster = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, 12, 50, 847));
		spawnLoc.add(new Location(Monsterworld.w, 46, 50, 860));
		spawnLoc.add(new Location(Monsterworld.w, 49, 50, 779));
		spawnLoc.add(new Location(Monsterworld.w, 35, 50, 758));
		spawnLoc.add(new Location(Monsterworld.w, 37, 50, 835));
		spawnLoc.add(new Location(Monsterworld.w, 9, 50, 864));
		spawnLoc.add(new Location(Monsterworld.w, 23, 50, 845));
		spawnLoc.add(new Location(Monsterworld.w, 46, 50, 802));
		spawnLoc.add(new Location(Monsterworld.w, 34, 51, 822));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				for(int i = monster.size(); i < 14; i++) {
					spawnZombieSlime(main);
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings({ "static-access"})
	private static void spawnZombieSlime(Main main) {
		
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		Zombie wolf = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		wolf.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
		wolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		wolf.setBaby(false);
		
		Slime slime = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
		slime.setSize(2);
		slime.setCustomName(lvl);
		slime.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 100));
		wolf.setPassenger(slime);
		
		
		new EntityModifier(wolf, main).modify().setCanDespawn(false);
		new EntityModifier(slime, main).modify().setNoAI(true);
		Monsters.monster.put(wolf.getUniqueId(), Monsters.LVL5);
		monster.add(1);
	}
	

	

	
}

