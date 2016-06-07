package me.survival.elite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import me.survival.Main;

public class Command_Firework implements CommandExecutor {

	public static List<String> cooldown = new ArrayList<>();
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			if(p.hasPermission("vetox.firework")) {
				if(!cooldown.contains(p.getName())) {
					
					p.sendMessage(Main.prefix + "§7Das Feuerwerk startet...");
					
					cooldown.add(p.getName());
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {
						@Override
						public void run() {
							cooldown.remove(p.getName());
						}
					}, 20*30);
					
					
					final int ID[] = new int[1];
					List<Integer> i = new ArrayList<>();
					ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
						@Override
						public void run() {
							i.add(1);
							if(i.size() > 10) {
								Bukkit.getScheduler().cancelTask(ID[0]);
								p.sendMessage(Main.prefix + "§7Ende!");
								return;
							}
							launchFirewor(p);
						}
					}, 0, 20);
					
					
					
					
				} else {
					p.sendMessage(Main.prefix + "§cDu kannst diesen Befehl nur alle 30 Sekunden ausf§hren!");
				}
			} else {
				p.sendMessage(Main.prefix + "§7Kaufe dir §6Elite §7oder §aTitan §7um dieses Feature zu benutzen!");
			}
		} else {
			p.sendMessage(Main.prefix + "§cSyntax: §7/firework!");
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void launchFirewor(Player p) {
        //Spawn the Firework, get the FireworkMeta.
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        //Our random generator
        Random r = new Random();  

        //Get the type
        int rt = r.nextInt(4) + 1;
        Type type = Type.BALL;      
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
        if (rt == 5) type = Type.STAR;
       
        //Get our random colours  
        Color c1 = Color.AQUA;
        Color c2 = Color.BLACK;
       
        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
       
        //Then apply the effect to the meta
        fwm.addEffect(effect);
       
        //Generate some random power and set it
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
       
        //Then apply this to our rocket
        fw.setFireworkMeta(fwm); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
