package me.survival.methods;

import java.util.HashMap;
import java.util.UUID;

import me.survival.magic.MagicManager;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.api.ItemBuilder;

public class InventoryLock {

	private static int konstant = 2;
	public static HashMap<String, ItemStack[]> inv = new HashMap<>();
	public static HashMap<String, ItemStack[]> armor = new HashMap<>();
	
	public static void openInv(Player p) {
		if(p == null) {
			return;
		}
		
		Inventory inventory = p.getServer().createInventory(null, 9, "§aWillst du deine Sachen wieder?");
		int price = (int) (MoneyManager.getMoney(p.getUniqueId()) / 10) * konstant;
		inventory.setItem(0, new ItemBuilder(Material.EMERALD_BLOCK).setDiplayname("§aJa").setLore("Kosten: §3" + price + " Coins").build());
		inventory.setItem(1, new ItemBuilder(Material.REDSTONE_BLOCK).setDiplayname("§4Nein!").build());
		inventory.setItem(8, new ItemBuilder(Material.BOOK).setDiplayname("§aWas ist das?").
					setLore("§7Da du gestorben bist kannst du", "§7nun entweder " + konstant + "0%", "§7deines Geldes zahlen oder", "§7auf Nein drücken!" ).build());
		p.openInventory(inventory);
	}
	
	public static void onDeny(Player p) {
		if(inv.containsKey(p.getName())) {
			inv.remove(p.getName());
		}
		p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
		p.closeInventory();
		p.setLevel(VetoxPlayer.stats.get(p.getUniqueId()).getLvl());
		p.setExp(0.99f);
		MagicManager.mana.put(p.getName(), 100);
	}
	
	public static void onAccept(Player p) {
		ItemStack[] inven = inv.get(p.getName());
		if(inven != null) {
			
			p.getInventory().setArmorContents(armor.get(p.getName()));
			p.getInventory().setContents(inven);
			inv.remove(p.getName());
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.CAT_MEOW, 1, 1);
			UUID UUID = p.getUniqueId();
			int price = (int) (MoneyManager.getMoney(UUID) / 10) * konstant;
			MoneyManager.removeMoney(UUID, price);
			p.setLevel(VetoxPlayer.stats.get(p.getUniqueId()).getLvl());
			p.setExp(0.99f);
			MagicManager.mana.put(p.getName(), 100);
			return;
		}
		p.closeInventory();
		p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
		p.setLevel(VetoxPlayer.stats.get(p.getUniqueId()).getLvl());
		p.setExp(0.99f);
		MagicManager.mana.put(p.getName(), 100);
		
	}
	
	
	
}
