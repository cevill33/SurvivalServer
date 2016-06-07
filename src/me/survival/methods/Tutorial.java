package me.survival.methods;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.AntiCheat;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.ActionBar;
import me.survival.api.ClickableChat;
import me.survival.api.Title;
public class Tutorial {

	public static void loadRecurcePack(Player p) {
		p.setResourcePack("http://vetoxmc.de/RpVetoxTutorial.zip");
	}
	
	public static void clearRecurcePack(Player p) {
		p.setResourcePack("http://www.file-upload.net/download5.php?valid=766.14077066215&id=11259210&name=EmptyRecurcepack.rar");
	}
	
	
	public static void startTutorial(Player p) {
		Location loc = new Location(Bukkit.getWorld("Clan"), 0.5, 6, 0.5);
		p.teleport(loc);
		AntiCheat.addFlying(p, "rprecurce", 20*80);
		loadRecurcePack(p);
	}
 	
	public static void startTutorialAfterAccept(Player p, Main main) {
		Title title = new Title("§aAktiviere", "deinen Minecraft Tonvorallem die Notenblöcke!");
		p.sendMessage(Main.prefix + "§7Das Tutorial geht in 5 Sekunden los!");
		title.setStayTime(7);
		title.setTimingsToSeconds();
		title.send(p);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
			
			@Override
			public void run() {
				if(p != null)
				p.playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GOLD_RECORD);
				ActionBar.sendActionBar(p, "");
				ClickableChat.send(p, "§4Falls du jetzt nichts hören solltest klicke ", "§f§l§nHIER§4!", "Aktiviere deine Sound \nin den Minecraft Einstellungen\n und gebe /tutorial ein!");
				
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
					
					@Override
					public void run() {
						
						
						if(p != null) {
							Title t = new Title("§7Gebe nun:", "§3/spawn §7ein!");
							t.send(p);
						}	
						p.sendMessage(Main.prefix + "§7Um das Tutorial nochmal auszuführen kannst du jederzeit §3/tutorial §7eingeben!");
						new DBVetoxPlayer(p.getUniqueId().toString()).setObject("maintutorial", 1);
												
						if(!p.isOnline()) {
							return;
						}
						
						
					}
				},20*149);
			}
		},20*5 );

		
	}
	
	
	
}
