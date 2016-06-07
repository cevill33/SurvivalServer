package me.survival.magic.magics;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.survival.magic.MagicManager;

public class Healer1 {

	public static int cooldown = 50;
	
	public static void fire(Player p) {
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 2));
		p.setFoodLevel(p.getFoodLevel() + 8);
		p.sendMessage(MagicManager.prefix + "§7Magic...");
		p.playSound(p.getLocation(), Sound.EAT, 1, 1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
