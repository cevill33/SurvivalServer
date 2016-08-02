package me.survival.listener;

import me.survival.api.ItemBuilder;
import me.survival.elite.Command_Head;
import me.survival.methods.NickNamer;
import me.survival.nation.NationManager;
import me.survival.npc.King;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.survival.Main;
import me.survival.api.Title;
import me.survival.elite.Command_Song;
import me.survival.magic.MagicManager;
import me.survival.methods.InventoryLock;
import me.survival.objects.BetterEnchant;
import me.survival.objects.Sword;
import me.survival.objects.TravelBallon;
import me.survival.shop.VillagerShop;

public class Listener_InventoryClickEvent implements Listener {


	@EventHandler
	public static void onKlick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null) return;
		Player p = (Player) e.getWhoClicked();
		String name = e.getInventory().getName();
		ItemStack current = e.getCurrentItem();
		if(current.getType() == Material.AIR) return;
		//Zum Registrieren eines Inventars immer mit §a beginnen!

		if(name.startsWith("§a")) {

			//Bürgermeister
			if(name.equals("§aDein König:")){
				e.setCancelled(true);
				King.klickKingGUI(p,current);
				return;
			}

			//Sword
			if(name.equals("§a§5Sword")) {
				e.setCancelled(true);
				if(current.getType().equals(Material.EMERALD)) {
					Sword.onEmeraldKlick(p);
					return;
				}				
			}

			//Ballon
			if(name.equals("§aBallon: Wohin solls gehen?")) {
				e.setCancelled(true);
				String n = current.getItemMeta().getDisplayName();
				if(n == null) return;
				if(n.startsWith("§4")) return;
				
				if(n.equals("Home")) {

					Location loc = new DBVetoxPlayer(p.getUniqueId().toString()).getLocation("home");
					if(loc == null) {
						p.closeInventory();
						p.sendMessage(Main.prefix + "§cDu hast noch keinen Home, setze ihn mit /sethome!");
						return;
					}

					if(!Bukkit.getWorlds().contains(loc.getWorld())) {
						p.sendMessage(Main.prefix + "§cDie Welt in der dein Home liegt existiert nicht mehr.");
						return;
					}

					loc.setY(250);
					p.teleport(loc);
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*9, 10));
					p.sendMessage(Main.prefix + "§7Du wirst nach Hause teleportiert!");
					
					
					
					return;
				}
				
				TravelBallon t = TravelBallon.travel.get(n);
				p.teleport(t.getLoc());
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				Title title = new Title("§7Kontinent: §b" + t.getName());
				title.send(p);
				p.sendMessage(Main.prefix + "§3Reise:");
				p.sendMessage("§7Kontinent: §f" + t.getKontinent());
				String pvp = "";
				if(t.isPvp()) pvp = "§aaktiviert";
				if(!t.isPvp()) pvp = "§4daktiviert";
				p.sendMessage("§7Typ: " + t.getType());
				p.sendMessage("§7PvP: " + pvp);
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 10));
				if(t.getName() == "Farmwelt") {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 650, 10));
				}
				
			}
			//NickNamer
			if(name.equals("§aNickNamer")){
				e.setCancelled(true);
				NickNamer.onClick(p,current, e.getRawSlot());
			}
			//Shop
			if(name.startsWith("§a§lShop§7: ")) {
				e.setCancelled(true);
				if(current.getType() == Material.AIR) return;
				VillagerShop.onKlick(p, current);
				return;
			}
			
			//Verkaufen
			if(name.startsWith("§aVerkaufen: ")) {
				e.setCancelled(true);
				if(current.getType() == Material.AIR) return;
				double price = Double.parseDouble(name.substring(13, name.length() - 11));
				VillagerShop.onSell(p, current, price);
				return;
			}
	
			//Musik
			if(name.equals("§aMusik: ")) {
				e.setCancelled(true);
				Command_Song.onKlick(p, current);
				return;
			}
			
			//Verzaubern
			if(name.equals("§aVerzaubern:")) {
				e.setCancelled(true);
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
				BetterEnchant.onKlick(p, current);
				return;
			}
			
			//Verzauberung
			if(name.equals("§aVerzauberung:")) {
				if(e.getRawSlot() > 1 && e.getRawSlot() < 9) {
					e.setCancelled(true);
				}
				
				if(e.getRawSlot() == 7) {
					BetterEnchant.onEmeradlKlick(p, e.getInventory());
				}
				return;
			}
			
			//Magics
			if(name.equalsIgnoreCase("§aMagics:")) {
				e.setCancelled(true);
				MagicManager.onKlick(p, e.getRawSlot(), current);
				return;
				
			}
			
			//Magic Shop
			if(name.startsWith("§aMagic Shop:")) {
				e.setCancelled(true);
				if(e.getRawSlot() == 8) {
					MagicManager.openMagics(p);
					return;
				}
				if(e.getRawSlot() == 1) {
					MagicManager.onBuyMagic(p, Integer.parseInt(name.substring(16)));
					return;
				}
				
				
			}
			//Respawn Inventar
			if(name.startsWith("§aWillst du deine Sachen wieder?")) {
				e.setCancelled(true);
				if(e.getRawSlot() == 0) {
					InventoryLock.onAccept(p);
					return;
				}
				if(e.getRawSlot() == 1) {
					InventoryLock.onDeny(p);
					return;
				}
			}

			//Choose Nation
			if(name.equals("§aWähle deine Nation/Rasse!")) {
				e.setCancelled(true);
				King.onNationChooseGuiKlick(p, e.getRawSlot(), e.getCurrentItem());
				return;
			}

			//Head
			//TODO Wirft Fehler muss verbessert werden:
			/*if(name.equals("§aHeads")){
				Command_Head.onKlick(p, current.getType());
				e.setCancelled(true);
				return;
			}*/
			
			
			return;
		}

		//Click on Head
		/*if(e.getInventory() instanceof PlayerInventory) {
			e.setCancelled(Command_Head.onHeadClick(p, e.getCurrentItem()));
		}*/
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(current.hasItemMeta()) {
			if(p.getGameMode().equals(GameMode.CREATIVE)) return;
			if(!current.getItemMeta().hasDisplayName()) return;
			if(current.getItemMeta().getDisplayName().startsWith("§f§6")) {
				
			
				if(p.getGameMode().equals(GameMode.SURVIVAL)) {
				p.sendMessage(Sword.prefix + "§cDu kannst die Position deines Schwertes nur mit §7/sword slot <slot> §cändern.");
		e.setCancelled(true);
	}
}

}



		}
	
	
	
	
	
	
	
	
	
}
