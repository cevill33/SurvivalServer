package me.survival.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skullist {

	public static ItemStack question = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
	static{
		SkullMeta meta = (SkullMeta) question.getItemMeta();
		meta.setOwner("MHF_Question");
		meta.setDisplayName("§aWas ist das?");
		List<String> lore = new ArrayList<>();
		lore.add("§7Hier kannst du deine Items");
		lore.add("§7Enchanten, lege dafür ganz links das Item");
		lore.add("§7hinein welches du Enchanten");
		lore.add("§7willst. In das rechte legst du");
		lore.add("§7eine bestimmte anzahl von §aEnchantern");
		lore.add("§7hinein. Mehr infos hier:");
		lore.add("§a/verzaubern");
		meta.setLore(lore);
		Skullist.question.setItemMeta(meta);
	}

	

	
	
	
	
	
	
}
