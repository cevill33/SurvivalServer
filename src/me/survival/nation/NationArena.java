package me.survival.nation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 20.07.2016.
 */
public class NationArena {

    public static List<NationArena> arenas = new ArrayList<>();
    private int id;
    private Location center;

    public NationArena(int id, Location center) {
        this.id = id;
        this.center = center;
        arenas.add(this);
    }

    public int getId() {
        return id;
    }

    public Location getCenter() {
        return center;
    }

    //68Blocks Z-Axe

    public Location getN1() {
        return new Location(center.getWorld(), center.getX(), center.getY()+2, center.getBlockZ() + 68);
    }

    public Location getN2() {
        return new Location(center.getWorld(), center.getX(), center.getY()+2, center.getBlockZ() - 68);
    }

    public static void registerNationArenas() {
        World fight = Bukkit.getWorld("Fight");
        new NationArena(0, new Location(fight, 0, 35, 0));
    }
}
