package me.survival.listener;

        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.BlockFromToEvent;
        import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * Created by Jakob on 14.04.2016.
 */
public class Listener_WaterFlowEvent implements Listener{

    @EventHandler
    public void on2(BlockFromToEvent e) {
        if(e.getBlock().getWorld().getName().startsWith("Farmworld")) return;
        e.setCancelled(true);
    }

}
