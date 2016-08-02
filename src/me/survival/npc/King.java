package me.survival.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import me.survival.api.ItemBuilder;
import me.survival.nation.Nation;
import me.survival.nation.NationManager;
import me.vetoxapi.mongodb.DBVetox;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class King
{
    public static List<String> descn1 = new ArrayList();
    public static List<String> descn2 = new ArrayList();
    public static ArrayList<String> beschwerdeeinreichen;
    public static ArrayList<String> beschwerden;

    public static void openNationCooseGui(Player p)
    {
        Inventory inv = p.getServer().createInventory(null, 27, "§aWähle deine Nation/Rasse!");
        ItemBuilder b1 = new ItemBuilder(Material.PAPER).setDiplayname(Nation.N1.getName());
        ItemBuilder b2 = new ItemBuilder(Material.PAPER).setDiplayname(Nation.N2.getName());

        DBVetox vetox = new DBVetox();

        if (vetox.getNation1Player() < vetox.getNation2Player()) {
            List list = Nation.N1.getDesc();
            list.add("§fHier bekommst du §6100Coins§f!");
            b1.setLoreInArrayList(list);
            b2.setLoreInArrayList(Nation.N2.getDesc());
        }

        if (vetox.getNation2Player() < vetox.getNation1Player()) {
            List list = Nation.N2.getDesc();
            list.add("§fHier bekommst du §6100Coins§f!");
            b2.setLoreInArrayList(list);
            b1.setLoreInArrayList(Nation.N1.getDesc());
        }

        inv.setItem(12, b1.build());
        inv.setItem(14, b2.build());
        inv.setItem(26, new ItemBuilder(Material.BARRIER).setDiplayname("§4Schließen.").build());
        p.openInventory(inv);
    }

    public static void onNationChooseGuiKlick(Player p, int slot, ItemStack stack)
    {
        if (slot == 12) {
            Nation n = Nation.findByString(stack.getItemMeta().getDisplayName());
            new DBVetoxPlayer(p.getUniqueId().toString()).setObject("nation", n.getName());
            ((VetoxPlayer)VetoxPlayer.stats.get(p.getUniqueId())).setNation(n.getName());
            DBVetox vetox = new DBVetox();
            vetox.setNation1Player(vetox.getNation1Player() + 1);

            p.sendMessage("§a[§7Nation§a] §f§aDu bist nun ein Mitglied der Nation §f" + n.getName() + "§a!");

            if (vetox.getNation1Player() < vetox.getNation2Player()) {
                MoneyManager.addMoney(p.getUniqueId(), 100.0D);
                p.sendMessage("§a[§7Nation§a] §f§aZusätzlich bekommst du §2100Coins§a.");
            }
            p.closeInventory();
            return;
        }

        if (slot == 14) {
            Nation n = Nation.findByString(stack.getItemMeta().getDisplayName());
            new DBVetoxPlayer(p.getUniqueId().toString()).setObject("nation", n.getName());
            ((VetoxPlayer)VetoxPlayer.stats.get(p.getUniqueId())).setNation(n.getName());
            DBVetox vetox = new DBVetox();
            vetox.setNation2Player(vetox.getNation2Player() + 1);
            p.sendMessage("§a[§7Nation§a] §f§aDu bist nun ein Mitglied der Nation §f" + n.getName() + "§a!");

            if (vetox.getNation2Player() < vetox.getNation1Player()) {
                MoneyManager.addMoney(p.getUniqueId(), 100.0D);
                p.sendMessage("§a[§7Nation§a] §f§aZusätzlich bekommst du §2100Coins§a.");
            }
            p.closeInventory();
            return;
        }

        if (stack.getType().equals(Material.BARRIER)) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);
        }
    }

    public static void openKingGUI(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(null, 27, "§aDein König:");

        inv.setItem(0, new ItemBuilder(Material.SAPLING).setDiplayname("§aKönigwahl").setLore(new String[] { "§7Wähle deinen neuen König" }).build());
        //inv.setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, 1).setDiplayname("§cBeschwerde einreichen").setLore(new String[] { "§7Du findest, dass der", "§7König sich nicht richtig", "§7verhält? Reiche eine ", "§7Beschwerde ein!", "§cMissbrauch/Spam strafbar" }).build());
        //inv.setItem(9, new ItemBuilder(Material.getMaterial(351), 1, 15).setDiplayname("§7Nation wechseln").setLore(new String[] { "§7Du bist nicht mehr", "§7zufrieden in deiner Nation?", "§7Wechsel sie einfach!" }).build());
        inv.setItem(13, new ItemBuilder(Material.GOLD_HELMET).setDiplayname("§6Dein König").build());
        inv.setItem(21, new ItemBuilder(Material.DIAMOND_SWORD).setDiplayname("§9Punkte von " + Nation.N1.getName()).build());
        inv.setItem(23, new ItemBuilder(Material.DIAMOND_SWORD).setDiplayname("§9Punkte von " + Nation.N2.getName()).build());
    }

    public static void klickKingGUI(Player p, ItemStack i) {
        Material m = i.getType();
        if (m.equals(Material.SAPLING)) {
            Inventory localInventory = Bukkit.getServer().createInventory(null, 27, "§aNeuer König?");
        }
        else if (m.equals(Material.SKULL_ITEM)) {
            beschwerdeeinreichen.add(p.getName());
            p.sendMessage("§a[§7Nation§a] §fSchreibe deine Beschwerde in den Chat.");
        } /*else if (i.equals(new ItemBuilder(Material.getMaterial(351), 1, 15).setDiplayname("§7Nation wechseln").setLore(new String[] { "§7Du bist nicht mehr", "§7zufrieden in deiner Nation?", "§7Wechsel sie einfach!" }).build())) {
            NationManager.openCooseInventory(p);
        }*/
    }

    public static void onKingKlick(Player p, String kingname) {
        VetoxPlayer vP = (VetoxPlayer)VetoxPlayer.stats.get(p.getUniqueId());
        if (vP.getNation() == null) {
            if (!Talk.talk.containsKey(p.getUniqueId())) {
                Talk.talk.put(p.getUniqueId(), new Talk(kingname, 0));
            }
            Talk t = (Talk)Talk.talk.get(p.getUniqueId());
            int steps = 7;
            switch (t.getStep()) {
                case 0:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Hi ich bin der König von diesem Land!");
                    t.highStep();
                    break;
                case 1:
                    NPCManager.sendOwn(p, "Gut! Denn ich bin hier um einer Nation beizutreten.");
                    t.highStep();
                    break;
                case 2:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Okay kein Problem. Du weißt aber schon, wenn du einer Nation beitretest, dass du dann auch gegen die gegnerische Nation kämpfen musst.");

                    t.highStep();
                    break;
                case 3:
                    NPCManager.sendOwn(p, "Na klar, deswegen bin ich ja auch hier. Nur noch eine kleine Frage: Wo genau werde ich dann kämpfen?");
                    t.highStep();
                    break;
                case 4:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Es ist unterschiedlich. Normalerweise kannst du nur um 18:00-19:00 mit §a/nation fight§f kämpfen. Aber ");
                    t.highStep();
                    break;
                case 5:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Klicke nun nochmals auf mich um eine Nation auszuwählen, wenn du einer bestimmten Nation beitretest bekommst du eine Belohnung. Bei welcher Nation du die Belohnung  bekommst wirst du sehen, wenn du nun auf mich klickst.");

                    t.highStep();
                    break;
                case 6:
                    openNationCooseGui(p);
                    break;
                default:
                    openNationCooseGui(p);
            }

            return;
        }

        NPCManager.sendNPC(p, 1, 1, kingname, "Hi ich bin der Bürgermeister, bald werden Nation-Kriege verfügbar sein.");
    }

    static
    {
        descn1.add("§7Das ganze ist nur");
        descn1.add("§7Test. Weuk....");
        descn2.add("§7Das ganze ist nur");
        descn2.add("§7Test. Weuk....");

        beschwerdeeinreichen = new ArrayList();
        beschwerden = new ArrayList();
    }
}