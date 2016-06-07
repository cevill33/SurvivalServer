package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;

public class RegionLvl6 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 6";
	public static List<Integer> monster = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, -113, 76, 879));
		spawnLoc.add(new Location(Monsterworld.w, -123, 78, 864));
		spawnLoc.add(new Location(Monsterworld.w, -142, 94, 853));
		spawnLoc.add(new Location(Monsterworld.w, -155, 104, 841));
		spawnLoc.add(new Location(Monsterworld.w, -167, 110, 835));
		spawnLoc.add(new Location(Monsterworld.w, -182, 116, 834));
		spawnLoc.add(new Location(Monsterworld.w, -199, 127, 851));
		
		spawnLoc.add(new Location(Monsterworld.w, -129, 83, 834));
		spawnLoc.add(new Location(Monsterworld.w, -143, 87, 812));
		spawnLoc.add(new Location(Monsterworld.w, -160, 85, 799));
		spawnLoc.add(new Location(Monsterworld.w, -231, 96, 829));
		spawnLoc.add(new Location(Monsterworld.w, -232, 91, 891));
		spawnLoc.add(new Location(Monsterworld.w, -183, 85, 907));
		spawnLoc.add(new Location(Monsterworld.w, -155, 85, 89));
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				for(int i = monster.size(); i < 20; i++) {
					spawnWhitherSkeletonLohe(main);
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings({ "static-access"})
	private static void spawnWhitherSkeletonLohe(Main main) {
		
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		Skeleton wither = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
		wither.setSkeletonType(SkeletonType.WITHER);
		
		wither.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
		wither.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		wither.setCustomName(lvl);
		
		Blaze blaze = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
		blaze.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
		blaze.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
		blaze.setCustomName(lvl);
		
		wither.setPassenger(blaze);
		
		
		new EntityModifier(blaze, main).modify().setCanDespawn(false);
		new EntityModifier(wither, main).modify().setCanDespawn(false);
		Monsters.monster.put(wither.getUniqueId(), Monsters.LVL6);
		Monsters.monster.put(blaze.getUniqueId(), Monsters.LVL6);
		monster.add(2);
	}
	

	

	
}

