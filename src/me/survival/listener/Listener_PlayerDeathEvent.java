package me.survival.listener;

import me.survival.methods.NickNamer;
import me.survival.nation.Nation;
import me.vetoxapi.api.BoardManager;
import me.vetoxapi.methods.NationPoints;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.survival.Main;
import me.survival.methods.InventoryLock;

import java.util.UUID;

public class Listener_PlayerDeathEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(NickNamer.changeing.contains(p.getName())) return;
		UUID id = p.getUniqueId();
		VetoxPlayer vP = VetoxPlayer.stats.get(id);
		vP.setDeaths(vP.getDeaths() + 1);
		InventoryLock.inv.put(p.getName(), p.getInventory().getContents());
		InventoryLock.armor.put(p.getName(), p.getInventory().getArmorContents());
		e.setDeathMessage("");
		e.getDrops().clear();
		e.setDroppedExp(0);


		//Nation Krieg:
		if(p.getWorld().getName().equals("Fight")) {
			p.spigot().respawn();
			if(p.getKiller() instanceof Player) { p.sendMessage(Nation.prefix + "§7Du wurdest von §c" + p.getKiller().getName() + " §7getötet!");} else {
				p.sendMessage(Nation.prefix + "§7Du bist gestorben.");
			}
			if(!Nation.respawn.contains(p.getName())) Nation.respawn.add(p.getName());

			if(vP.getNation().equals(Nation.N1.getName())) NationPoints.now.setN2(NationPoints.now.getN2() + 1);
			if(vP.getNation().equals(Nation.N2.getName())) NationPoints.now.setN1(NationPoints.now.getN1() + 1);

			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
				@Override
				public void run() {
					p.spigot().respawn();
					Nation.onRespawn(p);
				}
			},2);


		}

		if(p.getKiller() instanceof Player) {
			Player killer = p.getKiller();
			killer.sendMessage(Main.prefix + "§7Du hast den Spieler §a" + p.getDisplayName() + " §7gekillt!");
			VetoxPlayer vK = VetoxPlayer.stats.get(killer.getUniqueId());
			vK.setKills(vK.getKills() + 1);
		}

		Entity entity = Listener_PlayerInteractEvent.chair.get(p.getName());
		if(entity != null){
			entity.remove();
			Listener_PlayerInteractEvent.chair.remove(p.getName());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
