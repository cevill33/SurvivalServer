package chunkgs;

import java.util.HashMap;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import me.survival.Main;
import me.survival.api.Title;
import me.survival.objects.Level;

public class Help_GsTutorial {
	
	
	public static int count;
	public static HashMap<String, Location> tutorial = new HashMap<>();
	public static void clearChat(Player p) {
		p.playSound(p.getLocation(), Sound.LEVEL_UP , 1, 1);
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		
	}
	
	
	
	
	public static void execute(Player p, Main main) {
		if(tutorial.containsKey(p.getName())) {
			p.sendMessage(Main.gsprefix + "§cDu machst gerade dieses Tutorial!");
			return;
		}
		
		tutorial.put(p.getName(), p.getLocation());
		Title title = new Title("§eGs- Tutorial");
		title.send(p);
		p.playSound(p.getLocation(), Sound.LEVEL_UP , 1, 1);
		
		
		
		
		count = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			int i = 0;
			
			
			@Override
			public void run() {
				i++;
				if(p.isOnline()) {
					if(i == 1) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas ist Gs überhaupt?");
						p.sendMessage("§7");
						p.sendMessage("§7");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 2) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas ist Gs überhaupt?");
						p.sendMessage("§7Ein Plugin was dir erlaubt geschützte");
						p.sendMessage("§7Grundstücke zu erstellen!");
						p.sendMessage("§3§m§l------------------------------------");						
						return;
					}
					
					if(i == 3) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWie geht das genau?");
						p.sendMessage("§7");
						p.sendMessage("§7");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 4) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWie geht das genau?");
						p.sendMessage("§7Der Befehl zum erstellen lautet!");
						p.sendMessage("§7/gs buy");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 5) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas bewirkt er?");
						p.sendMessage("§7Er erstellt ein Gs,");
						p.sendMessage("");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 6) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas bewirkt er?");
						p.sendMessage("§7Er erstellt ein Gs,");
						p.sendMessage("§7welches einen Chunk groß ist!");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 7) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas ist ein Chunk");
						p.sendMessage("§7Ein Chunk ist eine 16*16 große Region");
						p.sendMessage("");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 8) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas ist ein Chunk");
						p.sendMessage("§7Ein Chunk ist eine 16*16 große Region");
						p.sendMessage("§fDamit du immer weißt von wo bis wo,");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 9) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aWas ist ein Chunk");
						p.sendMessage("§7ein Chunk geht, musst du nur /gs chunk");
						p.sendMessage("§7eingeben!");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 10) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aAchtung:");
						p.sendMessage("§7Desto mehr Gs's du hast,");
						p.sendMessage("§7");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 11) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------------");
						p.sendMessage("§aAchtung:");
						p.sendMessage("§7Desto mehr Gs's du hast,");
						p.sendMessage("§7desto mehr kosten sie auch.");
						p.sendMessage("§3§m§l------------------------------------");
						
						return;
					}
					
					if(i == 12) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------");
						p.sendMessage("§7Damit du immmer weißt von wo bis wo");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("§3§m§l------------------------------");
						
						return;
					}
					if(i == 13) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------");
						p.sendMessage("§7Damit du immmer wei§t von wo bis wo");
						p.sendMessage("§7das Gs geht: §3/gs chunk");
						p.sendMessage("");
						p.sendMessage("§3§m§l------------------------------");
						
						return;
					}
					
					if(i == 14) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------");
						p.sendMessage("Du kannst den Preiß für dein nächstes");
						p.sendMessage("Gs mit /gs next nachschauen!");
						p.sendMessage("§7");
						p.sendMessage("§3§m§l------------------------------");
						
						return;
					}
					
					
					
					if(i == 15) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------");
						p.sendMessage("§aWie kann mein Freund mitbauen?");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("§3§m§l------------------------------");
						
						return;
					}
					
					
					if(i == 16) {
						clearChat(p);
						p.sendMessage("§3§m§l------------------------------");
						p.sendMessage("§aWie kann mein Freund mitbauen?");
						p.sendMessage("Dafür musst du nur auf deinem Gs stehen");
						p.sendMessage("und §a/gs add <Spieler> §feingeben!");
						p.sendMessage("§3§m§l------------------------------");
						
						return;
					}
					
					
					if(i == 17) {
						String UUID = p.getUniqueId().toString();
						DBVetoxPlayer dV = new DBVetoxPlayer(UUID);
						if((Integer) dV.getObject("gstutorial") == 0) {
						clearChat(p);
						Title title = new Title("§eDanke fürs Tutorial.", "§7+§a100 §7Coins");
						title.send(p);
						Bukkit.getScheduler().cancelTask(count);
						MoneyManager.addMoney(p.getUniqueId(), 100);
						Level.addXp(p, 70);
						p.sendMessage(Main.gsprefix + "§7+ §a100§7Coins!");
						p.sendMessage(Main.gsprefix + "§7+ §a70§7Xp!");
						dV.setObject("gstutorial", 1);
						tutorial.remove(p.getName());
						
						return;
						
						}
						
						
						
						clearChat(p);
						Title title = new Title("§eDanke fürs Tutorial.", "");
						title.send(p);
						Bukkit.getScheduler().cancelTask(count);
						p.sendMessage(Main.gsprefix + "§7+ §aGeschafft!");
						tutorial.remove(p.getName());
						
						
						
						}

		
					
					
					
				} else {
					Bukkit.getScheduler().cancelTask(count);
					Help_GsTutorial.tutorial.remove(p.getName());
				}
				
			}
		}, 4, 22*4);
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
