package me.survival.methods;

import me.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jakob on 07.05.2016.
 */
public class AFK {

    private static HashMap<String, Location> locationHashMap = new HashMap<>();

    public static void registerAFK(Main main) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {

                for(Player all : Bukkit.getOnlinePlayers()) {
                    Location now = all.getLocation();
                    Location last = locationHashMap.get(all.getName());

                    if(last == null) {
                        locationHashMap.put(all.getName(), new Location(all.getWorld(), now.getBlockX(), now.getBlockY(), now.getBlockZ()));
                        continue;
                    }

                    if(now.getBlockX() == last.getBlockX() && now.getBlockZ() == last.getBlockZ() && now.getBlockZ() == last.getBlockZ()) {
                        all.kickPlayer(Main.prefix + "\n \n §aDu wurdest da du §8AFK§a warst gekickt!");
                        Bukkit.broadcastMessage(Main.prefix + "§7Der Spieler §f" + all.getName() + " §7wurde gekickt, da er AFK war!");
                        continue;
                    }

                    locationHashMap.put(all.getName(), new Location(all.getWorld(), now.getBlockX(), now.getBlockY(), now.getBlockZ()));

                }

            }
        },20*60*8, 20*60*8);
    }

}
