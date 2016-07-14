package me.survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Created by mariusk on 14.07.2016.
 */
public class Listener_PlayerToggleSneakEvent implements Listener {


    @EventHandler
    public void s(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();
        if(Listener_PlayerInteractEvent.chair.get(p)!=null){
            Listener_PlayerInteractEvent.chair.get(p).remove();
            Listener_PlayerInteractEvent.chair.put(p,null);
        }
    }
}
