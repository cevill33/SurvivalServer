package me.survival.nation;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.nation.NationManager;import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jakob on 31.05.2016.
 */

public enum Nation {


    N1("Aincrad", Arrays.asList("")),
    N2("Trivia", Arrays.asList(""));

    private String name;
    private List<String> desc;

    Nation(String name, List<String> desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public static HashMap<Player,Integer> visi = new HashMap<>();

    @Deprecated
    public static void nationextraevent(Player p,Block b) {
        if (p.isSneaking()) {

            if (NationManager.isPlayerInNation(p,N1)) {
                if(b.getType() == Material.CROPS){
                    b.setType(Material.CROPS);
                    b.getLocation().getWorld().dropItem(b.getLocation(), new ItemStack(Material.WHEAT));
                    b.getLocation().getWorld().dropItem(b.getLocation(), new ItemStack(Material.CROPS));
                }
            }

            if (NationManager.isPlayerInNation(p,N2)) {
                if(visi.get(p)==0) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 8, 3));
                    visi.put(p, 60);
                    Bukkit.getScheduler().runTaskTimerAsynchronously(Main.main, new Runnable() {
                        @Override
                        public void run() {
                            visi.put(p, visi.get(p) - 1);
                            if (visi.get(p) == 0) {
                                return;
                            }
                        }
                    }, 20, 20);
                }
            }

        }
    }

}
