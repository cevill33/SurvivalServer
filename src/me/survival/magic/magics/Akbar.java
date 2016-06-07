package me.survival.magic.magics;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.survival.magic.MagicManager;

public class Akbar {
	
	public static int cooldown = 30;
	
	public static void fire(Player p) {
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:
		if(!p.getWorld().getPVP()) {
			for(Entity e : p.getNearbyEntities(4, 4, 4) ) {
				if(e instanceof Player) {
					p.sendMessage(MagicManager.prefix + "§cDu kannst in einer nicht PvP Welt explosionen, nicht in der nähe eines Spielers zünden!");
					return;
				}
			}
			p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
			//p.getWorld().createExplosion(p.getLocation(), 2, false);	
			return;
		}
		p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
		//p.getWorld().createExplosion(p.getLocation(), 2, false);	
	}
	
	
	
}
