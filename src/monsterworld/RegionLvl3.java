package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;

public class RegionLvl3 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 3";
	public static List<Integer> monster = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, 3, 15, 227));
		spawnLoc.add(new Location(Monsterworld.w, -22, 14, 235));
		spawnLoc.add(new Location(Monsterworld.w, -34, 14, 261));
		spawnLoc.add(new Location(Monsterworld.w, -5, 17, 277));
		spawnLoc.add(new Location(Monsterworld.w, -24, 14, 298));
		spawnLoc.add(new Location(Monsterworld.w, -5, 23, 326));
		spawnLoc.add(new Location(Monsterworld.w, -15, 28, 354));
		spawnLoc.add(new Location(Monsterworld.w, -22, 28, 368));
		spawnLoc.add(new Location(Monsterworld.w, 33, 26, 317));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				while(monster.size() < 30) {
					int r = GetRandom.returnRandom(1, 2);
					if(r == 1) {
						spawnPigmans(main);
						continue;
					}
					
					if(r == 2) {
						spawnZombie(main);
					}
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings("static-access")
	private static void spawnZombie(Main main) {
		
//		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size()));
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		
		Zombie z = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		z.setCustomName(lvl);
		z.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
		z.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		z.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
		new EntityModifier(z, main).modify().setCanDespawn(false);
		Monsters.monster.put(z.getUniqueId(), Monsters.LVL3);
		monster.add(1);
	}
	
	
	@SuppressWarnings("static-access")
	private static void spawnPigmans(Main main) {
		
//		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size()));
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		
	    PigZombie z = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
		z.setCustomName(lvl);
		z.setAngry(true);
		new EntityModifier(z, main).modify().setCanDespawn(false);
		Monsters.monster.put(z.getUniqueId(), Monsters.LVL3);
		monster.add(1);
	}
	

	
}

