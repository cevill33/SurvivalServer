package me.survival.npc;

import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.nation.Nation;
import me.vetoxapi.mongodb.DBVetox;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by mariusk on 16.06.2016.
 */
public class King {

    public static void openNationCooseGui(Player p) {
        Inventory inv = p.getServer().createInventory(null,27,"§aWähle deine Nation/Rasse!");
        inv.setItem(12, new ItemBuilder(Material.PAPER).setDiplayname(Nation.N1.getName()).build());
        inv.setItem(14, new ItemBuilder(Material.PAPER).setDiplayname(Nation.N2.getName()).build());
        inv.setItem(26, new ItemBuilder(Material.BARRIER).setDiplayname(Nation.N2.getName()).build());
        p.openInventory(inv);
    }

    public static void onNationChooseGuiKlick(Player p, int slot, ItemStack stack) {

        if(slot == 12) {
            Nation n = Nation.findByString(stack.getItemMeta().getDisplayName());
            new DBVetoxPlayer(p.getUniqueId().toString()).setObject("nation", n.getName());
            VetoxPlayer.stats.get(p.getUniqueId()).setNation(n.getName());
            DBVetox vetox = new DBVetox();
            p.sendMessage(Nation.prefix + "§aDu bist nun ein Mitglied der Nation §f" + n.getName() + "§a!");

            if(vetox.getNation1Player() < vetox.getNation2Player()) {
                MoneyManager.addMoney(p.getUniqueId(), 100);
                p.sendMessage(Nation.prefix + "§aZusätzlich bekommst du §2100Coins§a.");
            }
            return;
        }

        if(slot == 14) {
            Nation n = Nation.findByString(stack.getItemMeta().getDisplayName());
            new DBVetoxPlayer(p.getUniqueId().toString()).setObject("nation", n.getName());
            VetoxPlayer.stats.get(p.getUniqueId()).setNation(n.getName());
            DBVetox vetox = new DBVetox();
            p.sendMessage(Nation.prefix + "§aDu bist nun ein Mitglied der Nation §f" + n.getName() + "§a!");

            if(vetox.getNation2Player() < vetox.getNation1Player()) {
                MoneyManager.addMoney(p.getUniqueId(), 100);
                p.sendMessage(Nation.prefix + "§aZusätzlich bekommst du §2100Coins§a.");
            }
            return;
        }

        if(stack.getType().equals(Material.BARRIER)) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.BLAZE_HIT, 1, 1);
        }
    }

    public static void openKingGUI(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(null, 27, "§aDein König:");
        
    }

    public static void onKingKlick(Player p, String kingname) {
        VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
        if(vP.getNation() == null) {
            if(!Talk.talk.containsKey(p.getUniqueId())) {
                Talk.talk.put(p.getUniqueId(), new Talk(kingname, 0));
            }
            Talk t = Talk.talk.get(p.getUniqueId());
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
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Okay kein Problem. Du weißt aber schon, wenn du einer Nation beitretest, dass du dann auch gegen die gegnerische Nation" +
                            " kämpfen musst.");
                    t.highStep();
                    break;
                case 3:
                    NPCManager.sendOwn(p, "Na klar, deswegen bin ich ja auch hier. Nur noch eine kleine Frage: Wo genau werde ich dann kämpfen?");
                    t.highStep();
                    break;
                case 4:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Es ist unterschiedlich. Normalerweise kannst du nur um 18:00-19:00 mit §a/nation fight§f kämpfen. Aber ");//TODO Termine, Commands .. anpassenAber..
                    t.highStep();
                    break;
                case 5:
                    NPCManager.sendNPC(p, t.getStep() + 1, steps, kingname, "Klicke nun nochmals auf mich um eine Nation auszuwählen, wenn du einer bestimmten Nation beitretest bekommst du eine Belohnung." +
                            " Bei welcher Nation du die Belohnung  bekommst wirst du sehen, wenn du nun auf mich klickst.");
                    t.highStep();
                    break;
                case 6:
                    openNationCooseGui(p);
                    break;
                default:
                    openNationCooseGui(p);
                    break;


            }
            return;
        } else {

            //If Player is in a Nation:

        }
    }


}
