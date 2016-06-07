package me.survival.magic.magics;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.magic.MagicManager;

public class Speed1 {

	public static int cooldown = 45;
	
	public static void fire(Player p) {
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
		p.playSound(p.getLocation(), Sound.CAT_MEOW, 1, 1);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
