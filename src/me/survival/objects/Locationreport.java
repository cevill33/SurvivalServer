package me.survival.objects;

import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by mariusk on 30.06.2016.
 */
public class LocationReport {

    private String grund;
    private Location location;

    public static ArrayList<LocationReport> locationreports = new ArrayList<>();

    public LocationReport(Location loc, String reason){
        location = loc;
        grund = reason;
        locationreports.add(this);
    }

    public static Location getLocation(LocationReport lr){
        return lr.location;
    }

    public static String getReason(LocationReport lr){
        return lr.grund;
    }

}
