package me.survival.methods;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.survival.Main;
import me.survival.api.GameProfileBuilder;
import me.survival.api.UUIDFetcher;
import me.survival.commands.Command_Nick;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mariusk on 16.06.2016.
 */
public class NickNamer {

    public static List<String> changeing = new ArrayList<>();
    public static HashMap<CraftPlayer,Location> loc = new HashMap<>();
    public static HashMap<CraftPlayer,Double> health = new HashMap<>();

    public static void change(CraftPlayer cp, String playerskin){
        GameProfile skingp = cp.getProfile();

        try {
            skingp = GameProfileBuilder.fetch(UUIDFetcher.getUUID(playerskin));
        }catch(IOException e){
            cp.sendMessage(Main.prefix + "§4Fehler:§7 Der Skin konnte nicht geladen werden!");
            e.printStackTrace();
            return;
        }

        Collection<Property> props = skingp.getProperties().get("textures");
        cp.getProfile().getProperties().removeAll("textures");
        cp.getProfile().getProperties().putAll("textures",props);

        loc.put(cp,cp.getLocation().add(0,1,0));
        health.put(cp,cp.getHealth());

        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPackage(destroy);
        PacketPlayOutPlayerInfo tabremove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        sendPackage(tabremove);
        NickNamer.changeing.add(cp.getName());
        cp.setHealth(0);

        new BukkitRunnable(){

            @Override
            public void run(){
                cp.spigot().respawn();
                cp.setHealth(health.get(cp));
                cp.teleport(loc.get(cp));
                PacketPlayOutPlayerInfo tabadd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
                sendPackage(tabadd);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(!all.getName().equals(cp.getName()))
                    ((CraftPlayer) all).getHandle().playerConnection.sendPacket(spawn);
                }
            }
        }.runTaskLater(Main.main,2);
        cp.getPlayer().setPlayerListName(playerskin);

    }
    public static void sendPackage(Packet packet){
        for(Player all : Bukkit.getOnlinePlayers()){
            ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
        }
    }
    public static void onClick(Player p,ItemStack i){
        if(i.getType().equals(Material.SKULL_ITEM)){
            if(Command_Nick.cooldownnick.contains(p.getName())){
                p.sendMessage(Main.prefix + "§7Du kannst dich nur alle 20 Sekunden Nicken!");
                return;
            }
            SkullMeta cm = (SkullMeta) i.getItemMeta();
            change((CraftPlayer)p,cm.getOwner());
            p.sendMessage(Main.prefix + "§7Die Spieler sehen dich nun als §f" + cm.getOwner());
            Command_Nick.cooldownnick.add(p.getName());
            Bukkit.getScheduler().runTaskLater(Main.main, new Runnable() {
                @Override
                public void run() {
                    Command_Nick.cooldownnick.remove(p.getName());
                }
            },20*60*20);
        }
        if(i.getType().equals(Material.BARRIER)){
            change((CraftPlayer)p,p.getName());
            p.sendMessage(Main.prefix + "Alle Spieler sehen dich wieder so wie du bist!");
        }
    }
}
