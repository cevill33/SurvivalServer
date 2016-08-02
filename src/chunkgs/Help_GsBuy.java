package chunkgs;

import java.util.List;
import java.util.UUID;

import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.survival.Main;
import worldmanager.GsAllowedWorld;

public class Help_GsBuy {

	public static void execute(Player p) {
		Location loc = p.getLocation();
		Chunk c = loc.getChunk();
		if(GsAllowedWorld.worlds.containsKey(loc.getWorld().getName())) {
			if(!BetterChunk.chunkworld.containsKey(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ())) {
				BuilderRegion bR = ChunkManager.getBuilderRegion(c);
				if(bR != null) {
					int lvl = VetoxPlayer.stats.get(p.getUniqueId()).getLvl();
					if(lvl < bR.getRang()) {
						if(bR.getRang() == 11) {
							if(!p.hasPermission("vetox.allbuy")) {
								p.sendMessage(Main.gsprefix + "§cHier können nur Teammitglieder bauen!");
								return;
							}
						}
						p.sendMessage("");
						p.sendMessage(Main.gsprefix + "§cHier(" + bR.getName() + ") kann man erst mit Baurang " + bR.getRang() + " §cbauen!");
						p.sendMessage(Main.gsprefix + "§7Dein Baurang: §3" + lvl);
						p.sendMessage(Main.gsprefix + "§7Zurzeit kann man keinen Baurang aufsteigen.");
						p.sendMessage("§7Die Region geht von: X1 = §3" + bR.getX1() + "§7, Z1 = §3" + bR.getZ1() + "§7 || X2 = §3" + bR.getX2() + "§7, Z2 = §3" + bR.getZ2());
						p.sendMessage("§7" + bR.getMessage());
						p.sendMessage("");
						return;
					}
					
					
				}
				
				if(ChunkManager.getChunks(p.getUniqueId().toString()) != null) {
				if(!p.hasPermission("vetox.chunk.buy.10")) {
					if(ChunkManager.getChunks(p.getUniqueId().toString()).size() > 10) {
						p.sendMessage(Main.gsprefix + " §6Du musst dir Elite §7oder §aTitan §7kaufen um mehr als 10 Grundst§cke zu haben!");
						return;
					}
				}
				}
				
				
				if(!ChunkManager.isOnStreet(c)) {
					UUID uuid = p.getUniqueId();
					String id = uuid.toString();
					ChunkManager.createPlayerFile(id);
					List<String> chunks = ChunkManager.getChunks(id);
					int price = ChunkManager.getPrice(chunks.size() + 1);
					if(MoneyManager.getMoney(uuid) >= price) {
						
						
						
			
						new BetterChunk(c.getX(), c.getZ(), c.getWorld().getName(), id);
						p.sendMessage(Main.gsprefix + "§aDu hast das Gs erfolgreich für §3" + price + " §aCoins gekauft!");
						MoneyManager.removeMoney(uuid, (int) price);
						chunks.add(loc.getWorld().getName() + ":" + loc.getChunk().getX() + ":" + loc.getChunk().getZ());
						ChunkManager.setChunk(id, chunks);
					
					
					
					} else {
						p.sendMessage(Main.gsprefix + "§cDu hast zuwenig Coins. Das Gs kostet §7" + price + " §cCoins!");
					}
				} else {
					p.sendMessage(Main.gsprefix + "§cDu kannst hier kein Gs kaufen weil sich eine Straße neben dir befindet!");
				}
			} else {
				p.sendMessage(Main.gsprefix + "§cJemand hat sich hier bereits ein Gs gekauft!");
			}

		} else {
			p.sendMessage(Main.gsprefix + "§cIn dieser Welt darf man kein Gs kaufen!");
		}

	}
	
	
	
	
	
	
	
	
}
