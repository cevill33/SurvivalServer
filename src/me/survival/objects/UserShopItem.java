package me.survival.objects;

import me.survival.usershop.UserShop;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Created by mariusk on 22.08.2016.
 */
public class UserShopItem {

    private Material mat;
    private int menge;
    private int kosten;
    private int id;
    private int shortid;
    private List<String> lore;
    private List<EnchantmentManager> ench;
    private int amount;
    private int durability;

    public UserShopItem(ItemStack i){
        String s = UserShop.getItemStackString(i);
        this.shortid = i.getData().getData();
        this.id = i.getTypeId();
        this.kosten = Integer.parseInt(i.getItemMeta().getLore().get(0).replace("§6Kosten: ",""));
        this.menge = Integer.parseInt(i.getItemMeta().getLore().get(1).replace("§6Menge im Shop: ",""));
        this.amount = i.getAmount();
        this.durability = i.getDurability();
        this.ench = UserShop.getEnchantments(i);
        this.lore = i.getItemMeta().getLore();
        this.mat = i.getType();
    }

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Material getMaterial() {
        return mat;
    }

    public int getDurability() {
        return durability;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public int getKosten() {
        return kosten;
    }

    public int getMenge() {
        return menge;
    }

    public int getShortid() {
        return shortid;
    }

    public List<EnchantmentManager> getEnch() {
        return ench;
    }

    public List<String> getLore() {
        return lore;
    }
}
