package me.survival.methods;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantManager {

	public static final String prefix = "§7[Enchant]";
	@SuppressWarnings("deprecation")
	public static void fireBallEnchant(ItemStack current, Player p) {
		
		if(current.getAmount() != 1) {
			p.sendMessage(prefix + "§cDu musst genau 1 Items reinlegen!");
			return;
		}
		
		int id = current.getTypeId();
		if(id >= 306 && id <= 309) {
			
			
			
			
			
		} else {
			p.sendMessage(prefix + "§cDas linke Item muss eine Rüstungsteil aus Eisen sein!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
