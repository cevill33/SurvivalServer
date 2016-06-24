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
        new Nick("sade1212", "xXPvPPlayerXx");
        new Nick("Fox", "FireFox306");
        new Nick("Mzilla", "WulfPlayer");
        new Nick("Snake", "Phyton");
        new Nick("TheStabbyBunny", "BabyXXL");
        new Nick("Snipex01", "zGreeiden");
        new Nick("vexx21322", "IchBinEinMonster");
        new Nick("R2_D2_2002", "R2_D2_2002");
    }
}
