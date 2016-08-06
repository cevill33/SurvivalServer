package me.survival.listener;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.nation.Nation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import chunkgs.ChunkManager;
import me.survival.magic.MagicManager;
import me.survival.objects.BetterEnchant;
import org.bukkit.inventory.ItemStack;
import worldmanager.GsAllowedWorld;

import java.util.HashMap;
import java.util.Random;

public class Listener_PlayerInteractEvent implements Listener {

	public static HashMap<String,Entity> chair = new HashMap<>();
	
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
					chair.put(p.getName(),arrow);
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

			//SuperHoe
			ItemStack i = new ItemBuilder(Material.DIAMOND_HOE).setDiplayname("§bSuperHoe").setLore(new String[]{"§aRechtsklick","§7auf eine Pflanze"}).build();
			if(e.getItem().getType().equals(i.getType()) && e.getItem().getItemMeta().equals(i.getItemMeta())){
				if(b == null) return;

				if(GsAllowedWorld.worlds.containsKey(p.getWorld().getName()) && worldmanager.WorldManager.protectedworlds.contains(p.getWorld().getName())) return;

				if(ChunkManager.isForHimBuildable(p.getUniqueId().toString(), b.getLocation())){
					if((b.getType().equals(Material.CARROT)&&b.getData()==7) || (b.getType().equals(Material.CROPS)&&b.getData()==7) || (b.getType().equals(Material.POTATO)&&b.getData()==7)){
						e.setCancelled(true);
						Random rdm = new Random();
						int zufall = rdm.nextInt(4);
						if(b.getType().equals(Material.CARROT)){
							for(int ii = 0;ii<zufall;ii++) {
								p.getWorld().dropItem(b.getLocation(), new ItemStack(Material.CARROT_ITEM));
							}
							b.setData((byte) 0);
							return;
						}
						if(b.getType().equals(Material.POTATO)){
							for(int ii = 0;ii<zufall;ii++) {
								p.getWorld().dropItem(b.getLocation(), new ItemStack(Material.POTATO_ITEM));
							}
							b.setData((byte) 0);
							return;
						}
						if(b.getType().equals(Material.CROPS)){
							for(int ii = 0;ii<zufall;ii++) {
								p.getWorld().dropItem(b.getLocation(), new ItemStack(Material.WHEAT));
							}
							Random rdm2 = new Random();
							int zufall2 = rdm2.nextInt(1);
							for(int ii = 0;ii<zufall2;ii++) {
								p.getWorld().dropItem(b.getLocation(), new ItemStack(Material.SEEDS));
							}

							b.setData((byte) 0);
							return;
						}
					}
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
