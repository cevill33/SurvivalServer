package me.survival.magic.magics;

import org.bukkit.Bukkit;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.magic.MagicManager;

public class Fireball1 {

	public static int cooldown = 60;
	
	public static void fire(Player p) {
		MagicManager.startLoadinMana(p);		//Hier Kommt der Code:
		Fireball f = p.launchProjectile(Fireball.class);
		f.setPassenger(p);
		if(!p.getWorld().getPVP()) {
			f.setYield(0);
			p.sendMessage(MagicManager.prefix + "§7Da hier kein §4PvP §7erlaubt ist macht der Feuerball keinen Schaden!");
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				
				if(f != null) {
					f.remove();
				}
				
				
			}
		},20*20);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
