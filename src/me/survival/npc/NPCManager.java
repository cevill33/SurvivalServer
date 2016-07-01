package me.survival.npc;

import org.bukkit.entity.Player;

/**
 * Created by Jakob on 16.06.2016.
 */
public class NPCManager {

    public static void sendOwn(Player p, String text) {
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage(p.getDisplayName() + "§8§l: §f" + text);
    }

    public static void sendNPC(Player p, int page, int steps, String name, String text) {
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("§8" + page + "/" + steps + " §9" + name + " §8§l§: §f" + text);
    }







}
