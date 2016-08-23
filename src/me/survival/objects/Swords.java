package me.survival.objects;

import me.survival.api.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jakob on 03.04.2016.
 */
public enum Swords {

    LVL0(0, new ItemBuilder(Material.WOOD_SWORD).setDiplayname("meta").build(), Arrays.asList("Das ist Level 0 du bist ein Fehler im System!")),

    LVL1(1, new ItemBuilder(Material.STONE_SWORD).setDiplayname("meta").build(),
            Arrays.asList("§6AufragsName: §fPionier", "§7Reite mit dem Pferd welches", "§7du mit /ride bekommst 10.000 Blöcke.")),

    LVL2(2, new ItemBuilder(Material.WOOD_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).setDiplayname("meta").build(),
    Arrays.asList("§6AufragsName: §fKaufman", "§7Zahle mit 300 Coins ;D")),

    LVL3(3, new ItemBuilder(Material.STONE_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),
    Arrays.asList("§6AufragsName: §fLevler", "§7Verbringe 3 Stunden auf dem Server!")),

    LVL4(4, new ItemBuilder(Material.DIAMOND_AXE).setDiplayname("meta").build(),
    Arrays.asList("§6AufragsName: §fKaufman2", "§7Zahler 300 Coins!")),

    LVL5(5, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 1).build(),
    Arrays.asList("§6AufragsName: §fKein Neuling", "§7Verbinge 7 Stunden auf dem Server!")),

    LVL6(6, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 2).build(),
            Arrays.asList("§6AufragsName: §fForscher", "§7Bringe mit 64 Stück Eisen (Gebraten)!", "§7Tipp: Bei der nächsten Mission",
                    "§7musst du 10mal gevotet haben.", "§7Also beginne schon jetzt!")),

    LVL7(7, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 3).build(),
            Arrays.asList("§6AufragsName: §fFreund", "§7Vote 10mal für den Server!", "§7--> /vote")),

    LVL8(8, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 4).build(),
            Arrays.asList("§6AufragsName: §fStammspieler", "§7Verbinge 30 Stunden auf dem Server!")),

    LVL9(9, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 4).
            addEnchantment(Enchantment.LOOT_BONUS_MOBS, 2).build(),
            Arrays.asList("§6AufragsName: §fJäger", "§7Töte 300 Spinnen!")),

    LVL10(10, new ItemBuilder(Material.IRON_SWORD).setDiplayname("meta").addEnchantment(Enchantment.DAMAGE_ALL, 5).
            addEnchantment(Enchantment.LOOT_BONUS_MOBS, 2).build(),
            Arrays.asList("§6AufragsName: §fVerzaubern", "§7Bringe mir 32 Enchanter ;D!"));



    private int lvl;
    private ItemStack itemStack;
    private List<String> description;


    Swords(int lvl, ItemStack itemStack, List<String> description) {
        this.lvl = lvl;
        this.itemStack = itemStack;
        this.description = description;
    }

    public static Swords getByLevel(int lvl) {
        for (Swords s : Swords.values()) {
            if(s.getLvl() == lvl) {
                System.out.println("Lvl " + lvl + " gefunden.");
                return s;
            }
        }
        return Swords.LVL1;
    }



    public int getLvl() {
        return lvl;
    }

    public ItemStack getItemStack(String name, List<String> description) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§f§6" + name);
        meta.setLore(description);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public List<String> getDescription() {
        return description;
    }

    public String getMethod() {
        return "m" + getLvl();
    }
}
