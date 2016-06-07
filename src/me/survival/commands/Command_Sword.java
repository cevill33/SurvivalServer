package me.survival.commands;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.survival.api.IsInteger;
import me.survival.objects.Sword;

public class Command_Sword implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		Player p = (Player) cs;
		
		
		if(args.length == 0) {
			p.sendMessage(Sword.prefix + "§a/sword help");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("geben")) {
			String id = p.getUniqueId().toString();

			int slot = (Integer) new DBVetoxPlayer(id).getObject("swordslot");
			if(p.getInventory().getItem(slot - 1) != null) {
				p.sendMessage(Sword.prefix + "§cDer Slot §7" + (slot) + " §cist bereits belegt!");
				p.sendMessage(Sword.prefix + "§cWenn es sich um dein Schwert handelt gebe §7/sword delete §cein!");
				return true;
			}

			p.getInventory().setItem(slot-1, Sword.getSword(p));
			p.sendMessage(Sword.prefix + "§7Du hast dein Schwert neu bekommen!");
			return true;
			
		}
		
		if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("löschen")) {
			Sword.delete(p);
			p.sendMessage(Sword.prefix + "§7Du hast dein Schwert erfolgreich gelöscht!");
			p.sendMessage(Sword.prefix + "§7Hole es mit §a/sword give §7zurück!");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("slot")) {
			if(args.length == 2) {
				if(IsInteger.isInteger(args[1])) {
					int slot = Integer.parseInt(args[1]);
					if(slot <=  9 && slot>=1) {
						String id = p.getUniqueId().toString();

						if(p.getInventory().getItem(slot - 1) != null) {
							p.sendMessage(Sword.prefix + "§cDer Slot §7" + slot + " §cist bereits belegt!");
							return true;
						}
						p.getInventory().remove(p.getInventory().getItem(slot - 1));
						new DBVetoxPlayer(id).setObject("swordslot", slot);
						p.getInventory().setItem(slot - 1, Sword.getSword(p));
						p.sendMessage(Sword.prefix + "§7Du hast den Slot für den Schwert §aerfolgreich §7geändert!");
						return true;
						
					} else {
						p.sendMessage(Sword.prefix + "§cDer Slot muss eine Zahl zwischen 1 und 9 sein!");
						return true;
					}
				} else {
					p.sendMessage(Sword.prefix + "§cDer Slot muss eine Zahl sein!");
					return true;
				}
			} else {
				p.sendMessage(Sword.prefix + "§cSyntax: §7/sword slot <slot>");
				return true;
			}
		}
		
		if(args[0].equalsIgnoreCase("upgrade") || args[0].equalsIgnoreCase("verbessern")) {
			if(args.length == 1) {
				p.sendMessage("§3Wie kann ich mein Schwert verbessern?");
				p.sendMessage("§7Am Spawn der Hauptwelt gibt es ein kleines Ziegelhaus.");
				p.sendMessage("§7In dem steht ein Dorfbewohner. Klick auf ihn und");
				p.sendMessage("§7es wird sich von selbst erklären wie du es verbessern kannst!");
				return true;
			} 
			p.sendMessage(Sword.prefix + "§cSyntax: §7/sword upgrade!");
			return true;
		}
		
		
		if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe")) {
			if(args.length == 1) {
				p.sendMessage(Sword.prefix + "§3Hife:");
				p.sendMessage("§3Was ist Sword überhaupt?");
				p.sendMessage("§7Das ist dein eigenes Schwert welches du beim");
				p.sendMessage("§7Schmied am Spawn verbessern kannst!");
				p.sendMessage("§7Du kannst es bist zu einem verzauberten Eisenschwert");
				p.sendMessage("§7mit Schärfe 10 verbessern!");
				p.sendMessage("§3Befehle:");
				p.sendMessage(" §7- §f/sword help");
				p.sendMessage(" §7- §f/sword give");
				p.sendMessage(" §7- §f/sword delete");
				p.sendMessage(" §7- §f/sword upgrade");
				p.sendMessage(" §7- §f/sword slot <slot>");
				return true;
			} else {
				p.sendMessage(Sword.prefix + "§cSyntax: §7/sword help!");
				return true;
			}
			

		}
		
		
		
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
