package me.survival.listener;

import me.survival.commands.Command_Stats;
import me.vetoxapi.event.NewTimeEvent;
import me.vetoxapi.event.TimeState;
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

            //Test:
            //TODO: Test it
            File f = new File("plugins/NewDay");
            if(!f.exists()) {
                f.mkdir();
            }
        }




    }







}

