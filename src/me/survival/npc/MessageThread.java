package me.survival.npc;

import org.bukkit.entity.Player;

/**
 * Created by Jakob on 16.06.2016.
 */
public class MessageThread  implements Runnable{


    private Thread thread;
    private Player p;
    private char[] text;
    private String npcname;

    public MessageThread(String npcname, String text, Player p) {
        this.npcname = npcname;
        this.text = text.toCharArray();
        this.p = p;
    }


    @Override
    public void run() {

        String t = "";

        for(int i = 1; i < text.length; i++) {
            t = t + text[i];
            sendPlaceHolder(p);
            p.sendMessage("§9" + npcname + "§8§l:§f " + t);


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public void sendPlaceHolder(Player p) {
        for(int i = 0; i <= 30; i++) {
            p.sendMessage(" ");
        }
    }
}
