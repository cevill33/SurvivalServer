package me.survival.api;

import org.bukkit.Location;
import org.bukkit.Material;


public class GetHighestBlock {

	
	public static Integer getHighestBlock(Location loc) {
		for(int i = 255; i >= 0; i--) {
			Location nloc = new Location(loc.getWorld(), loc.getX(), i, loc.getZ());
			if(nloc.getBlock().getType() != Material.AIR && nloc.getBlock().getType() != Material.BARRIER) {
				return i + 1;
			}
		}
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
}
