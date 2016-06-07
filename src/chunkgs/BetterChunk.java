package chunkgs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class BetterChunk {

	
	public static HashMap<String, BetterChunk> chunkworld = new HashMap<>();
	
	
	private String world;
	private int x;
	private int z;
	
	private String owneruuid;
	private List<String> friends;
	
	
	@SuppressWarnings("static-access")
	public BetterChunk(int x, int z, String world, String ownerID) {
		this.x = x;
		this.z = z;
		this.world = world;
		this.owneruuid = ownerID;
		
		File file = new File("plugins/gs/chunks/" + x + ":" + z + ":" + world + ".yml");
		YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
		cfg.set("world", world);
		cfg.set("x", x);
		cfg.set("z", z);
		cfg.set("owner", ownerID);
		List<String> friend = new ArrayList<>();
		friend.add("testfreund");
		cfg.set("friends", friend);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Chunk c = new Location(Bukkit.getWorld(world), x*16, 10, z*16).getChunk();
		chunkworld.put(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ(), this);
		
	}
	

	
	
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	
	public String getOwneruuid() {
		return owneruuid;
	}
	
	public List<String> getFriends() {
		return friends;
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
	
	
	
	
	
	
}
