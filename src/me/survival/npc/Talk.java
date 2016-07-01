package me.survival.npc;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Jakob on 01.07.2016.
 */
public class Talk {

    public static HashMap<UUID, Talk> talk = new HashMap<>();
    private String name;
    private int step;

    public Talk(String name, int step) {
        this.name = name;
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void highStep() {
        this.step++;
    }
}
