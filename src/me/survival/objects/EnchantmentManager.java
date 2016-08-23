package me.survival.objects;

import org.bukkit.enchantments.Enchantment;

/**
 * Created by mariusk on 21.08.2016.
 */
public class EnchantmentManager {

    private int level;
    private Enchantment ench;

    public EnchantmentManager(Enchantment ench,int level){
        this.level = level;
        this.ench = ench;
    }

    public Enchantment getEnch() {
        return ench;
    }

    public int getLevel() {
        return level;
    }

}
