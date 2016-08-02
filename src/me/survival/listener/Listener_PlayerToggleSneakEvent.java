package me.survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Created by mariusk on 14.07.2016.
 */
public class Listener_PlayerToggleSneakEvent implements Listener {


    @EventHandler
    public void SneakEvent(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();
        Entity entity = Listener_PlayerInteractEvent.chair.get(p.getName());

        if(entity != null){
            entity.remove();
            Listener_PlayerInteractEvent.chair.remove(p.getName());
        }
    }
}
