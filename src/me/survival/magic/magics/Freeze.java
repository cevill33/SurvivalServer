package me.survival.magic.magics;

import me.survival.magic.MagicManager;
import org.bukkit.Bukkit;
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
        for(Player o : Bukkit.getOnlinePlayers()){
            if(o==p){
                return;
            }
            int Blockx = o.getLocation().getBlockX();
            int Blockz = o.getLocation().getBlockZ();
            if(((Blockx == x) && (Blockz == z)) || ((Blockx == x+1) && (Blockz == z+1))
                    || ((Blockx == x+1) && (Blockz == z-1)) || ((Blockx == x-1) && (Blockz == z+1)
                    || ((Blockx == x+1) && (Blockz == z))) || ((Blockx == x-1) && (Blockz == z))
                    || ((Blockx == x) && (Blockz == z+1)) || ((Blockx == x) && (Blockz == z-1))
                    || ((Blockx == x-1) && (Blockz == z-1))|| ((Blockx == x+2) && (Blockz == z+2))
                    || ((Blockx == x+2) && (Blockz == z-2)) || ((Blockx == x-2) && (Blockz == z+2)
                    || ((Blockx == x+2) && (Blockz == z))) || ((Blockx == x-2) && (Blockz == z))
                    || ((Blockx == x) && (Blockz == z+2)) || ((Blockx == x) && (Blockz == z-2))
                    || ((Blockx == x-2) && (Blockz == z-2))
                    || ((Blockx == x+3) && (Blockz == z-3)) || ((Blockx == x-3) && (Blockz == z+3)
                    || ((Blockx == x+3) && (Blockz == z))) || ((Blockx == x-3) && (Blockz == z))
                    || ((Blockx == x) && (Blockz == z+3)) || ((Blockx == x) && (Blockz == z-3))
                    || ((Blockx == x-3) && (Blockz == z-3))) {
                PotionEffect e = new PotionEffect(PotionEffectType.SLOW, 200, 2);
                o.addPotionEffect(e);
            }
        }

    }
}
