package me.survival.methods;

import me.survival.api.ItemBuilder;
import me.survival.magic.MagicManager;
import me.survival.objects.Sword;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jakob on 09.06.2016.
 */
public class Kit {

    public static void standartKit(Player p) {
        ItemStack spitzhacke = new ItemBuilder(Material.STONE_PICKAXE).build();
        ItemStack food = new ItemStack(Material.COOKED_FISH, 64);
        ItemStack food2 = new ItemStack(Material.COOKED_FISH, 64);
        ItemStack helm = new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.AQUA).build();
        ItemStack harnisch = new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.AQUA).build();
        ItemStack hose = new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.AQUA).build();
        ItemStack schuhe = new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.AQUA).build();



        p.getInventory().setHelmet(helm);
        p.getInventory().setChestplate(harnisch);
        p.getInventory().setLeggings(hose);
        p.getInventory().setBoots(schuhe);
        p.getInventory().addItem(Sword.getSword(p));
        p.getInventory().addItem(MagicManager.stick);
        p.getInventory().addItem(spitzhacke);
        p.getInventory().addItem(food);
        p.getInventory().addItem(food2);

    }


}
