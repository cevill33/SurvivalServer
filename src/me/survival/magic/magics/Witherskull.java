package me.survival.magic.magics;

import me.survival.Main;
import me.survival.magic.MagicManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;

/**
 * Created by mariusk on 25.06.2016.
 */
public class Witherskull {

    public static int cooldown = 60;

    public static void fire(Player p) {
        Fireball f = p.launchProjectile(WitherSkull.class);
        if(!p.getWorld().getPVP()) {
            f.setYield(0);
            p.sendMessage(MagicManager.prefix + "§7Da hier kein §4PvP §7erlaubt ist macht der WitherKopf keinen Schaden!");
        }
        MagicManager.startLoadinMana(p);		//Hier Kommt der Code:

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.main, new Runnable() {

            @Override
            public void run() {

                if(f != null) {
                    f.remove();
                }


            }
        },20*20);


    }
}
