package me.survival.magic.magics;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.magic.MagicManager;

public class CobWeb1 {

	public static int cooldown = 35;
	
	public static void fire(Player p) {
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:

		if(p.getLocation().getBlock().getType().equals(Material.WATER)){//TODO wenn man in den boden schaut <==
			return;
		}
		@SuppressWarnings("deprecation")
		FallingBlock block = p.getWorld().spawnFallingBlock(p.getLocation().add(0,1,0) ,Material.WEB, (byte) 1);
		block.setVelocity(p.getLocation().getDirection().multiply(2D));
		p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 3F, 1);

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				if(block.getLocation().getBlock().getType().equals(Material.WEB)) {
					if(block.getLocation().getBlock().getType().equals(Material.AIR)) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {

							@Override
							public void run() {
								if(block.getLocation().getBlock().getType().equals(Material.WEB)) {
									if(block.getLocation().getBlock().getType().equals(Material.AIR)) {
										Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {

											@Override
											public void run() {
												if(block.getLocation().getBlock().getType().equals(Material.WEB)) {
													if(block.getLocation().getBlock().getType().equals(Material.AIR)) {
														Bukkit.broadcastMessage("§c" + p.getName() + "hat einen Bugg im Gadget Spider gefunden. Bitte einem Developer bescheidgeben!");
													}else{
														block.getLocation().getBlock().setType(Material.AIR);
													}
												}

											}
										}, 20*4);
									}else{
										block.getLocation().getBlock().setType(Material.AIR);
									}
								}

							}
						}, 20*4);
					}else{
						block.getLocation().getBlock().setType(Material.AIR);
					}
				}
				
			}
		}, 20*4);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
