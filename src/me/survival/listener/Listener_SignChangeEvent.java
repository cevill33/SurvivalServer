package me.survival.listener;

import me.survival.usershop.UserShop;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created by mariusk on 21.08.2016.
 */
public class Listener_SignChangeEvent implements Listener {

    @EventHandler
    public void on(SignChangeEvent e) {
        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        if (e.getLine(0).equalsIgnoreCase("[Shop]")) {
            if(UserShop.isAllowedUserShop(p,(Sign) e.getBlock())) {
                e.setLine(0, "§7[§8Shop§7]");
                UserShop.createShop(p,l);
                String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + l.getWorld().getName();
                UserShop.openAdminMenue(p,loc);
            }
        }
    }
}
