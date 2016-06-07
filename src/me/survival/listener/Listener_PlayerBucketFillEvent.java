package me.survival.listener;

import chunkgs.ChunkManager;
import me.survival.Main;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import worldmanager.GsAllowedWorld;
import worldmanager.WorldManager;

import java.util.List;
import java.util.Set;

/**
 * Created by Jakob on 14.04.2016.
 */
public class Listener_PlayerBucketFillEvent implements Listener{


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFill(PlayerBucketFillEvent e) {

        Player p = e.getPlayer();
        if(!WorldManager.protectedworlds.contains(p.getWorld().getName()) && !GsAllowedWorld.worlds.containsKey(p.getWorld().getName())) {
            return;
        }

        List<Block> los = e.getPlayer().getLineOfSight((Set<Material>) null, 5);
        boolean bo = false;
        for(Block b : los) {
            final Material type = b.getType();
            System.out.println(type.toString());
            if(type == Material.STATIONARY_LAVA || type == Material.STATIONARY_WATER) {
                if(ChunkManager.isForHimBuildable(p.getUniqueId().toString(), b.getLocation())) {
                    e.setCancelled(false);
                    continue;
                }
                e.setCancelled(true);
                bo = true;
                b.getLocation().getBlock().setType(type);
            }
        }

        if(bo) p.sendMessage(Main.prefix + "§cDu darfst hier dieser Region keine Flüssigkeit entnehmen!");
    }


}
