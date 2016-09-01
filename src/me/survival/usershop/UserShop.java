package me.survival.usershop;

import chunkgs.ChunkManager;
import me.survival.Main;
import me.survival.api.ItemBuilder;
import me.survival.objects.EnchantmentManager;
import me.survival.objects.UserShopItem;
import me.vetoxapi.objects.MoneyManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import worldmanager.GsAllowedWorld;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by mariusk on 21.08.2016.
 */
public class UserShop {
    //TODO wie setze ich die Lore(0:Kosten , 1:Menge im Shop {Info in getItemStackString}) wenn ein neues item in das inventar gesetzt wird?
    public static final BlockFace[] SHOP_FACES = { BlockFace.SELF, BlockFace.DOWN, BlockFace.UP, BlockFace.EAST, BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH };
    public static ArrayList<String> editadmins = new ArrayList<>();
    //public static HashMap<String,String> editadmins2 = new HashMap<>();
    public static HashMap<String,Integer> acceptitemslot = new HashMap<>();
    public static HashMap<String,String> getCfgStringAdmin = new HashMap<>();
    //Boolean
    public static boolean isOwner(Location l,Player p){
        String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + "_" + l.getWorld().getName();
        File file = new File("plugins//UserShop//" + loc + ".yml");
        if(!file.exists()){
            p.sendMessage(Main.prefix + "Dieser Shop exestiert nicht!");
            return false;
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String owner = cfg.getString("owner");
        List<String> admins = cfg.getStringList("admins");
        if(p.getUniqueId() == UUID.fromString(owner)){
            return true;
        }
        if(admins.contains(p.getName())){
            return true;
        }
        return false;
    }
    public static boolean isAllowedUserShop(Player p,Sign sign){
        Chest b = findConnectedChest(sign);
        if(b == null){
            if(GsAllowedWorld.worlds.containsKey(p.getWorld().getName()) && worldmanager.WorldManager.protectedworlds.contains(p.getWorld().getName())) return false;

            if(ChunkManager.isForHimBuildable(p.getUniqueId().toString(), b.getLocation()) && ChunkManager.isForHimBuildable(p.getUniqueId().toString(), b.getLocation()))return false;
        }
        return true;
    }
    public static Chest findConnectedChest(Sign sign) {
        Block block = sign.getBlock();
        return findConnectedChest(block);
    }
    public static Chest findConnectedChest(Block block) {
        for (BlockFace bf : SHOP_FACES) {
            Block faceBlock = block.getRelative(bf);
            if (isChest(faceBlock)) {
                return (Chest)faceBlock.getState();
            }
        }
        return null;
    }
    public static boolean isChest(Block holder) {
        return (holder instanceof Chest/* || ((holder instanceof DoubleChest)*/);
    }
    //User
    public static void openShop(Player p,Location l){
        String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + l.getWorld().getName();
        File file = new File("plugins//UserShop//" + loc + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        Inventory inv = p.getServer().createInventory(null,27,"§aUserShop §7| §8" + loc);

        for(int i = 0;i<getContent(cfg).size();i++){
            inv.setItem(i,getContent(cfg).get(i));
        }
        p.openInventory(inv);
    }
    public static void onClickShop(Player p,String loc,ItemStack i/*,int slot*/){
        //String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + l.getWorld().getName();
        //File file = new File("plugins//UserShop//" + loc + ".yml");
        //YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        /*ItemMeta itemm = i.getItemMeta();
        List<String> lore = itemm.getLore();
        lore.set(2,"§7" + slot);
        itemm.setLore(lore);*/
        if(i.getType().equals(Material.BARRIER)){
            return;
        }
        UserShopItem item = new UserShopItem(i);
        double money = MoneyManager.getMoney(p.getUniqueId());
        if(money >= item.getKosten() && item.getMenge() >= item.getAmount()){
            Inventory inv = p.getServer().createInventory(null,InventoryType.HOPPER,"§aKaufen? §7| " + loc);
            inv.setItem(0,new ItemBuilder(Material.WOOL,1,(short)5).setDiplayname("§aJa").build());
            inv.setItem(2,i);
            inv.setItem(4,new ItemBuilder(Material.WOOL,1,(short)14).setDiplayname("§cNein").build());
        }

    }
    public static void onClickAcceptmenue(Inventory inv,ItemStack i,Player p){
        ItemStack im = inv.getItem(2);
        UserShopItem item = new UserShopItem(im);
        double money = MoneyManager.getMoney(p.getUniqueId());
        if(i.getItemMeta().getDisplayName().equals("§aJa")) {
            if (money >= item.getKosten()) {
                MoneyManager.removeMoney(p.getUniqueId(), (double) item.getKosten());
                ItemStack is = new ItemStack(item.getMaterial(),item.getAmount(),(short)item.getShortid());
                is.setDurability((short)item.getDurability());
                for(EnchantmentManager em : item.getEnch()) {
                    is.addUnsafeEnchantment(em.getEnch(),em.getLevel());
                }
                p.getInventory().addItem(is);
                p.getOpenInventory().close();
                File file = new File("plugins//UserShop//" + inv.getName().replace("§aKaufen? §7| ","") + ".yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                item.setMenge(item.getMenge()-item.getAmount());
                List<String> content = cfg.getStringList("Items");
                content.set(acceptitemslot.get(p.getName()),getShopItemString(item));
                acceptitemslot.remove(p.getName());
                cfg.set("Items",content);
                Main.saveFile(file,cfg);
            }
        }
    }
    //Save | get
    public static String getEnchantmentsToString(ItemStack i){
        String ench = "null/";
        ArrayList<EnchantmentManager> enchantments = getEnchantments(i);
        for(int s = 0;s<enchantments.size();s++){
            ench = ench + "/" + enchantments.get(s).getEnch() + ";" + enchantments.get(s).getLevel();
        }
        return ench;
    }
    public static ArrayList<EnchantmentManager> getEnchantments(ItemStack i){
        ArrayList<EnchantmentManager> enchantments = new ArrayList<>();
        Map<Enchantment, Integer> Enchants = i.getEnchantments();
        for(Enchantment ench : Enchantment.values()){
            if (Enchants.containsKey(ench)) {
                Integer level = Enchants.get(ench);
                for(int s = 1;s<10;s++){
                    if(level == s){
                        enchantments.add(new EnchantmentManager(ench,s));
                        break;
                    }
                }
            }
        }
        return enchantments;
    }
    public static String getItemStackString(ItemStack i){
        int shortid = i.getData().getData();
        int id = i.getTypeId();
        int kosten = Integer.parseInt(i.getItemMeta().getLore().get(0).replace("§6Kosten: ",""));
        int menge = Integer.parseInt(i.getItemMeta().getLore().get(1).replace("§6Menge im Shop: ",""));
        int amount = i.getAmount();
        int durability = i.getDurability();
        String ench = getEnchantmentsToString(i);
        List<String> lore = i.getItemMeta().getLore();
        String loreStr = lore.get(0);
        for(int s = 1;s<lore.size();s++){
            loreStr = loreStr + "/" + lore.get(s);
        }
        return id + ";" + shortid +"," + amount + "," + durability + "," + kosten + "," + menge + "," + loreStr + "," + ench;
    }
    public static String getShopItemString(UserShopItem i){
        int shortid = i.getShortid();
        int id = i.getId();
        int kosten = i.getKosten();
        int menge = i.getMenge();
        int amount = i.getAmount();
        int durability = i.getDurability();
        String ench = "null";
        for(EnchantmentManager em : i.getEnch()){
            ench = ench + "/" + em.getEnch() + ";" + em.getLevel();
        }
        List<String> lore = i.getLore();
        String loreStr = lore.get(0);
        for(int s = 1;s<lore.size();s++){
            loreStr = loreStr + "/" + lore.get(s);
        }
        return id + ";" + shortid +"," + amount + "," + durability + "," + kosten + "," + menge + "," + loreStr + "," + ench;
    }
    public static void enableUserShop(){
        File ordner  = new File("plugins//UserShop");

        if(!ordner.exists()){
            ordner.mkdir();
        }
    }
    public static void saveItemsInCfg(Inventory inv,String loc){
        //String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + l.getWorld().getName();
        File file = new File("plugins//UserShop//" + loc + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> content = new ArrayList<>();
        for(int i = 0;i<27;i++){
            ItemStack im = inv.getItem(i);
            if(im.getType().equals(Material.AIR) || im.getType().equals(Material.BARRIER)){
                content.add(getItemStackString(new ItemBuilder(Material.AIR).build()));
            }else{
                content.add(getItemStackString(inv.getItem(i)));
            }
        }
        cfg.set("Items",null);
        cfg.set("Items",content);
        Main.saveFile(file,cfg);
    }
    public static ArrayList<ItemStack> getContent(YamlConfiguration cfg){
        ArrayList<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < cfg.getStringList("Items").size(); i++) {
            String s = cfg.getStringList("Items").get(i);
            String[] split = s.split(",");
            String[] data = split[0].split(";");
            int mat = Integer.parseInt(data[0]);
            int shortid = Integer.parseInt(data[1]);
            int amount = Integer.parseInt(split[1]);
            short durability = Short.parseShort(split[2]);
            String kosten = "§6Kosten: " + split[3];
            int menge = Integer.parseInt(split[4]);

            short sh = (short) shortid;
            ItemStack is = new ItemStack(mat, amount, sh);

            List<String> lore = new ArrayList<>();
            for(String str : split[5].split("/")){
                lore.add(str);
            }

            String[] ench = split[6].split("/");
            for(int ii=0; ii<ench.length; ii++){
                String[] a = ench[ii].split(";");
                for(Enchantment enchs : Enchantment.values()){
                    if(enchs.toString().equals(ench)){
                        is.addUnsafeEnchantment(enchs,Integer.parseInt(a[1]));
                    }
                }
                /*switch(a[0]){
                    case "null":
                        break;
                    case "sharpness":
                        is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,Integer.parseInt(a[1]));
                        break;
                    case "protection":
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,Integer.parseInt(a[1]));
                        break;
                    case "infinity":
                        is.addUnsafeEnchantment(Enchantment.ARROW_INFINITE,Integer.parseInt(a[1]));
                        break;
                    case "fire_aspect":
                        is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT,Integer.parseInt(a[1]));
                        break;
                    case "power":
                        is.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,Integer.parseInt(a[1]));
                        break;
                    case "unbreaking":
                        is.addUnsafeEnchantment(Enchantment.DURABILITY,Integer.parseInt(a[1]));
                        break;
                    case "knockback":
                        is.addUnsafeEnchantment(Enchantment.KNOCKBACK,Integer.parseInt(a[1]));
                        break;
                    case "punch":
                        is.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,Integer.parseInt(a[1]));
                        break;
                    case "thorns":
                        is.addUnsafeEnchantment(Enchantment.THORNS,Integer.parseInt(a[1]));
                        break;
                    case "flame":
                        is.addUnsafeEnchantment(Enchantment.ARROW_FIRE,Integer.parseInt(a[1]));
                        break;
                    case "looting":
                        is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS,Integer.parseInt(a[1]));
                        break;
                    case "luck":
                        is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,Integer.parseInt(a[1]));
                        break;
                    case "projectile_protection":
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE,Integer.parseInt(a[1]));
                        break;
                }*/
            }
            ItemMeta meta = is.getItemMeta();
            meta.setLore(lore);
            is.setItemMeta(meta);
            is.setDurability(durability);
            items.add(is);
        }
        return items;
    }
    //Admin
    public static void addToAdmins(String name,String loc){
        File file = new File("plugins//UserShop//" + loc + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> admins = cfg.getStringList("admins");
        admins.add(name);
        cfg.set("admins",admins);
        Main.saveFile(file,cfg);
    }
    public static void onClickAdminMenue(Player p,ItemStack i,int itemSlot){
        if(i.getType().equals(Material.BARRIER)){
            editadmins.add(p.getName());
            p.sendMessage(Main.prefix + "§7Bitte schreibe nun den Namen des spielers in den Chat, der rechte auf deinen Shop erhalten soll.");
            p.sendMessage(Main.prefix + "§7Wenn du doch niemanden hinzufügen willst nutze die Nachricht leave.");
            p.getOpenInventory().close();
        }else if(i.getType().equals(Material.FIRE)){
            Inventory inv = p.getServer().createInventory(null,InventoryType.DISPENSER,"§aNeues ShopItem");
            ItemStack im = new ItemBuilder(Material.BARRIER).setDiplayname("").build();
            inv.setItem(0,im);
            inv.setItem(1,im);
            inv.setItem(2,im);
            inv.setItem(3,new ItemBuilder(Material.WOOL,1,(short)14).setDiplayname("§cBack").build());
            inv.setItem(5,new ItemBuilder(Material.WOOL,1,(short)5).setDiplayname("§aAccept").build());
            inv.setItem(6,im);
            inv.setItem(7,im);
            inv.setItem(8,im);
            p.openInventory(inv);
        }else{
            int kosten = Integer.parseInt(i.getItemMeta().getLore().get(0).replace("§6Kosten: ",""));
            Inventory inv = p.getServer().createInventory(null, InventoryType.HOPPER,"§aKosten für " + i.getType().name());
            inv.setItem(0,new ItemBuilder(Material.STONE_BUTTON).setDiplayname("§c-1").build());
            inv.setItem(2,new ItemBuilder(i.getType()).setDiplayname("§9Kosten: " + kosten).setLore(new String[]{"§7" + itemSlot}).build());
            inv.setItem(4,new ItemBuilder(Material.STONE_BUTTON).setDiplayname("§a+1").build());
            p.openInventory(inv);
        }
    }
    public static void onClickAddItemMenue(ItemStack i,Inventory inv,Player p){
        if(i.getType().equals(Material.WOOL)) {
            if(i.getData().getData() == 4) {
                if ((inv.getItem(4) != null) || (!inv.getItem(4).getType().equals(Material.AIR))) {
                    File file = new File("plugins//UserShop//" + getCfgStringAdmin.get(p.getName()) + ".yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    //TODO in die cfg setzen
                }
            }else{
                if ((inv.getItem(4) != null) || (!inv.getItem(4).getType().equals(Material.AIR))) {
                    p.getInventory().addItem(inv.getItem(4));
                }
                p.getOpenInventory().close();
            }
        }
    }
    public static void onClickKostenChangeInv(Inventory inv,ItemStack i,Player p){
        int kosten = Integer.parseInt(inv.getItem(2).getItemMeta().getDisplayName().replace("§9Kosten: ",""));
        int itemSlot = Integer.parseInt(inv.getItem(2).getItemMeta().getLore().get(0).replace("§7",""));
        File file = new File("plugins//UserShop//" + UserShop.getCfgStringAdmin.get(p.getName()) + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if(i.getItemMeta().getDisplayName().equals("§c-1")){
            if(kosten==0){
                return;
            }
            kosten=-1;
            inv.setItem(2,new ItemBuilder(i.getType()).setDiplayname("§9Kosten: " + kosten).build());
        }else if(i.getItemMeta().getDisplayName().equals("§a+1")){
            if(kosten>=100000){
                return;
            }
            kosten=+1;
            inv.setItem(2,new ItemBuilder(i.getType()).setDiplayname("§9Kosten: " + kosten).build());
        }
        UserShopItem item = new UserShopItem(inv.getItem(2));
        item.setKosten(kosten);
        List<String> content = cfg.getStringList("Items");
        content.set(itemSlot,getShopItemString(item));
        cfg.set("Items",content);
        Main.saveFile(file,cfg);
    }
    public static void createShop(Player p, Location l){
        String loc = l.getX() + "_" + l.getY() + "_" + l.getZ() + "_" + l.getWorld().getName();
        File file = new File("plugins//UserShop//" + loc + ".yml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("owner",p.getUniqueId().toString());
        Main.saveFile(file,cfg);
    }
    public static void openAdminMenue(Player p,String loc){
        File file = new File("plugins//UserShop//" + loc + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        getCfgStringAdmin.put(p.getName(),loc);

        Inventory inv = p.getServer().createInventory(null,36,"§aUserShop §7AdminMenü");
        for(int i = 0;i<getContent(cfg).size();i++){
            inv.setItem(i,getContent(cfg).get(i));
        }
        inv.setItem(35,new ItemBuilder(Material.BARRIER).setDiplayname("§cAdmin's").setLore(new String[]{"§7Klicke um jemanden rechte","§7für den Shop zu geben"}).build());
        inv.setItem(34,new ItemBuilder(Material.FIRE).setDiplayname("§aNeues Item").setLore(new String[]{"§7Klicke um ein neues Item in","§7den Shop hinzuzufügen!"}).build());
        p.openInventory(inv);
    }
}
