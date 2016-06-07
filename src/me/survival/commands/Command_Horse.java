package me.survival.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;


public class Command_Horse implements CommandExecutor {
	
	private Main main;
	private List<String> cooldown = new ArrayList<>();

	public Command_Horse(Main main) {
		this.main = main;
	}

	
	public static HashMap<String, Horse> ridemap = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player) cs;

		
		if(args.length == 0) {

			//Cooldown:
			if(cooldown.contains(p.getName())) {
				return true;
			} else {
				Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
					@Override
					public void run() {
						cooldown.remove(p.getName());
					}
				},20);
			}

			//Code
			cooldown.add(p.getName());
			Location up = new Location(p.getWorld(), p.getLocation().getX(), 255, p.getLocation().getZ());
			if(up.getBlock().getType().equals(Material.BARRIER)) {
				Horse horse = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
				horse.setAdult();
				horse.setCarryingChest(false);
				horse.setStyle(Horse.Style.NONE);
				horse.setVariant(Horse.Variant.HORSE);
				horse.setColor(Horse.Color.DARK_BROWN);
				horse.setPassenger(p);
				horse.setTamed(true);
				horse.setOwner(p);
				horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
				ridemap.put(p.getName(), horse);
				int[] count = new int[4];

				count[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
					
					@Override
					public void run() {
						if(!ridemap.containsKey(p.getName())) {
							Bukkit.getScheduler().cancelTask(count[0]);
						}
							
						Location up = new Location(p.getWorld(), p.getLocation().getX(), 255, p.getLocation().getZ());		
						if(!up.getBlock().getType().equals(Material.BARRIER)) {
							horse.remove();
							p.sendMessage(Main.prefix + "§cDu kannst ein Reittier nur auf einer Straße reiten!");
							Bukkit.getScheduler().cancelTask(count[0]);
							ridemap.remove(p.getName());
						}
						
						
						
						
						
					}
				}, 20*4, 20*4);
				
				
				
				
				
				
			} else {
				p.sendMessage(Main.prefix + "§cDu kannst ein Reittier nur auf einer Straße reiten!");
			}
			
			
			
			
			
			
			
			
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/ride");
			
		}
		
		
		
		
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
