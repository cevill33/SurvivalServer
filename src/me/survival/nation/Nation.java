package me.survival.nation;


import me.survival.Main;
import me.survival.npc.King;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jakob on 31.05.2016.
 */

public enum Nation {


    N1("Aincrad", King.descn1, new Location(Bukkit.getWorld("Fight"),1,7,1)),
    N2("Trivia", King.descn2, new Location(Bukkit.getWorld("Fight"),1,7,1));

    static Scoreboard board = Main.main.getServer().getScoreboardManager().getMainScoreboard();
    private String name;
    private List<String> desc;
    private Location klassicwarlocation;
    public static final String prefix = "§a[§7Nation§a] §f";

    Nation(String name, List<String> desc, Location klassicwarlocation) {
        this.name = name;
        this.desc = desc;
        this.klassicwarlocation = klassicwarlocation;
    }

    public static Nation findByString(String s) {
        if(N1.getName().equals(s)) {
            return N1;
        }
        if(N2.getName().equals(s)) {
            return N2;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public static HashMap<Player,Integer> visi = new HashMap<>();

    public Location getKlassicwarlocation() {
        return klassicwarlocation;
    }


    public static void joinFight(Player p) {
        VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
        if(vP.getNation() == null) {
            p.sendMessage(Nation.prefix + "§cDu musst zuerst einer Nation beitreten.");
            p.sendMessage(Nation.prefix + "§cRede dafür mit dem Bürgermeister!");
            return;
        }

        Date date = new Date();


        //TODO: == to !=
        if(date.getHours() == 18) {
            p.sendMessage(Nation.prefix + "§cDu kannst nur von §f18:00 - 19:00 §ckämpfen.");
            p.sendMessage(Nation.prefix + "§cZurzeit ist es: §f" + date.getHours() + ":" + date.getMinutes() + "§c.");
            return;
        }

        Location loc = null;
        if(vP.getNation().equals("Aincrad")) loc = NationArena.arenas.get(0).getN1();
        if(vP.getNation().equals("Trivia"))  loc = NationArena.arenas.get(0).getN2();
        p.teleport(loc);
        p.sendMessage(Nation.prefix + "§7Viel Glück beim Kämpf.");
    }

    public static void sendNationName(Player p, Nation nation) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective obj = sb.getObjective("obj" + nation.getName());
        if(obj == null) obj = sb.registerNewObjective("obj" + nation.getName(), "dummy");
        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        obj.setDisplayName("§7" + nation.getName());
    }

}
