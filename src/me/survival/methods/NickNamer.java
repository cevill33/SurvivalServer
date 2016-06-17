package me.survival.methods;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.survival.Main;
import me.survival.api.GameProfileBuilder;
import me.survival.api.UUIDFetcher;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by mariusk on 16.06.2016.
 */
public class NickNamer {

    public static HashMap<CraftPlayer,Location> loc;
    public static HashMap<CraftPlayer,Double> Health;

    public static void changeSkin(CraftPlayer cp, String UUID){
        GameProfile skingp = cp.getProfile();

        try {
            skingp = GameProfileBuilder.fetch(UUIDFetcher.getUUID(UUID));
        }catch(IOException e){
            cp.sendMessage(Main.prefix + "§cSyntax: Der Skin konnte nicht geladen werden!");
            e.printStackTrace();
            return;
        }

        Collection<Property> props = skingp.getProperties().get("textures");
        cp.getProfile().getProperties().removeAll("textures");
        cp.getProfile().getProperties().putAll("textures",props);

        loc.put(cp,cp.getLocation().add(0,1,0));
        Health.put(cp,cp.getHealth());

        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPackage(destroy);
        PacketPlayOutPlayerInfo tabremove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        sendPackage(tabremove);
        cp.setHealth(0);

        new BukkitRunnable(){

            @Override
            public void run(){
                cp.spigot().respawn();
                cp.setHealth(Health.get(cp));
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

    }
    public static void sendPackage(Packet packet){
        for(Player all : Bukkit.getOnlinePlayers()){
            ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
