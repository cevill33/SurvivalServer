package me.survival.listener;

import me.survival.Main;
import me.survival.nation.Nation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import chunkgs.ChunkManager;
import me.survival.magic.MagicManager;
import me.survival.objects.BetterEnchant;
import worldmanager.GsAllowedWorld;

import java.util.HashMap;

public class Listener_PlayerInteractEvent implements Listener {

	public static HashMap<Player,Entity> chair = new HashMap<>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		String id = p.getUniqueId().toString();
		Block b = e.getClickedBlock();

		if(e.getClickedBlock() != null) {
			
//			if(b.getType().equals(Material.ANVIL)) {
//				RepairManager.onAmbosKlick(p);
//				e.setCancelled(true);
//				return;
//			}
			
			if(b.getType() == Material.ENCHANTMENT_TABLE) {
				if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					e.setCancelled(true);
					BetterEnchant.openInv(p);
				}
				return;
			}
			if(b.getType() == Material.WOOD_STAIRS || b.getType()==Material.SMOOTH_STAIRS){
				if(b.getLocation().subtract(0,2,0).getBlock().getType() == Material.COAL_BLOCK){
					Arrow arrow = (Arrow) p.getWorld().spawnEntity(b.getLocation().add(0.5,0,0.5), EntityType.ARROW);
					arrow.setPassenger(p);
					chair.put(p,arrow);
				}
			}
			
			if(b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST ||  b.getType() == Material.FURNACE || b.getType() == Material.BURNING_FURNACE || b.getType() == Material.FIRE) {
				if(GsAllowedWorld.worlds.containsKey(b.getWorld().getName())) {
					if(ChunkManager.isForHimBuildable(id, b.getLocation())) {
						e.setCancelled(false);
						return;
					} 
					e.setCancelled(true);
				}
			}
			
			
			
		}
		
		
		if(e.getItem() != null) {

			//Magic
			if(e.getItem().getType().equals(Material.BLAZE_ROD)) {
				if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					MagicManager.openMagics(p);
				}
				
				if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					MagicManager.executeMagic(p);
				}
			}

			//Water & Lava Bucket
			if(e.getItem().getType().equals(Material.WATER_BUCKET) || e.getItem().getType().equals(Material.LAVA_BUCKET)) {
				if(b == null) return;

				if(!GsAllowedWorld.worlds.containsKey(p.getWorld().getName()) && !worldmanager.WorldManager.protectedworlds.contains(p.getWorld().getName()))return;

				if(ChunkManager.isForHimBuildable(p.getUniqueId().toString(), b.getLocation())) return;

				p.sendMessage(Main.prefix + "§cDu kannst hier deinen Eimer nicht benutzen!");
				e.setCancelled(true);
			}










			
			
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
