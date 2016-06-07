package me.survival.magic.magics;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.magic.MagicManager;

public class Bow1 {

	public static int cooldown = 40;
	public static List<Arrow> arrwos = new ArrayList<>();
	
	public static void fire(Player p) {
		
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:

		final int[] ID = new int[1];
		ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
			int high = 3;
			@Override
			public void run() {
				high--;
				Arrow arr = p.launchProjectile(Arrow.class);
				arr.setShooter(p);
				arr.setVelocity(p.getLocation().getDirection().multiply(4.5));
				arrwos.add(arr);
				if(high <= 0) {
					Bukkit.getScheduler().cancelTask(ID[0]);
				}
				
			}
		}, 0, 10);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
