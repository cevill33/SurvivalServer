package me.survival.magic.magics;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import me.survival.magic.MagicManager;

public class Blitz1 {

	public static int cooldown = 40;
	
	public static void fire(Player p) {
		//Hier Kommt der Code:
		if(!p.getWorld().getPVP()) {
			p.sendMessage(MagicManager.prefix + "§cIn dieser Welt ist PvP verboten!");
			return;
		}
		
		MagicManager.startLoadinMana(p);
		Block block = p.getTargetBlock((Set<Material>) null, 100);
		if(block != null) {
			block.getWorld().strikeLightning(block.getLocation());
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
