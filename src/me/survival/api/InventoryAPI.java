package me.survival.api;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryAPI {

	
	public static  HashMap<Integer, ItemStack> getItems(Player player, Material material) {
	
		HashMap<Integer, ItemStack> map = new HashMap<>();
 		for(int i = 0; i < player.getInventory().getContents().length; i++) {
 			ItemStack item = player.getInventory().getItem(i);
			
			if(item != null) {
				if(item.getType() == material) {
					map.put(i, item);
				}
			}
		}
		
		return map;
	}
	
	
	public static  Integer getItemAmmount(Player player, Material material, String displayname) {
		
		int ammount = 0;
 		for(int i = 0; i < player.getInventory().getContents().length; i++) {
 			ItemStack item = player.getInventory().getItem(i);
			
			if(item != null) {
				if(item.getType() == material) {
					if(item.hasItemMeta()) {
						ItemMeta meta = item.getItemMeta();
						if(meta.getDisplayName().equals(displayname)) {
							ammount = ammount + item.getAmount();
						}
					}
					
				}
			}
		}
		
		return ammount;
	}
	
	
	
	
	public static  Integer getItemAmmount(Player player, Material material) {
		
		int ammount = 0;
 		for(int i = 0; i < player.getInventory().getContents().length; i++) {
 			ItemStack item = player.getInventory().getItem(i);
			
			if(item != null) {
				if(item.getType() == material) {
						ammount = ammount + item.getAmount();
						
					}
					
				}
			}
		
		
		return ammount;
	}
}
