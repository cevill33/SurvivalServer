package me.survival.objects;

import java.util.HashMap;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.api.ItemBuilder;

public class TravelBallon {

	
	public static HashMap<String, TravelBallon> travel = new HashMap<>();
	private Material material;
	private String name;
	private Location loc;
	private boolean pvp;
	private String kontinent;
	private int lvl;
	private String type;
	private int slot;

	public TravelBallon(Material material, String name, Location loc, boolean pvp, String kontinent, int lvl, String type, int slot) {
		this.material = material;
		this.name = name;
		this.loc = loc;
		this.pvp = pvp;
		this.kontinent = kontinent;
		this.lvl = lvl;
		this.type = type;
		this.slot = slot;
		travel.put(name, this);
	}
	
	
	public Location getLoc() {
		return loc;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public String getName() {
		return name;
	}
	
	public String getKontinent() {
		return kontinent;
	}
	
	public boolean isPvp() {
		return pvp;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public ItemStack getItemStack() {
		String fight;
		fight = "";
		if(pvp) fight = "§aaktiviert";
		if(!pvp) fight = "§4daktiviert";
		ItemStack i = new ItemBuilder(this.material).setDiplayname(name).setLore("§7Kontinent: " + kontinent, "§7PvP: " + fight, "§7Typ: " + type).build();
		return i;
	}
	
	public static void openInventory(Player p, String signname) {
		Inventory inv = p.getServer().createInventory(null, 27, "§aBallon: Wohin solls gehen?");
		int lvl = VetoxPlayer.stats.get(p.getUniqueId()).getLvl();
		for(TravelBallon t : travel.values()) {
			if(signname.endsWith(t.getName())) {
				inv.setItem(t.getSlot(), new ItemBuilder(t.getMaterial()).setDiplayname("§c" + t.getName()).setLore("§7Hier befindest du dich gerade!").build());
				continue;
			}
			if(lvl >= t.getLvl()) {
				inv.setItem(t.getSlot(), t.getItemStack());
			} else {
				inv.setItem(t.getSlot(), new ItemBuilder(t.getMaterial()).setDiplayname("§4Erst ab Level §c" + t.getLvl() + " §4verfügbar!").build());
			}
		}
		
		inv.setItem(18, new ItemBuilder(Material.SIGN).setDiplayname("Home").setLore("§7Teleportiert dich nach Hause!").build());
		
		
		p.openInventory(inv);
	}
	
	public static void registerTravelBallon() {
		new TravelBallon(Material.NETHER_STAR, "Spawn", new Location(Bukkit.getWorld("Mainworld"), -254.5, 48.5, 438.5), false, "Elbros", 0, "Hauptwelt", 13);
		new TravelBallon(Material.FLOWER_POT_ITEM, "Farmwelt", new Location(Bukkit.getWorld("Farmworld1A_2"), -30, 300, 250), true, "Europia", 0, "Farmwelt", 16);
		new TravelBallon(Material.FIREBALL, "Monster", new Location(Bukkit.getWorld("Monsterworld"), 51.5, 13.5, 1.5), false, "Pandora", 0, "Monsterwelt", 26);
		new TravelBallon(Material.PACKED_ICE, "Ice", new Location(Bukkit.getWorld("Mainworld"), 1677.5, 53.2, -1529.5), false, "Elbros", 2, "Hauptwelt", 21);
		new TravelBallon(Material.HAY_BLOCK, "Rohan", new Location(Bukkit.getWorld("Mainworld"), -984.5, 61, 803.5), false, "Elbros", 10, "Hauptwelt", 23);
		new TravelBallon(Material.GRASS, "ClassicFarmworld", Bukkit.getWorld("Farmworld2").getSpawnLocation(), false, "Europia", 5, "Farmwelt", 6);
	}
	
	
	
}
