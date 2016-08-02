package me.survival.listener;

import me.survival.commands.Command_Stats;
import me.survival.nation.Nation;
import me.vetoxapi.VetoxAPI;
import me.vetoxapi.event.NewTimeEvent;
import me.vetoxapi.event.TimeState;
import me.vetoxapi.methods.NationPoints;
import me.vetoxapi.mongodb.DBVetox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

/**
 * Created by Jakob on 17.06.2016.
 */
public class Listener_TimeEvent implements Listener{


    @EventHandler
    public void onChange(NewTimeEvent e) {

        //New Day:
        if(e.getTimeState().equals(TimeState.NEW_DAY)) {
            Command_Stats.cooldown.clear();
        }

        if(e.getTimeState().equals(TimeState.NEW_HOUR)) {
            if(e.getValue() == 18) {
                NationPoints.now = new NationPoints();
            }

            if(e.getValue() == 19) {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.getWorld().getName().equals("Fight")) {
                        all.teleport(VetoxAPI.spawn);
                    }
                }

                NationPoints now = NationPoints.now;
                if(now.getN1() > now.getN2()) {
                    new DBVetox().addNation1Point();
                    Bukkit.broadcastMessage(Nation.prefix + "§aDie Nation §f" + Nation.N1.getName() + " §ahat den heutigen kampf gewonnen!");
                }
                if(now.getN2() > now.getN1()) {
                    Bukkit.broadcastMessage(Nation.prefix + "§aDie Nation §7" + Nation.N2.getName() + " §ahat den heutigen kampf gewonnen!");
                    new DBVetox().addNation2Point();
                }
                if(now.getN1() == now.getN2()) Bukkit.broadcastMessage(Nation.prefix + "§aKeiner der Nationen konnte sich heute als die bessere darstellen, da sie UNENTSCHIDEN gespielt haben.");
            }

        }




    }







}

