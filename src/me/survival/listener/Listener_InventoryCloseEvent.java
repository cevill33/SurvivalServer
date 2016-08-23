package me.survival.listener;

import me.survival.usershop.UserShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.methods.InventoryLock;

import java.io.File;
import java.util.ArrayList;

public class Listener_InventoryCloseEvent implements Listener {
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		String name = e.getInventory().getName();
		
		if(name.startsWith("§a")) {
			Player p = (Player) e.getPlayer();
			Inventory inv = e.getInventory();
			
			//Verzauberung
			if(name == "§aVerzauberung:") {
				ItemStack one = inv.getItem(0);
				ItemStack two = inv.getItem(1);
				if(one != null) {
					p.getInventory().addItem(one);
				}
				
				if(two != null) {
					p.getInventory().addItem(two);
				}
				
			}
			
			//Respawn Inventar
			if(name == "§aWillst du deine Sachen wieder?") {
				if(InventoryLock.inv.containsKey(p.getName())) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main	, new Runnable() {
						
						@Override
						public void run() {
							InventoryLock.openInv(p);
							
						}
					},10);
					
				}
 				return;
			}
			//UserShop
			if(name.startsWith("§aUserShop §7AdminMenü ")){
				String loc = name.replace("§aUserShop §7AdminMenü ","");
				UserShop.saveItemsInCfg(inv,loc);
			}
			//UserShop preis
			if(name.startsWith("Kosten für ")){
				String loc = inv.getItem(2).getItemMeta().getLore().get(0).replace("§7","");
				UserShop.openAdminMenue(p,loc);

			}

			
		}

		
		
	}
	
	

}
