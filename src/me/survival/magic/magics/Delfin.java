package me.survival.magic.magics;

import me.survival.Main;
import me.survival.magic.MagicManager;
import me.survival.objects.Particle;
import me.vetoxapi.objects.AntiCheat;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by mariusk on 09.06.2016.
 */
public class Delfin {

    public static int cooldown = 40;

    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);
        final int ID[] = new int[1];
        AntiCheat.addFlying(p, "delfin");

        ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
            int step = 0;

            @Override
            public void run() {
                step++;
                Particle particle = new Particle(EnumParticle.WATER_SPLASH, p.getLocation(), true, 0.75f, 0.75f, 0.75f, 0, 10);
                particle.sendPlayer(p);
                for(Entity near : p.getNearbyEntities(15, 10, 15)) {
                    if(near instanceof Player) {
                        Player n = (Player) near;
                        particle.sendPlayer(n);
                    }
                }

                if(p.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
                    Vector v = p.getLocation().getDirection().multiply(1.2D).setY(1.2D);
                    p.setVelocity(v);
                }

                if(step == 30) {
                    Bukkit.getScheduler().cancelTask(ID[0]);
                    AntiCheat.removeFlying(p, "delfin");
                }

            }
        },10,10);


    }
}