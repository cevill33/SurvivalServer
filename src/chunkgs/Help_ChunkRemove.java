package chunkgs;

import java.io.File;

import me.survival.api.UUIDFetcher;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.UUIDConverter;

public class Help_ChunkRemove {

	public static void execute(Player p, String friend) {
		String fid = UUIDFetcher.getUUID(friend).toString();//TODO Testen
		String id = p.getUniqueId().toString();
		Chunk c = p.getLocation().getChunk();
		BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());
		
		if(bC != null) {
			if(bC.getOwneruuid().equals(id)) {
				if(bC.getFriends().contains(fid)) {
					
					bC.getFriends().remove(fid);
					File file = new File("plugins/gs/chunks/" + c.getX() + ":" + c.getZ() + ":" + c.getWorld().getName() + ".yml");
					@SuppressWarnings("static-access")
					YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
					if(cfg.getList("friends").contains(fid)) {
						cfg.getList("friends").remove(fid);
					}
					
					p.sendMessage(Main.prefix + "§7Du hast den Spieler entfernt!");
					
 				} else {
 					p.sendMessage(Main.gsprefix + "§cDu hast diesen Spieler nicht auf den Gs!");
 				}
			}else {
				p.sendMessage(Main.gsprefix + "§cDu hast hier kein Gs!");
			}
		} else {
			p.sendMessage(Main.gsprefix + "§cHier wo du stehst existiert kein Gs!");
		}
		
	}
	
	
	
	
	
	
	
	
}
