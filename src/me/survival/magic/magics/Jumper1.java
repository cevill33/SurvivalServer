package me.survival.magic.magics;

import java.util.ArrayList;
import java.util.List;

import me.vetoxapi.objects.AntiCheat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.survival.Main;
import me.survival.magic.MagicManager;

public class Jumper1 {

	public static int cooldown = 35;
	private static List<String> cool = new ArrayList<>();
	
	
	public static void fire(Player p) {
		if(cool.contains(p.getName())) {
			p.sendMessage(Main.prefix + "§cDu kannst diesen Zauber nur alle 4 Sekunden ausführen!");
			return;
		}
		
		cool.add(p.getName());
		MagicManager.startLoadinMana(p);
		//Hier Kommt der Code:
		Vector v = p.getLocation().getDirection().multiply(0.8).setY(2.2D);
		p.setVelocity(v);
		AntiCheat.addFlying(p, "jumper");

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*3, 10));
				
			}
		},19);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {

				AntiCheat.removeFlying(p, "jumper");
				cool.remove(p.getName());
				
			}
		},20*4);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

