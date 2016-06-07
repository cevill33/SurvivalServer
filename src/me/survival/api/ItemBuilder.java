package me.survival.api;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {

	ItemStack item;
	
	public ItemBuilder(Material m) {
		this.item = new ItemStack(m);
	}
	
	public ItemBuilder(Material m, int ammount) {
		this.item = new ItemStack(m, ammount);
	}
	
	public ItemBuilder(Material m, int ammount, short data) {
		this.item = new ItemStack(m, ammount, data);
	}
	
	
	public ItemBuilder setDiplayname(String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setUnbreakable(boolean b) {
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(b);
		item.setItemMeta(meta);
		return this;
		
	}
	
	public ItemBuilder setLore(String... lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLoreInArrayList(List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public ItemBuilder setGlassPane(DyeColor color) {
		item = new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLeatherColor(Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addEnchant() {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		item.setItemMeta(meta);
		return this;
		
	}
	
	public ItemBuilder addEnchantment(Enchantment enchant, int amplifyer) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, amplifyer, true);
		item.setItemMeta(meta);
		return this;
		
	}
	
	public ItemBuilder addItemFlag(ItemFlag... flag) {
		ItemMeta meta = item.getItemMeta();
		for(ItemFlag flags : flag) {
			meta.addItemFlags(flags);
		}
		item.setItemMeta(meta);
		return this;
	}
	
	
	public ItemStack build() {
		return this.item;
	}
	
}
