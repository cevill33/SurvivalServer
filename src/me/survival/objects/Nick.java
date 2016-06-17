package me.survival.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 17.06.2016.
 */
public class Nick {

    public static List<Nick> nicks = new ArrayList<>();
    private String playerskin;
    private String playername;

    public Nick(String playerskin, String playername) {
        this.playerskin = playerskin;
        this.playername = playername;
    }

    public String getPlayerskin() {
        return playerskin;
    }

    public String getPlayername() {
        return playername;
    }

    public static void registerNicks() {
        new Nick("sade1212", "xXPvPXx");
    }
}
