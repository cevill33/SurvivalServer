package me.survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import me.survival.Main;
import me.survival.methods.Tutorial;

public class Listener_RecurcePack implements Listener {

	@EventHandler
	public void onRecurePack(PlayerResourcePackStatusEvent e) {
		Player p = e.getPlayer();
		if(e.getStatus().equals(Status.DECLINED) || e.getStatus().equals(Status.DECLINED)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					p.sendMessage(" ");
					p.sendMessage(" ");
					p.sendMessage(" ");
					p.sendMessage(" ");
					p.sendMessage(" ");
					p.sendMessage(" ");
					p.sendMessage(Main.prefix + "§4Bitte nehme die Recurcen Pack anfrage an!");
					p.sendMessage(Main.prefix + "§4Damit du dir das Tutorial anhören kannst.");
					p.sendMessage(Main.prefix + "§4So gehts: ");
					p.sendMessage("   §31) §fServer verlassen.");
					p.sendMessage("   §32) §fAuf Server einmal klicken.");
					p.sendMessage("   §33) §fAuf Bearbeiten klicken.");
					p.sendMessage("   §34) §fServer-Recourcenpackete auf AKTIVIERT stellen!");
					p.sendMessage("   §35) §fAuf den Server Joinen!");
					p.sendMessage("   §36) §fWieder /tutorial eingeben!");
					p.setAllowFlight(false);
				}
			},20*2);
			
			return;
		}
		
		if(e.getStatus().equals(Status.SUCCESSFULLY_LOADED)) {
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					Tutorial.startTutorialAfterAccept(p, Main.main);
				}
			}, 20*2);
		}
	}
	
	
	
}
