package me.survival.magic.magics;

import me.survival.Main;
import me.survival.magic.MagicManager;
import me.survival.nation.Nation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by mariusk on 07.06.2016.
 */
public class Pheonix {

    public static int cooldown = 50;
    public static List<Arrow> arrows = new ArrayList<>();
    
    
    public static void fire(Player p) {

        //TODO: Cancell Damage
        if(!p.getWorld().getPVP()) {
            p.sendMessage(Main.prefix + "§cNur in PvP Welt aktiv.");
            return;
        }
        Block block = p.getTargetBlock((Set<Material>) null, 100);
        //Radius 1
        MagicManager.startLoadinMana(p);
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



        arrows.add(arrow);
        arrows.add(arrow2);
        arrows.add(arrow3);
        arrows.add(arrow4);
        arrows.add(arrow5);
        arrows.add(arrow6);
        arrows.add(arrow7);
        arrows.add(arrow8);
        arrows.add(arrow9);
        arrows.add(arrow10);
        arrows.add(arrow11);
        arrows.add(arrow12);
        arrows.add(arrow13);
        arrows.add(arrow14);
        arrows.add(arrow15);
        arrows.add(arrow16);

        for(Arrow a : arrows) {
            a.setCritical(true);
            a.setFireTicks(50);
        }

    }
}
