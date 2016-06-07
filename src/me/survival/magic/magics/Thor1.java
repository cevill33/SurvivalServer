package me.survival.magic.magics;


import me.vetoxapi.VetoxAPI;
import me.vetoxapi.objects.AntiCheat;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.survival.magic.MagicManager;

public class Thor1 {

    public static int cooldown = 40;

    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);
        //Hier Kommt der Code:

        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 1);
        for(Entity enitiy : p.getNearbyEntities(3, 1, 3)) {

            if(enitiy instanceof Player) {
                ((Player) enitiy).playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
                enitiy.setVelocity(enitiy.getLocation().getDirection().multiply(-1.5D).setY(1));
                AntiCheat.addFlying(p, "thorkick");
            }


        }







    }















}
