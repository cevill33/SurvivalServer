package me.survival.listener;

import me.survival.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import chunkgs.ChunkManager;
import worldmanager.GsAllowedWorld;
import worldmanager.WorldManager;


public class Listener_BlockPlaceEvent implements Listener {
	
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		String id = p.getUniqueId().toString();
		
	
		if(p.getName().equals("cevill33") || p.getName().equals("EndCrafter_LP") || p.getName().equals("DerLoooder")) {
			e.setCancelled(false);
			return;
		}
		
		if(e.getItemInHand().getType().equals(Material.REDSTONE)) {
			p.sendMessage(Main.prefix + "§4Du kannst auf diesem Server kein Redstone benutzen!");
			e.setCancelled(true);
		}
		
		if(WorldManager.protectedworlds.contains(p.getWorld().getName())) {
			if(!p.hasPermission("vetox.protectedworld.build")) {
				e.setCancelled(true);
			}
		}
		
		if(GsAllowedWorld.worlds.containsKey(b.getWorld().getName())) {
			if(ChunkManager.isForHimBuildable(id, b.getLocation())) {
				e.setCancelled(false);
				return;
			} 
			e.setCancelled(true);
		}
		
		
		
		
		
		
	}

}
