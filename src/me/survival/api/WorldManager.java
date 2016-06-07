package me.survival.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldManager {

	private WorldCreator w;
	private String worldname;
	
	public WorldManager(String worldname) {
		this.w = new WorldCreator(worldname);
		this.worldname = worldname;
	}
	
	
	public WorldManager setFlat() {
		w.environment(Environment.NORMAL).type(WorldType.FLAT);
		return this;
	}
	
	public WorldManager setNormal() {
		w.environment(Environment.NORMAL);
		return this;
	}
	
	public WorldManager setNether() {
		w.environment(Environment.NETHER);
		return this;
	}
	
	public WorldManager setEnd() {
		w.environment(Environment.THE_END);
		return this;
	}
	
	
	public void create() {
		if(!isWorldOnServer(worldname)) {
		w.createWorld();
		//FileManager.addWorldInFile(worldname);
		Bukkit.getWorlds().add(Bukkit.getWorld(worldname));
		World world = Bukkit.getWorld(worldname);
		Location normaly = world.getSpawnLocation();
		Location loc = world.getHighestBlockAt(normaly).getLocation();
		world.setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());	
		}
	}
	
	
	
	
    public static void dirDelete(File file) throws IOException
    {
        if (file.isDirectory())
        {
            if (file.list().length == 0)
                file.delete();
            else
            {
                String files[] = file.list();

                for (String temp : files)
                    dirDelete(new File(file, temp));

                if (file.list().length == 0)
                    file.delete();
            }
        }
        else
            file.delete();
    }
    
    
    
    public static void onImport(String worldname) {
    	Bukkit.createWorld(new WorldCreator(worldname));
    }

    
    public static boolean isWorldOnServer(String worldname) {
    	for(String worldnames : Bukkit.getWorldContainer().list()) {
    		if(worldnames.equals(worldname)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    
    
    
	
	
	
	
	
	
}

