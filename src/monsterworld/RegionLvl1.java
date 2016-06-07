package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;
import me.survival.api.ItemBuilder;

public class RegionLvl1 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 1";
	public static List<Integer> monsterlv1 = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, 58, 5, 19));
		spawnLoc.add(new Location(Monsterworld.w, 24, 5, 22));
		spawnLoc.add(new Location(Monsterworld.w, 30, 5, 39));
		spawnLoc.add(new Location(Monsterworld.w, 29, 6, 55));
		spawnLoc.add(new Location(Monsterworld.w, 37, 5, 68));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				while(monsterlv1.size() < 15) {
					spawnMiniZombieLv1(main);
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("static-access")
	private static void spawnMiniZombieLv1(Main main) {
		
//		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size()));
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		Zombie z = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		z.setBaby(true);
		z.getEquipment().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.OLIVE).build());
		z.setCustomName(lvl);
		z.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
		new EntityModifier(z, main).modify().setCanDespawn(false);
		Monsters.monster.put(z.getUniqueId(), Monsters.LVL1);
		monsterlv1.add(1);
	}
	

	
}
