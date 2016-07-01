package me.survival.listener;

import me.survival.npc.King;
import me.vetoxapi.mongodb.DBVetox;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by mariusk on 16.06.2016.
 */
public class Listener_NPCClickEvent implements Listener {


    @EventHandler
    public void onClick(NPCRightClickEvent e){
        String name = e.getNPC().getName();
        if(name.equals(new DBVetox().getNation1King())) {
            King.onKingKlick(e.getClicker(), name);
        }
    }
}
