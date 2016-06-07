package me.survival.listener;

import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.survival.objects.Level;
import monsterworld.Monsters;

import java.util.UUID;

public class Listener_EntityDeathEvent implements Listener{

	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		LivingEntity entity = e.getEntity();
		
		e.setDroppedExp(0);
		if(entity.getKiller() instanceof Player) {
			Player p = entity.getKiller();
			Level.addXp(p, 1);
		}
		
		
		
		if(Monsters.monster.containsKey(entity.getUniqueId())) {
			Monsters m = Monsters.monster.get(entity.getUniqueId());
			if(!m.getList().isEmpty()) { m.getList().remove(1); }
			e.getDrops().clear();
			if(entity.getKiller() instanceof Player ) {
				Player p = entity.getKiller();
				UUID id = p.getUniqueId();
				VetoxPlayer vP = VetoxPlayer.stats.get(id);
				double konstant = (100.0 - vP.getLvl()) / 100.0;
				double drop = m.getDropcoins() * konstant;
				p.sendMessage("§a+§f" + MoneyManager.round(drop) + " §3Coins!");
				vP.setCoins(vP.getCoins() + drop);
					
				if(m.getLvl() == 5) {
					Entity pas = entity.getPassenger();
					if(pas != null) {
						pas.remove();
					}
				}

					
			}
		}
			
			

		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
