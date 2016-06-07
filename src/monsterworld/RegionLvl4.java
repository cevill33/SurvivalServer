package monsterworld;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.GetRandom;

public class RegionLvl4 {

	
	
	private static List<Location> spawnLoc = new ArrayList<>();
	
	private static String lvl = "§a§lLVL§8:§f 4";
	public static List<Integer> monster = new ArrayList<>();
	
	public static void startSpawning(Main main) {
		spawnLoc.add(new Location(Monsterworld.w, 6, 58, 593));
		spawnLoc.add(new Location(Monsterworld.w, 15, 56, 580));
		spawnLoc.add(new Location(Monsterworld.w, 25, 52, 580));
		spawnLoc.add(new Location(Monsterworld.w, 36, 52, 547));
		spawnLoc.add(new Location(Monsterworld.w, 12, 56, 548));
		spawnLoc.add(new Location(Monsterworld.w, -10, 52, 550));
		spawnLoc.add(new Location(Monsterworld.w, 37, 52, 609));
		spawnLoc.add(new Location(Monsterworld.w, 43, 52, 628));
		spawnLoc.add(new Location(Monsterworld.w, 36, 62, 653.5));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			
			
			@Override
			public void run() {
				for(int i = monster.size(); i < 25; i++) {
					spawnSkeletonSpiders(main);
					
				}
			}
		}, 20*5, 20*30);
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings({ "static-access"})
	private static void spawnSkeletonSpiders(Main main) {
		
		Location loc = spawnLoc.get(GetRandom.returnRandom(0, spawnLoc.size() -1));
		if(!loc.getChunk().isLoaded()) {loc.getChunk().load();}
		
		Spider spider = (Spider) loc.getWorld().spawnEntity(loc, EntityType.SPIDER);
		Skeleton skeleton = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta meta = bow.getItemMeta();
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		bow.setItemMeta(meta);
		skeleton.getEquipment().setItemInHand(bow);
		skeleton.setCustomName(lvl);
		spider.setCustomName(lvl);
		spider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
		skeleton.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
		
		spider.setPassenger(skeleton);
		
		new EntityModifier(skeleton, main).modify().setCanDespawn(false);
		Monsters.monster.put(skeleton.getUniqueId(), Monsters.LVL4);
		
		new EntityModifier(spider, main).modify().setCanDespawn(false);
		Monsters.monster.put(spider.getUniqueId(), Monsters.LVL2);
		monster.add(2);
	}
	

	

	
}

