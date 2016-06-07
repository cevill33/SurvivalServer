package me.survival.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.api.Title;

public class Ballon {

	public static void openBallonInventory(Player p) {
		
		Inventory inv = p.getServer().createInventory(null, 9, "§aBallon: Wohin solls gehen?");
		inv.addItem(new ItemBuilder(Material.STONE_PICKAXE).setDiplayname("§6Farmwelt").setLore("§7Kontinent: Europia" , "§7PvP: §aaktiviert", "§7Typ: Farmwelt").build());
		inv.addItem(new ItemBuilder(Material.PACKED_ICE).setDiplayname("§6Ice").setLore("§7Kontinent: Elbros" , "§7PvP: §4deaktiviert", "§7Typ: Hauptwelt").build());
		p.openInventory(inv);
		
	}
	
	
	
	
	
	
	public static void teleportIce(Player p) {
		
		p.teleport(new Location(Bukkit.getWorld("Mainworld"), 1677.5, 53.2, -1529.5));
		p.sendMessage(Main.prefix + "§3Reise:");
		p.sendMessage("   §7Kontinent: §fElbros(Mainworld)");
		p.sendMessage("   §7Typ: §fHauptwelt");
		p.sendMessage("   §7PvP: §4Deaktiviert");
		new Title("§7Kontinent: §bElbros").send(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 3));
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
