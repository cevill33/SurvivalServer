package chunkgs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Help_ChunkAdd {

	public static void execute(Player p, String friend) {
		Location loc = p.getLocation();
		Chunk c = loc.getChunk();
		BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());
		if(bC != null) {	
			if(bC.getOwneruuid().equals(p.getUniqueId().toString())) {
				Player f = Bukkit.getPlayerExact(friend);
				if(f != null) {
					String fid = f.getUniqueId().toString();
					List<String> list = bC.getFriends();
					if(list == null) list = new ArrayList<String>();
					if(!list.contains(fid)) {
						
						list.add(fid);
						bC.setFriends(list);
//						BetterChunk.chunkworld.remove(loc.getChunk());
//						BetterChunk.chunkworld.put(loc.getChunk(), bC);
						
						//Files:
						File file = new File("plugins/gs/chunks/" + loc.getChunk().getX() + ":" + loc.getChunk().getZ() + ":" + loc.getWorld().getName() + ".yml");
						if(!file.exists()) {return; }
						@SuppressWarnings("static-access")
						YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
						List<String>  l = cfg.getStringList("friends");
						l.add(fid);
						cfg.set("friends", l);
						try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
						
						p.sendMessage(Main.gsprefix + "§7Du hast den Spieler §a" + friend + "§7 zum Gs hinzugefügt!");
						f.sendMessage(Main.gsprefix + "§7Der Spieler§a " + p.getName() + " §7hat dich auf eines seiner Gs hinzugefügt!");
					
					} else {
						p.sendMessage(Main.gsprefix + "§cDer Spieler ist bereits bei diesem Gs dabei!");
					}
				} else {
					p.sendMessage(Main.gsprefix + "§cDen Spieler den du adden willst muss online sein!");
				}
			} else {
				p.sendMessage(Main.gsprefix + "§cDu bist nicht der Besitzer von diesem Gs!");
			}
		} else {
			p.sendMessage(Main.gsprefix + "§cHier wo du stehst ist kein Gs!");
		}
	}
	
	
	
	public static void executeAbsolute(Player p, String friend) {
		Player f = Bukkit.getPlayerExact(friend);
		if(f != null) {
			String id = p.getUniqueId().toString();
			File playerfile = new File("plugins/gs/players/" + id + ".yml");
			if(playerfile.exists()) {
				@SuppressWarnings("static-access")
				YamlConfiguration pcfg = new YamlConfiguration().loadConfiguration(playerfile);
				List<String> chunks = pcfg.getStringList("chunks");
				System.out.println(chunks);
				for(String chunk : chunks) {
					String[] array = chunk.split(":");
					String world = array[0];
					int x = Integer.parseInt(array[1]);
					int z = Integer.parseInt(array[2]);
					Location loc = new Location(Bukkit.getWorld(world), x*16, 10, z*16);
					
					BetterChunk bC = BetterChunk.chunkworld.get(loc.getChunk());
					List<String> list = bC.getFriends();
					if(list == null) list = new ArrayList<String>();
					if(list.contains(f.getUniqueId().toString())) continue;
					list.add(f.getUniqueId().toString());
					bC.setFriends(list);
					
					
					File file = new File("plugins/gs/chunks/" + x + ":" + z + ":" + world + ".yml");
					@SuppressWarnings("static-access")
					YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
					List<String>  l = cfg.getStringList("friends");
					l.add(f.getUniqueId().toString());
					cfg.set("friends", l);
					try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
					
				}
				p.sendMessage(Main.gsprefix + "§7Du hast den Spieler§a " + friend +  " §7zu §aall §7deinen Grundst§cken hinzugefügt!");
				f.sendMessage(Main.gsprefix + "§7Der Spieler§a " + p.getName() + " §7hat dich zu all seinen Grundst§cken hinzugefügt!");
			} else {
				p.sendMessage(Main.gsprefix + "§cDu hast noch kein Gs!");
			}
		} else {
			p.sendMessage(Main.gsprefix + "§cDen Spieler den du adden willst muss online sein!");
		}
	}
	
	
	
	
	
	
	
	
	
}
