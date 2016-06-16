package me.survival.listener;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Filters;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.survival.Main;
import me.survival.api.ClickableChat;
import me.survival.objects.Ask;

public class Listener_PlayerChatEvent implements Listener {
	
	public static List<String> askcountdown = new ArrayList<>();
	private Main main;
	public Listener_PlayerChatEvent(Main main) {
		this.main = main;
	}




	
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String tolowmsg = msg.toLowerCase();
		
		
		//Ask...
		if(msg.startsWith("?") && msg.length() > 3) {
			if(!askcountdown.contains(p.getName())) {
			askcountdown.add(p.getName());
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
			p.sendMessage("§6[§7Ask§6] §7Kann es sein das es sich um eine dieser Fragen handelt!");
			p.sendMessage("");

			Ask questions = Ask.getAsk(tolowmsg);
			p.sendMessage("§aFrage: §7" + questions.getQuestion());
			p.sendMessage("§aAntwort: §f" + questions.getAnswer());

			
			p.sendMessage("");
			ClickableChat.send(p, "§6[§7Ask§6] §7Wenn die Frage nicht dabei war klicke ", "§f§lHIER", "§7Drück drauf", "/askhextori " + msg);
			p.sendMessage("§6[§7Ask§6] §7Um den live Support zu bekommen!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				
				@Override
				public void run() {
					askcountdown.remove(p.getName());
				}
			},20*40);
			} else {
				p.sendMessage("§6[§7Ask§6] §cDu darfst nur alle 40 Sekunden eine Frage stellen!");
				e.setCancelled(true);
			}
		}



		//LVL1 CHECK
		if(p.getLevel() <= 1) {
			e.setCancelled(true);
			p.sendMessage(Main.prefix + "§4Du darfst noch nicht schreiben:");
			p.sendMessage("");
			int tut = new DBVetoxPlayer(p.getUniqueId().toString()).getDocument().getInteger("maintutorial");
			p.sendMessage("  §7Du musst zuertst die erste Quest.");
			p.sendMessage("  §7absolvieren. Viel Glück ;)");
			p.sendMessage("");
			return;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


