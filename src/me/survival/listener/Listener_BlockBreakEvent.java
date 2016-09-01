package me.survival.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import chunkgs.ChunkManager;
import worldmanager.GsAllowedWorld;
import worldmanager.WorldManager;

public class Listener_BlockBreakEvent implements Listener {
	
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		String id = p.getUniqueId().toString();
		
		if(p.getName().equals("cevill33") || p.getName().equals("EndCrafter_LP") || p.getName().equals("DerLoooder") || p.getName().equals("R2_D2_2002")) {
			e.setCancelled(false);
			return;
		}
		
		
		
		if(WorldManager.protectedworlds.contains(p.getWorld().getName())) {
			if(!p.hasPermission("vetox.protectedworld.build")) {
				e.setCancelled(true);
			}
		}
		
		if(GsAllowedWorld.worlds.containsKey(b.getWorld().getName())) {
			if(ChunkManager.isForHimBuildable(id, b.getLocation())) {
				if(b.getType().equals(Material.REDSTONE_ORE)) {
					b.getDrops().clear();
				}
				
				
				e.setCancelled(false);
				return;
			} 
			e.setCancelled(true);
		}


		if(!e.isCancelled()) {
			if(b.getType().equals(Material.REDSTONE_ORE)) {
				b.getDrops().clear();
			}
		}

	}

}
