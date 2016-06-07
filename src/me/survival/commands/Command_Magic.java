package me.survival.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.magic.MagicManager;

public class Command_Magic implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("geben")) {
				if(p.getInventory().contains(MagicManager.stick)) {
					p.sendMessage(MagicManager.prefix + "§cDu hast bereits einen Zauberstab in deinem Inventar!");
					return true;
				}
				
				if(isFull(p.getInventory())) {
					p.sendMessage(MagicManager.prefix + "§cDein Inventar ist voll!");
					return true;
				}
				
				p.getInventory().addItem(MagicManager.stick);
				p.sendMessage(MagicManager.prefix + "§7Du hast nun einen §3Magicstick§7 erhalten!");
				return true;
				
			}
		}
		
		p.sendMessage(MagicManager.prefix + "§cDeine Magics kannst du nur per Zauberstab benutzen! Falls du keinen hast gebe /magic give ein!");
		return false;
	}

	
	
	public static boolean isFull(Inventory inv) {
	    for (ItemStack i : inv.getContents()) {
	        if (i == null || i.getType() == Material.AIR) return false;
	    }
	    return true;
	}
	
	
}
