package me.survival.magic.magics;

import me.survival.magic.MagicManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by mariusk on 08.06.2016.
 */
public class Freeze {

    public static int cooldown = 45;

    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);

        //Hier Kommt der Code:
        int x = p.getLocation().getBlockX();
        int z = p.getLocation().getBlockZ();
        for(Entity o : p.getNearbyEntities(4, 4, 4)){
            if(o instanceof Player) {
                Player player = (Player) o;
                PotionEffect e = new PotionEffect(PotionEffectType.SLOW, 200, 2);
                player.addPotionEffect(e);
            }
        }
    }

}

