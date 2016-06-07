package me.survival.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.methods.VetoxRecipes;

public class Command_Recipe implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			return true;
		}	
		if(args[0].equalsIgnoreCase("enchanter")) {
			Inventory inv = p.getServer().createInventory(null, InventoryType.WORKBENCH, "§aRezept");
			ItemStack iron = new ItemStack(Material.IRON_INGOT);
			inv.setItem(1, iron);
			inv.setItem(2, iron);
			inv.setItem(3, iron);
			inv.setItem(4, iron);
			inv.setItem(5, new ItemStack(Material.DIAMOND));
			inv.setItem(6, iron);
			inv.setItem(7, iron);
			inv.setItem(8, new ItemStack(Material.STICK));
			inv.setItem(9, iron);
			inv.addItem(VetoxRecipes.fireball);
			p.openInventory(inv);
		}		
		return false;
	}
}