package me.survival.methods;

import java.util.Map;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.survival.Main;

public class RepairManager {
	
	public static void onAmbosKlick(Player p) {
		System.out.println("Ambos Klick");
		int lvl = VetoxPlayer.stats.get(p.getUniqueId()).getLvl();
		
		if(lvl < 12) {
			p.sendMessage(Main.prefix + "§fItem + SmaragdBlock + Rechtsklick auf leeres K§stchen = Repariertes Item");			
		}
		p.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL, "§aAmboss"));
		
		
		
	}
	
	
	public static void onDrop(Inventory inv) {
	
		if(inv.getItem(1) != null && inv.getItem(1).getType().equals(Material.EMERALD_BLOCK)) {
			if(inv.getItem(0) != null) {
				
				System.out.println("NOW");
				ItemStack one = inv.getItem(0);
				ItemStack stack = new ItemStack(one.getType(), one.getAmount());
				if(one.hasItemMeta()) {
					ItemMeta meta = stack.getItemMeta();
					Map<Enchantment, Integer> enchants = one.getItemMeta().getEnchants();
					for(Enchantment ench : enchants.keySet()) {
						meta.addEnchant(ench, enchants.get(ench), true);
					}
					stack.setItemMeta(meta);
				}
				
				
				inv.setItem(2, stack);
				
			}
		}
	}
	
	public static void onEndClick(Player p, Inventory inv) {
		
		if(inv.getItem(2) == null) {
			return;
		}
		
		if(inv.getItem(1) != null && inv.getItem(1).getType().equals(Material.EMERALD_BLOCK)) {
			if(inv.getItem(0) != null) {
		
				ItemStack one = inv.getItem(0);
				ItemStack stack = new ItemStack(one.getType(), one.getAmount());
				if(one.hasItemMeta()) {
					ItemMeta meta = stack.getItemMeta();
					Map<Enchantment, Integer> enchants = one.getItemMeta().getEnchants();
					for(Enchantment ench : enchants.keySet()) {
						meta.addEnchant(ench, enchants.get(ench), true);
					}
					stack.setItemMeta(meta);
				}
				inv.setItem(0, null);
				inv.setItem(1, null);
				p.getInventory().addItem(stack);
				p.closeInventory();
				
			}
		}
		
		
	}
	
	
	
	
	
	

}
