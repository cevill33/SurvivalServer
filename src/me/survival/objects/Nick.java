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
        nicks.add(new Nick("sade1212", "xXPvPPlayerXx"));
        nicks.add(new Nick("Fox", "FireFox306"));
        nicks.add(new Nick("Mzilla", "WulfPlayer"));
        nicks.add(new Nick("Snake", "Phyton"));
        nicks.add(new Nick("TheStabbyBunny", "BabyXXL"));
        nicks.add(new Nick("Snipex01", "zGreeiden"));
        nicks.add(new Nick("vexx21322", "IchBinEinMonster"));
    }
}
