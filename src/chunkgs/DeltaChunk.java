package chunkgs;

import org.bukkit.Location;

public class DeltaChunk {

	private String world;
	private int x;
	private int z;
	
	public DeltaChunk(String world, int x, int z) {
		this.world = world;
		this.x = x;
		this.z = z;
	}
	
	public String getWorld() {
		return world;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public static DeltaChunk getChunk(Location loc) {
		return new DeltaChunk(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockZ());
	}
	
	
	
	
	
	
}
