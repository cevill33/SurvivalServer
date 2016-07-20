package me.survival.nation;

import me.survival.Main;
import me.survival.objects.Rangs;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jakob on 01.07.2016.
 */
public class WARS {


    static Scoreboard board = Main.main.getServer().getScoreboardManager().getMainScoreboard();
    private static List<UUID> t0 = new ArrayList<>();
    private static List<UUID> t1 = new ArrayList<>();

    static {
        Team team1 = board.getTeam("anation.n1");
        if(team1 == null) {
            board.registerNewTeam("anation.n1");
        }
        team1.setPrefix("§4✖ §f");


        Team team2 = board.getTeam("anation.n2");
        if(team2 == null) {
            board.registerNewTeam("anation.n2");
        }
        team2.setPrefix("§4✖ §f");
    }


    public static void joinNormalWar(Player p) {
        Time time = new Time(System.currentTimeMillis());
        if(time.getHours() != 18) {
            p.sendMessage(Nation.prefix + "§cDu kannst hier nur um 18:00-19:00 kämpfen.");
            return;
        }

        VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
        if(vP.getNation() == null) {
            p.sendMessage(Nation.prefix + "§cDu bist in keiner Nation, trete einer bei indem du zum König gehst und eine anforderst!");
            return;
        }

        Nation nation = Nation.findByString(vP.getNation());
        p.teleport(nation.getKlassicwarlocation());
    }

    public static void onJoin(Player p, Nation nation) {

        //Wenn in Nation1
        if(Nation.values()[0] == nation) {
            t0.add(p.getUniqueId());
        }

        //Wenn in Nation2
        else {
            t1.add(p.getUniqueId());
        }



    }

    //If Player is in Nation1
    public void sendTabNation1(Player p) {

        Team team = board.getTeam("anation.n1");
        team.addPlayer(p);

        for(UUID enemyuuid : t1) {
            Player enemy = Bukkit.getPlayer(enemyuuid);
            enemy.setScoreboard(board);

        }



    }




}
