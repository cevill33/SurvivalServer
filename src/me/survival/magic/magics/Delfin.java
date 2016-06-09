package me.survival.magic.magics;

import me.survival.Main;
import me.survival.magic.MagicManager;
import me.survival.methods.Particle;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by mariusk on 09.06.2016.
 */
public class Delfin {

    public static int cooldown = 35;
    public static int Jump = 0;
    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);

        //Hier Kommt der Code:
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.main, new Runnable() {
            @Override
            public void run() {
                Particle particle = new Particle(EnumParticle.WATER_SPLASH, p.getLocation(), true, 0.75f, 0.75f, 0.75f, 0, 10);
                particle.sendAll();
                if(p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
                    if(Jump==10){
                        Jump=0;
                        return;
                    }
                    Vector v = p.getLocation().getDirection().multiply(1D).setY(1D);
                    p.setVelocity(v);
                    Jump++;
                }
            }
        },10,10);
    }
}
