package me.survival.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.api.Skullist;
import me.survival.methods.VetoxRecipes;

public class BetterEnchant {
	public static final String prefix = "§aEnchant: ";
	private static final List<String> cooldown = new ArrayList<>();
	public static HashMap<String, BetterEnchant> betterenchants = new HashMap<>();
	static {
		new BetterEnchant("Schutz",Arrays.asList("§7Reduziert Schaden."), Enchantment.PROTECTION_ENVIRONMENTAL, 0, 8, Material.IRON_CHESTPLATE);
		new BetterEnchant("Haltbarkeit",Arrays.asList("§7Erhöht die Haltbarkeit."), Enchantment.DURABILITY, 13, 20, Material.BOOKSHELF);
		new BetterEnchant("Federfall",Arrays.asList("§7Reduziert den Fallschaden."), Enchantment.PROTECTION_FALL, 8, 5, Material.GOLD_BOOTS);
		new BetterEnchant("Feuerschutz",Arrays.asList("§7Reduziert den Schaden an Feuer."), Enchantment.PROTECTION_FIRE, 18, 6, Material.IRON_CHESTPLATE);
		new BetterEnchant("Explosionsschutz",Arrays.asList("§7Reduziert den Schaden an von Explosionen."), Enchantment.PROTECTION_EXPLOSIONS, 19, 5, Material.IRON_CHESTPLATE);
		new BetterEnchant("Schussicher",Arrays.asList("§7Reduziert den Schaden ", "§7von Projektilen(z.B.: Pfeile)."), Enchantment.PROTECTION_PROJECTILE, 10, 5, Material.IRON_CHESTPLATE);
		new BetterEnchant("Atmung",Arrays.asList("§7Damit kannst du unterwasser,", "§7länger atmen(Für Helm.)"), Enchantment.OXYGEN, 7, 5, Material.GOLD_HELMET);
		new BetterEnchant("Wasseraffinität",Arrays.asList("§7Lässt dich unterwasser ", "§7schneller abbauen."), Enchantment.WATER_WORKER, 6, 3, Material.GOLD_HELMET);
		new BetterEnchant("Dornen",Arrays.asList("§7Der Gegner bekommt auf den", "§7schlag auf deine Rüsstung schaden!"), Enchantment.THORNS, 9, 1, Material.IRON_CHESTPLATE);
		new BetterEnchant("Effizienz",Arrays.asList("§7Lässt Blöcke schneller abbauen(Für Spitzhacke)!"), Enchantment.DIG_SPEED, 14, 8, Material.IRON_PICKAXE);
		new BetterEnchant("Glück",Arrays.asList("§7Erhöht die Chance das mehr ", "§7Items aus einen Block droppen!"), Enchantment.LOOT_BONUS_BLOCKS, 12, 3, Material.IRON_PICKAXE);
		new BetterEnchant("Stärke",Arrays.asList("§7Erhöht den Pfeilschaden!"), Enchantment.ARROW_DAMAGE, 24, 6, Material.BOW);
		new BetterEnchant("Schlag",Arrays.asList("§7Wenn der Pfeil der abgeschossen wird,","§7einen Spieler trifft bekommt er Rückstoß"), Enchantment.ARROW_KNOCKBACK, 26, 2, Material.BOW);
		new BetterEnchant("Unendlichkeit",Arrays.asList("§7Damit kann dein Bogen unendlich", "§7Pfeile schießen(Muss mindestens 1 dabei haben)"), Enchantment.ARROW_INFINITE, 25, 1, Material.BOW);
		





	}
 	private String name;
	private Enchantment enchant;
	private int slot;
	private int max;
	private Material stack;
	private List<String> description;
	
	public BetterEnchant(String name, List<String> desc, Enchantment enchant, int slot, int max, Material stack) {
		this.name = name;
		this.enchant = enchant;
		this.slot = slot;
		this.max = max;
		this.stack = stack;
		this.description = desc;
		betterenchants.put(name, this);
	}
	
	
	public String getName() {
		return name;
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	public Enchantment getEnchant() {
		return enchant;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public void setDescription(List<String> description) {
		this.description = description;
	}
	
	public Material getStack() {
		return stack;
	}
	
	public static BetterEnchant getByEnchant(Enchantment enchantment) {
		for(BetterEnchant enchant : betterenchants.values()) {
			if(enchant.getEnchant().equals(enchantment)) {
				return enchant;
			}
			
		}
		return null;
	}
	
	public static void openInv(Player p) {
		Inventory inv = p.getServer().createInventory(null, 27, "§aVerzaubern:");
		for(BetterEnchant enchant : betterenchants.values()) {
			List<String> list = new ArrayList<>();
			for(String des : enchant.getDescription()) {
				list.add(des);
			}
			list.add("§3MaxLevel: §7" + enchant.getMax());
			ItemStack stack = new ItemBuilder(enchant.getStack()).setDiplayname(enchant.getName()).setLoreInArrayList(list).
							  addEnchantment(enchant.getEnchant(), 1).build();								
			inv.setItem(enchant.slot, stack);
		}

		inv.setItem(22, Skullist.question);
		p.openInventory(inv);
	}
	
	public static void onKlick(Player p, ItemStack current) {
			if(current.hasItemMeta()) {
				if(current.getItemMeta().hasDisplayName()) {
					String name = current.getItemMeta().getDisplayName();
					if(betterenchants.containsKey(name)) {
						Inventory inv = p.getServer().createInventory(null, 9, "§aVerzauberung:"); //p.getServer().createInventory(null, InventoryType.HOPPER, "§aVerzauberung:");
						Enchantment enchant = betterenchants.get(name).getEnchant();
						inv.setItem(8, new ItemBuilder(Material.BOOK).addEnchant().addItemFlag(ItemFlag.HIDE_ENCHANTS).setDiplayname("§aWas ist das?")
										.setLore("§7Hier kannst du deine Items", "§7Enchanten, lege dafür ganz links das Item", "§7hinein welches du Enchanten",
												 "§7willst. In das rechte legst du", "§7eine bestimmte anzahl von §aEnchantern", "§7hinein. Mehr infos hier:", "§a/verzaubern").build());
						inv.setItem(7, new ItemBuilder(Material.EMERALD_BLOCK).setDiplayname("§aBestätigen").addEnchantment(enchant, 1).build());
						ItemStack pane = new ItemBuilder(Material.THIN_GLASS, 1).setGlassPane(DyeColor.BLACK).build();
						inv.setItem(2,pane);
						inv.setItem(3,pane);
						inv.setItem(4,pane);
						inv.setItem(5,pane);
						inv.setItem(6,pane);
						p.openInventory(inv);
						
						
					}
				}
			}
		
		
		
		
	}
	
	public static void onEmeradlKlick(Player p, Inventory inv) {
		if(cooldown.contains(p.getName())) {
			return;
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				cooldown.remove(p.getName());
				
			}
		},30);
		
		ItemStack forenchant = inv.getItem(0);
		if(forenchant == null) {
			p.sendMessage(prefix + "§cDu musst ein Item in den ersten Slot geben um etwas zu Enchanten!");
			return;
		}
		
		@SuppressWarnings("deprecation")
		int typeid = forenchant.getTypeId();
		if(typeid >= 310 && typeid <= 313) {
			p.sendMessage(prefix + "§cDu kannst Diamantsachen nicht verzaubern!");
			return;
		}
		
		if(typeid >= 277 && typeid <= 279) {
			p.sendMessage(prefix + "§cDu kannst Diamantsachen nicht verzaubern!");
			return;
		}
		if(forenchant.getAmount() != 1){
			p.sendMessage(prefix + "§cDu kannst nur ein Item auf einmal Enchanten");
			return;
		}
		if(forenchant.getType().equals(Material.BOOK)){
			p.sendMessage(prefix + "§cBücher sind wegen buggs nicht enchantbar!");
			return;
		}
		ItemMeta meta = forenchant.getItemMeta();
		BetterEnchant be = BetterEnchant.getByEnchant(getEnchant(inv.getItem(7)));
		
		
		
		
		//Wenn es das Enchantment schon hat:
		if(forenchant.containsEnchantment(be.getEnchant())) {
			int lvl = forenchant.getEnchantmentLevel(be.getEnchant());
			if(lvl >= be.getMax()) {
				p.sendMessage(prefix + "§cDu hast das Maximale Verzauberungslevel von §3" + be.getName() + "§c erreicht!");
				p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
				return;
			}
			if(inv.getItem(1) == null) {
				p.sendMessage(prefix + "§cDu musst " +(lvl +1) + " Enchanter(Feuerbälle) reinlegen!");
				return;
			}
			if(inv.getItem(1).isSimilar(VetoxRecipes.fireball)) {
				if(inv.getItem(1).getAmount() == (lvl +1)) {
					meta.addEnchant(be.getEnchant(), (lvl + 1), true);
					forenchant.setItemMeta(meta);
					inv.setItem(1, new ItemStack(Material.AIR));
					p.sendMessage(prefix + "§7Du hast dein Item erfolgreich Verzaubert!");
					return;
				}
			} 
			p.sendMessage(prefix + "§cDu musst §b" +(lvl +1) + " §cEnchanter(Feuerbälle) reinlegen!");
			return;
			///
		} else {
			
			
			
			
			//Wenn es das Enchantment nicht hat:
			if(inv.getItem(1) == null) {
				p.sendMessage(prefix + "§cDu musst einen Enchanter(Feuerball) reinlegen!");
				return;
			}
			if(inv.getItem(1).equals(VetoxRecipes.fireball)) {
				if(inv.getItem(1).getAmount() == 1) {
					meta.addEnchant(be.getEnchant(), 1, true);
					forenchant.setItemMeta(meta);
					inv.setItem(1, new ItemStack(Material.AIR));
					p.sendMessage(prefix + "§7Du hast dein Item erfolgreich Verzaubert!");
					return;
				}
			}
			p.sendMessage(prefix + "§cDu musst §beinen §cEnchanter(Feuerball) reinlegen!");
			return;
			///
		}
	}
	 
	private static Enchantment getEnchant(ItemStack stack) {
		for(Enchantment ench : stack.getEnchantments().keySet()) {
			return ench;
		}
		return null;
	}
	
	
	
	
}
