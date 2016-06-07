package me.survival.objects;

import java.util.ArrayList;
import java.util.List;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.api.ActionBar;

public class Broadcast {
	
	public static int durchlauf = 0;
	public static List<String> lvl2 = new ArrayList<>();
	public static void startBroadcast(Main main) {
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				durchlauf++;
				if(durchlauf >= 10) durchlauf = 0;
				for(Player p : Bukkit.getOnlinePlayers()) {
					int level = VetoxPlayer.stats.get(p.getUniqueId()).getLvl();
					if(level <= 4) {
						ActionBar.sendActionBarTime(p, lvl2.get(durchlauf), 250);
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					
					
				}
			}
		}, 20*60*10, 20*60*10);

	}
	
	
	public static void registerLvl2() {
		lvl2.add("§7Wenn du auf der Straße §2/ride §7eingibst, reitest du auf einem §2Pferd§7.");
		lvl2.add("§7Mit §3/stats§7 siehst du deine §3Statistiken§7!");
		lvl2.add("§7Hast du schon ein §9Grundstück§7? §9/gs help");
		lvl2.add("§7Auf diesem Server gibt es §6Ballone§7 mit denen du §6Reisen§7 kannst!");
		lvl2.add("§7Fragen werden nur §abeantowrtet§7, wenn du mit einem §a? §7den Statz beginnst!");
		lvl2.add("§7Du willst dein §5Schwert verbessern§7? ---> §5/sword upgrade§7!");
		lvl2.add("§7Du willst in die §dFarmwelt§7? §7Dann Reise mit einem §dBallon§7 dort hin!");
		lvl2.add("§7Die Farmwelt hat §1viele§7 unnatürliche §1Höhlen§7 mit vielen §1Erzen§7!");
		lvl2.add("§7Wenn du unsere Regeln (§4/regeln§7) nicht einhältst, gibt es eine §4Bestrafung§7!");
		lvl2.add("§7Wir haben ein Pogramm das den Hackclient §4Xray §7schnell erkennt!");
	}
	
	
	public static void onEnable() {
		registerLvl2();
		startBroadcast(Main.main);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
