package chunkgs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import worldmanager.GsAllowedWorld;

public class ChunkManager {

	public static void onEnable() {
		createMainFiles();
		GsAllowedWorld.registerAllowedGs();
		loadAllChunks();
	}
	
	public static void createMainFiles() {
		
		File f1 = new File("plugins/gs");
		if(!f1.exists()) {
			f1.mkdir();
		}
			
		File f2 = new File("plugins/gs/chunks");
		if(!f2.exists()) {
				f2.mkdir();
		}
		
		File f3 = new File("plugins/gs/players");
		if(!f3.exists()) {
				f3.mkdir();
		}
	}
	
	
	@SuppressWarnings("static-access")
	public static void loadAllChunks() {
		File chunks = new File("plugins/gs/chunks");
		for(File chunk : chunks.listFiles()) {
			YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(chunk);
			int x = cfg.getInt("x");
			int z = cfg.getInt("z");
			String world = cfg.getString("world");
			String ownerID = cfg.getString("owner");
			List<String> friends = cfg.getStringList("friends");
			
			BetterChunk bC = new BetterChunk(x, z, world, ownerID);
			bC.setFriends(friends);
		}
	}
	
	
	public static void createPlayerFile(String id) {
		File file = new File("plugins/gs/players/" + id + ".yml");
		if(file.exists()) return;
		@SuppressWarnings("static-access")
		YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
		cfg.set("chunks", new ArrayList<String>());
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean hasPlayerFile(String id) {
		File file = new File("plugins/gs/players/" + id + ".yml");
		if(file.exists()) return true;
		return false;
	}
	
	public static List<String> getChunks(String id) {
		File file = new File("plugins/gs/players/" + id + ".yml");
		if(!file.exists()) return null;
		@SuppressWarnings("static-access")
		YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
		return cfg.getStringList("chunks");
	}
	
	public static void setChunk(String id, List<String> chunks) {
		File file = new File("plugins/gs/players/" + id + ".yml");
		if(!file.exists()) createPlayerFile(id);
		@SuppressWarnings("static-access")
		YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
		cfg.set("chunks", chunks);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isOnStreet(Chunk c) {
		World w = c.getWorld();
		int x = c.getX() * 16;
		int z = c.getZ() * 16;
		for(int x1 = x; x1 <= x+16; x1 = x1 + 2) {
			for(int z1 = z; z1 <= z+16; z1 = z1 +2) {
				Location loc = new Location(w, x1, 255, z1);
				if(loc.getBlock().getType() == Material.BARRIER) {
					return true;
				}
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		return false;
	}
	
	public static BuilderRegion getBuilderRegion(Chunk c) {
		World w = c.getWorld();
		int x = c.getX() * 16;
		int z = c.getZ() * 16;
		List<BuilderRegion> list = BuilderRegion.buildeRegion.get(w.getName());
		if(list != null) {
			for(BuilderRegion bR : list) {
				if(bR.getX1() <= x && bR.getX2() >= x) {
					if(bR.getZ1() <= z && bR.getZ2() >= z) {
						return bR;
					}
				}	
			}	
		}
		return null;
	}
	
	
	public static Integer getPrice(int chunks) {
		if(chunks > 8) {
			return chunks*250+400;
		}

		switch (chunks) {
			case 1:
				return 250;
			case 2:
				return 400;
			case 3:
				return 650;
			case 4:
				return 850;
			case 5:
				return 950;
			case 6:
				return 1050;
			case 7:
				return 1550;
			case 8:
				return 1850;
			default:
				return chunks*250+400;
		}
	}
	

	public static boolean isForHimBuildable(String id, Location loc) {
		Chunk c = loc.getChunk();
		BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());
		if(bC != null) {
			if(bC.getOwneruuid().equals(id)) {
				return true;
			}
			if(!bC.getOwneruuid().equals(id)) {
				if(bC.getFriends() != null) {
					if(bC.getFriends().contains(id)) return true;
					return false;
				} 
				return false;
			} 
		} 
		return false;
	}
	
	
	
	
	
	
	
	
	
}
