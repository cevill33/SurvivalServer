package chunkgs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.survival.api.UUIDConverter;
import me.survival.api.UUIDFetcher;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.GetHighestBlock;

public class Command_Gs implements CommandExecutor {

	private Main main;
	private static List<String> cooldown = new ArrayList<>();
	private static List<String> infocooldown = new ArrayList<>();

	public Command_Gs(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			p.sendMessage(Main.gsprefix + "§7Gebe /gs help ein!");
			return true;
		}
		
		
		if(args[0].equalsIgnoreCase("buy") || args[0].equalsIgnoreCase("kaufen")) {
			if(args.length == 1) {
				Help_GsBuy.execute(p);
			}
		}
		
		if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("hinzufügen")) {
			if(args.length == 2) {
				String name = args[1];
				Help_ChunkAdd.execute(p, name);
				return true;
			}
			
//			if(args.length == 3) {
//				if(args[2].equalsIgnoreCase("absolute")) {
//					String name = args[1];
//					Help_ChunkAdd.executeAbsolute(p, name); //Zurzeit Deaktiviert!
//					return true;
//					
//				}
//				
//			}
			
			p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs add <Spieler> (Addet Spieler auf das Gs auf dem du stehst)");
			p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs add <Spieler> absolute (Addet Spieler auf all deine Gs)");
		}
		
		
		
		if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("löschen")) {
			if(args.length == 2) {
				String name = args[1];
				Help_ChunkRemove.execute(p, name);
				return true;
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs remove <Spieler>!");
				return true;
			}
		}
		
		//Help
		if(args[0].equalsIgnoreCase("help")) {
			if(args.length == 1) {
				p.sendMessage(Main.gsprefix + "§3Befehle:");
				p.sendMessage(" §7- §3/gs buy §fDamit kannst du ein Gs Kaufen.");
				p.sendMessage(" §7- §3/gs add §fDamit kann man jmd. auf dem Gs bauen lassen.");
				p.sendMessage(" §7- §3/gs remove <Spieler> §fKickt einen Spieler von deinem Gs!");
				p.sendMessage(" §7- §3/gs tutorial §fIst ein ausführliches Tutorial.");
				p.sendMessage(" §7- §3/gs price §fZeigt den Preis für das nächste Gs.");
				p.sendMessage(" §7- §3/gs chunk §fZeigt an von wo bis wo sich der Chunk befindet.");
				p.sendMessage(" §7- §3/gs list §fZeigt dir all deine Grundstücke.");

			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs help!");
				return true;

			}
		}
		
		//Info
		if(args[0].equalsIgnoreCase("info")) {
			if(args.length == 1) {
				Location loc = p.getLocation();
				Chunk c = loc.getChunk();
				BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());
				
				if(bC != null) {
					
					p.sendMessage(Main.gsprefix + "§3Info:");
					p.sendMessage(" §bBesitzer: §7" + UUIDFetcher.getName(UUID.fromString(bC.getOwneruuid())));
					String keine = "";
					if(bC.getFriends().size() <= 1) keine = " §4Keine";
					p.sendMessage(" §bFreunde:" + keine);
					for(String friends : bC.getFriends()) {
						if(friends.equals("testfreund")) continue;
						p.sendMessage("   §e- §7" + UUIDFetcher.getName(UUID.fromString(friends)));
					}


					
				} else {
					p.sendMessage(Main.gsprefix + "§7Hier ist kein Gs!");
				}
				
				
				
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs info!");
			}
			return true;
		}
		
		
		
		//Tutorial
		if(args[0].equalsIgnoreCase("tutorial")) {
			if(args.length == 1) {
				 Help_GsTutorial.execute(p, main);
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs tutorial!");
				return true;
			}
			return true;
		}
		
		//List
		if(args[0].equalsIgnoreCase("list")) {
			
			if(args.length == 1) {
				
				if(ChunkManager.getChunks(p.getUniqueId().toString()) == null) {
					p.sendMessage(Main.gsprefix + "§cDu hast kein Gs :P!");
					return true;
				}	

				p.sendMessage(Main.gsprefix + "§3Deine Grundstücke:");
				
				for(String s : ChunkManager.getChunks(p.getUniqueId().toString())) {
				
					String[] array = s.split(":");
					BetterChunk bC = BetterChunk.chunkworld.get(array[0] + ":" + array[1] + ":" + array[2]);
					p.sendMessage("  §3X:§f " + bC.getX() * 16 + "§3|| Z: §f" + bC.getZ()*16 + "§3 || Welt: §f" + bC.getWorld());
				
				}		
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs list!");
					
			}
		}

		//AdminDelete
		if(args[0].equalsIgnoreCase("forcedelete")) {
			if(!p.hasPermission("vetox.gs.force.delete")) {
				p.sendMessage(Main.gsprefix + "§cDu hast keine Recht diesen Befehl zu benutzen.");
				return true;
			}

			if(args.length == 1) {

				Location loc = p.getLocation();
				Chunk c = loc.getChunk();
				BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());

				if(bC == null) {
					p.sendMessage(Main.gsprefix + "§cHier gibt es kein Gs!");
					return true;
				}

				BetterChunk.chunkworld.remove(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());
				ChunkManager.deleteChunckFromFiles(bC);
				p.sendMessage(Main.gsprefix + "§aDas Gs wurde gelöscht!");



			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs forcedelete!");
				return true;
			}

		}
			
		
		
		
		//Price , Next
		if(args[0].equalsIgnoreCase("next") || args[0].equalsIgnoreCase("price")) {
			if(args.length == 1) {
				if(ChunkManager.getChunks(p.getUniqueId().toString()) == null) {
					p.sendMessage(Main.gsprefix + "§7Das nächste Gs wird dich §3250§7 Coins kosten!");
					return true;
				}
				int price =  ChunkManager.getPrice(ChunkManager.getChunks(p.getUniqueId().toString()).size() + 1);
				p.sendMessage(Main.gsprefix + "§7Das nächste Gs wird dich §3" + price + "§7 Coins kosten!");
				return true;
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs next!");
				return true;
			}
		}


		if(args[0].equalsIgnoreCase("chunk")) {
			if(args.length == 1) {
				if(cooldown.contains(p.getName())) {
					p.sendMessage(Main.gsprefix + "§cDu kannst diesen Befehl nur alle 8 Sekunden benutzen!");
					return true;
				}
				cooldown.add(p.getName());
				Location loc = p.getLocation();
				World w = loc.getWorld();
				
				Location loc1 = loc.getChunk().getBlock(0, 0, 0).getLocation();
				Location pos1 = new Location(w, loc1.getX(), GetHighestBlock.getHighestBlock(loc1), loc1.getZ());
				
				Location loc2 = loc.getChunk().getBlock(0, 0, 15).getLocation();
				Location pos2 = new Location(w, loc2.getX(), GetHighestBlock.getHighestBlock(loc2), loc2.getZ());
				
				Location loc3 = loc.getChunk().getBlock(15, 0, 0).getLocation();
				Location pos3 = new Location(w, loc3.getX(), GetHighestBlock.getHighestBlock(loc3), loc3.getZ());
				
				Location loc4 = loc.getChunk().getBlock(15, 0, 15).getLocation();
				Location pos4 = new Location(w, loc4.getX(), GetHighestBlock.getHighestBlock(loc4), loc4.getZ());
				
				p.sendBlockChange(pos1, Material.SEA_LANTERN, (byte) 0);
				p.sendBlockChange(pos2, Material.SEA_LANTERN, (byte) 0);
				p.sendBlockChange(pos3, Material.SEA_LANTERN, (byte) 0);
				p.sendBlockChange(pos4, Material.SEA_LANTERN, (byte) 0);
				p.sendMessage(Main.gsprefix + "§7Die 4 Seelaternen sind die ecken des Chunks!");
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
					
					
					@Override
					public void run() {
						p.sendBlockChange(pos1, Material.AIR, (byte) 0);
						p.sendBlockChange(pos2, Material.AIR, (byte) 0);
						p.sendBlockChange(pos3, Material.AIR, (byte) 0);
						p.sendBlockChange(pos4, Material.AIR, (byte) 0);
						cooldown.remove(p.getName());
					}
				},20*8);
				
				
				
				
				
			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs chunk!");
			}
		}
		

		if(args[0].equalsIgnoreCase("info")) {
			if(args.length == 1) {

				if(infocooldown.contains(p.getName())) {
					p.sendMessage(Main.gsprefix + "§cDu kannst diesen Befehl nur alle 5 Sekunden nutzen!");
				} else {

					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
						@Override
						public void run() {
							infocooldown.remove(p.getName());
						}
					},20*5);

					Location loc = p.getLocation();
					Chunk c = loc.getChunk();
					BetterChunk bC = BetterChunk.chunkworld.get(c.getWorld().getName() + ":" + c.getX() + ":" + c.getZ());

					if(bC == null) {
						p.sendMessage(Main.gsprefix + "§cHier gibt es kein Gs!");
						return true;
					}

					p.sendMessage(Main.gsprefix + "§3Info: ");
					DBVetoxPlayer dB = new DBVetoxPlayer(bC.getOwneruuid());
					if(dB == null) {
						p.sendMessage(Main.gsprefix + "§cDieser Spieler wurde in unserer Datenbank nicht gefunden!");
						return true;
					}

					p.sendMessage("  §bBesitzer: §f" + dB.getObject("lastname"));
					p.sendMessage("  §bMitbewohner: §f");
					for (String s : bC.getFriends()) {
						DBVetoxPlayer dT = new DBVetoxPlayer(s);
						if(s == null) continue;
						p.sendMessage("     §b§l- §f" + dT.getObject("lastname"));
					}
				}

			} else {
				p.sendMessage(Main.gsprefix + "§cSyntax: §7/gs info!");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
