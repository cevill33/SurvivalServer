package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;
import me.survival.api.ItemBuilder;

public class RegionLvl2 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 2";
	public static List<Integer> monsterlv2 = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, 52, 8, 115));
		spawnLoc.add(new Location(Monsterworld.w, 62, 8, 144));
		spawnLoc.add(new Location(Monsterworld.w, 61, 8, 174));
		spawnLoc.add(new Location(Monsterworld.w, 36, 8, 196));
		spawnLoc.add(new Location(Monsterworld.w, 5, 8, 190));
		spawnLoc.add(new Location(Monsterworld.w, 7, 8, 170));
		spawnLoc.add(new Location(Monsterworld.w, 23, 8, 134));
		spawnLoc.add(new Location(Monsterworld.w, 31, 8, 108));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				
				while(monsterlv2.size() < 40) {
					int r = GetRandom.returnRandom(1, 2);
					if(r == 1) {
						spawnSkeletonLv2(main);
						continue;
					}
					
					if(r == 2) {
						spawnZombieLv2(main);
					}
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings("static-access")
	private static void spawnZombieLv2(Main main) {
//		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size()));
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		
		
		Zombie z = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		z.setCustomName(lvl);
		z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
		new EntityModifier(z, main).modify().setCanDespawn(false);
		Monsters.monster.put(z.getUniqueId(), Monsters.LVL2);
		monsterlv2.add(1);
	}
	
	
	@SuppressWarnings("static-access")
	private static void spawnSkeletonLv2(Main main) {
//		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size()));
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		
	    Skeleton z = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
		z.setCustomName(lvl);
		z.getEquipment().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.AQUA).build());
		new EntityModifier(z, main).modify().setCanDespawn(false);
		Monsters.monster.put(z.getUniqueId(), Monsters.LVL2);
		monsterlv2.add(1);
	}
	

	
}

