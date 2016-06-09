package me.survival.magic.magics;

import me.survival.magic.MagicManager;
import me.vetoxapi.objects.AntiCheat;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Created by mariusk on 07.06.2016.
 */
public class FlyingRocket {

    public static int cooldown = 35;

    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);

        //TODO FallDamage aus!
        //Hier Kommt der Code:
        Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
        FireworkEffect effect = FireworkEffect.builder()
                .withColor(Color.PURPLE)
                .flicker(true)
                .trail(true)
                .withFade(Color.PURPLE)
                .with(FireworkEffect.Type.BALL)
                .with(FireworkEffect.Type.BURST)
                .build();
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(effect);
        meta.setPower(3);
        firework.setFireworkMeta(meta);
        firework.setPassenger(p);
        AntiCheat.addFlying(p, "rocket", 20*7);
    }
}
