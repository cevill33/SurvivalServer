package me.survival.listener;

import chunkgs.ChunkManager;
import me.survival.nation.Nation;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.survival.commands.Command_Horse;
import org.bukkit.event.entity.EntityDamageEvent;
import worldmanager.GsAllowedWorld;

public class Listener_EntityDamageEvent implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		//If Player is Damager:
		if(e.getDamager() instanceof Player) {

			Player p = (Player) e.getDamager();
			Entity entity = e.getEntity();



			//Horse
			if(e.getEntityType().equals(EntityType.HORSE)) {
				Horse h = (Horse) entity;
				if(Command_Horse.ridemap.containsValue(h)) {
					e.setCancelled(true);
				}
			}
			//Gs fix
			if(e.getEntityType().equals(EntityType.ARMOR_STAND) || e.getEntityType().equals(EntityType.MINECART_CHEST) || e.getEntityType().equals(EntityType.MINECART)){
				if(!GsAllowedWorld.worlds.containsKey(p.getWorld().getName()) && !worldmanager.WorldManager.protectedworlds.contains(p.getWorld().getName()))e.setCancelled(true);

				if(!ChunkManager.isForHimBuildable(p.getUniqueId().toString(), entity.getLocation())) e.setCancelled(true);
			}
			//Player
			if(entity instanceof Player) {

				Player target = (Player) entity;
				if(target.getWorld().getName().equals("Fight")) {

					if(VetoxPlayer.stats.get(target.getUniqueId()).getNation() == VetoxPlayer.stats.get(p.getUniqueId()).getNation()) {
						p.sendMessage(Nation.prefix + "§cDas Schlagen deiner Teammitglieder ist VERBOTEN!");
						p.playSound(p.getLocation(), Sound.BLAZE_HIT, 1, 1);
						if(p.getHealth() != 0) p.setHealth(p.getHealth()-1);
						e.setCancelled(true);
					}

				}
			}

		}




		//If Arrow is Damager:
		if(e.getDamager() instanceof Arrow) {

			Arrow arrow = (Arrow) e.getDamager();
			Entity entity = e.getEntity();
			//If the Shooter is a Player:
			if(arrow.getShooter() instanceof Player && entity instanceof Player) {

				Player shooter = (Player) arrow.getShooter();
				Player target = (Player) entity;


				if(VetoxPlayer.stats.get(shooter.getUniqueId()).getNation() == VetoxPlayer.stats.get(target.getUniqueId()).getNation()) {
					shooter.sendMessage(Nation.prefix + "§cDas Beschießen deiner Teammitglieder ist VERBOTEN!");
					shooter.playSound(shooter.getLocation(), Sound.BLAZE_HIT, 1, 1);
					if(shooter.getHealth() != 0) shooter.setHealth(shooter.getHealth() - 1);
					e.setCancelled(true);
				}
			}
		}







	}
	
	

}
