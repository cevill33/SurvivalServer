package me.survival.magic.magics;

import me.survival.magic.MagicManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by mariusk on 07.06.2016.
 */
public class Pheonix {

    public static int cooldown = 50;

    public static void fire(Player p) {
        MagicManager.startLoadinMana(p);
        //Hier Kommt der Code:
        Block block = p.getTargetBlock((Set<Material>) null, 100);
        //Radius 1
        Arrow arrow = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(0,10,0), EntityType.ARROW);
        Arrow arrow2 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(0,10,2), EntityType.ARROW);
        Arrow arrow3 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(0,10,-2), EntityType.ARROW);
        Arrow arrow4 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-2,10,0), EntityType.ARROW);

        Arrow arrow5 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(1,10,1), EntityType.ARROW);
        Arrow arrow6 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-1,10,-1), EntityType.ARROW);
        Arrow arrow7 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(1,10,-1), EntityType.ARROW);
        Arrow arrow8 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-1,10,1), EntityType.ARROW);
        //Radius 2
        Arrow arrow9 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(0,10,4), EntityType.ARROW);
        Arrow arrow10 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(0,10,-4), EntityType.ARROW);
        Arrow arrow11 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-4,10,0), EntityType.ARROW);
        Arrow arrow12 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(4,10,0), EntityType.ARROW);

        Arrow arrow13 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(2,10,2), EntityType.ARROW);
        Arrow arrow14 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-2,10,-2), EntityType.ARROW);
        Arrow arrow15 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(2,10,-2), EntityType.ARROW);
        Arrow arrow16 = (Arrow) p.getWorld().spawnEntity(block.getLocation().add(-2,10,2), EntityType.ARROW);

        Bow1.arrwos.add(arrow);
        Bow1.arrwos.add(arrow2);
        Bow1.arrwos.add(arrow3);
        Bow1.arrwos.add(arrow4);
        Bow1.arrwos.add(arrow5);
        Bow1.arrwos.add(arrow6);
        Bow1.arrwos.add(arrow7);
        Bow1.arrwos.add(arrow8);
        Bow1.arrwos.add(arrow9);
        Bow1.arrwos.add(arrow10);
        Bow1.arrwos.add(arrow11);
        Bow1.arrwos.add(arrow12);
        Bow1.arrwos.add(arrow13);
        Bow1.arrwos.add(arrow14);
        Bow1.arrwos.add(arrow15);
        Bow1.arrwos.add(arrow16);
        //Update 2

    }
}
