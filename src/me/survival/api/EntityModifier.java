package me.survival.api;


import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;










 
/**
 * Modifiziere ein {@link Entity}
 * <p>
 * Made by GERVobis
 */
public class EntityModifier {
 
        static Entity entity;
        static CraftEntity craftentity;
        static net.minecraft.server.v1_8_R3.Entity entityS;
        static int scheduler;
        static Plugin plugin;
        static Player player = null;
        static float Speed;
 
        @SuppressWarnings("static-access")
        public EntityModifier(Entity entity,Plugin plugin) {
                this.entity = entity;
                this.craftentity = ((CraftEntity) entity);
                this.entityS = craftentity.getHandle();
                this.plugin = plugin;
        }
 
        /**
         * Modifiziere ein {@link Entity} mit dem Inhalt des Builders
         */
        public static Builder modify() {
                return new Builder();
        }
 
        public static final class Builder {
 
                /**
                 * Setzt den Anzeigenamen vom ausgew�hlten {@link Entity}
                 *
                 * @param display
                 * @return
                 */
                public Builder setDisplayName(String display) {
                        entity.setCustomName(display);
                        entity.setCustomNameVisible(true);
                        return this;
                }
 
                /**
                 * Setzt die Anzeigenamensichtbarkeit vom ausgew�hlten {@link Entity}
                 *
                 * @param visible
                 * @return
                 */
                public Builder setDisplayNameVisible(Boolean visible) {
                        entity.setCustomNameVisible(visible);
                        return this;
                }
 
                /**
                 * Spielt ein Effekt beim ausgew�hlten {@link Entity}
                 *
                 * @param entityeffect
                 * @return
                 */
                public Builder playEffekt(EntityEffect entityeffect) {
                        entity.playEffect(entityeffect);
                        return this;
                }
 
                /**
                 * Entfernt das ausgew�hlten {@link Entity}
                 *
                 * @param null
                 * @return
                 */
                public Builder remove() {
                        entity.remove();
                        return this;
                }
 
                /**
                 * Setzt den Passenger des ausgew�hlten {@link Entity}
                 *
                 * @param passenger
                 * @return
                 */
                public Builder setPassenger(Entity passenger) {
                        entity.setPassenger(passenger);
                        return this;
                }
 
                /**
                 * Setzt die FireTicks vom ausgew�hlten {@link Entity}
                 *
                 * @param ticks
                 * @return
                 */
                public Builder setFireTicks(int ticks) {
                        entity.setFireTicks(ticks);
                        return this;
                }
 
                /**
                 * Setzt die Location vom ausgew�hlten {@link Entity}
                 *
                 * @param location
                 * @return
                 */
                public Builder setLocation(Location location) {
                        teleport(location);
                        return this;
                }
               
                /**
                 * Setzt die Yaw und Pitch vom ausgew�hlten {@link Entity}
                 *
                 * @param location
                 * @return
                 */
                public Builder setYawPitch(float yaw,float pitch) {
                        Location loc = entity.getLocation();
                        loc.setPitch(pitch);
                        loc.setYaw(yaw);
                        teleport(loc);
                        return this;
                }
               
                /**
                 * Teleportiert das ausgew�hlte {@link Entity}
                 *
                 * @param location
                 * @return
                 */
                public Builder teleport(Location location) {
                        entity.teleport(location);
                        return this;
                }
 
                /**
                 * T�tet das ausgew�hlte {@link Entity}
                 *
                 * @param null
                 * @return
                 */
                public Builder die() {
                        entityS.die();
                        return this;
                }
 
                /**
                 * Setzt die Sichtbarkeit des ausgew�hlten {@link Entity}
                 *
                 * @param invisible
                 * @return
                 */
                public Builder setInvisible(boolean invisible) {
                        entityS.setInvisible(invisible);
                        return this;
                }
 
                /**
                 * Setzt die eigenschaft noClip f�r das ausgew�hlte {@link Entity}
                 *
                 * @param noClip
                 * @return
                 */
                public Builder noClip(boolean noClip) {
                        entityS.noclip = noClip;
                        return this;
                }
 
                /**
                 * Macht das ausgew�hlte {@link Entity} unsterblich
                 *
                 * @param invulnerable
                 * @return
                 */
                public Builder setInvulnerable(boolean invulnerable) {
                        try {
                                Field invulnerableField = net.minecraft.server.v1_8_R3.Entity.class
                                                .getDeclaredField("invulnerable");
                                invulnerableField.setAccessible(true);
                                invulnerableField.setBoolean(entityS, invulnerable);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                        return this;
                }
 
                /**
                 * Setzt die eigenschaft noAi f�r das ausgew�hlte {@link Entity}
                 *
                 * @param noAI
                 * @return
                 */
               
                public Builder setNoAI(boolean noAI) {
                        NBTTagCompound tag = new NBTTagCompound();
                        entityS.c(tag);
                        tag.setBoolean("NoAI", noAI);
                        EntityLiving el = (EntityLiving) entityS;
                        el.a(tag);
                        return this;
                }
               
                /**
                 * Setzt die eigenschaft canpickuploot f�r das ausgew�hlte {@link Entity}
                 *
                 * @param canpickuploot
                 * @return
                 */
                public Builder setCanPickUpLoot(boolean canpickuploot) {
                        NBTTagCompound tag = new NBTTagCompound();
                        entityS.c(tag);
                        tag.setBoolean("CanPickUpLoot", canpickuploot);
                        EntityLiving el = (EntityLiving) entityS;
                        el.a(tag);
                        return this;
                }
               
                /**
                 * Setzt das Leben f�r das ausgew�hlte {@link Entity}
                 *
                 * @param health
                 * @return
                 */
                public Builder setHealth(float health) {
                        NBTTagCompound tag = new NBTTagCompound();
                        entityS.c(tag);
                        tag.setFloat("HealF", health);
                        EntityLiving el = (EntityLiving) entityS;
                        el.a(tag);
                        return this;
                }
               
                /**
                 * Setzt die eigenschaft canDespawn f�r das ausgew�hlte {@link Entity}
                 *
                 * @param candespawn
                 * @return
                 */
                public Builder setCanDespawn(boolean candespawn) {
                        candespawn = !candespawn;
                        NBTTagCompound tag = new NBTTagCompound();
                        entityS.c(tag);
                        tag.setBoolean("PersistenceRequired", candespawn);
                        EntityLiving el = (EntityLiving) entityS;
                        el.a(tag);
                        return this;
                }
               
 
                /**
                 * L�st das ausgew�hlte {@link Entity} zu dieser Location laufen
                 *
                 * @param location
                 * @param speed
                 * @return
                 */
                public Builder walkToLocation(Location location, float speed) {
                        ((CraftCreature) entity)
                                        .getHandle()
                                        .getNavigation()
                                        .a(location.getX(), location.getY(), location.getZ(), speed);
                        return this;
                }
 
                /**
                 * L�st das ausgew�hlte {@link Entity} den Spieler folgen
                 *
                 * @param target
                 * @param speed
                 * @return
                 */
                public Builder followPlayer(Player target, float speed) {
                        player = target;
                        Speed = speed;
                        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                                        new Runnable() {
                                                @SuppressWarnings("deprecation")
                                                public void run() {
                                                        double distance = entity.getLocation().distance(
                                                                        player.getLocation());
                                                        if (distance < 11) {
                                                                float speed = Speed;
                                                                if (distance < 3) {
                                                                        speed = 0;
                                                                }
                                                                ((CraftCreature) entity)
                                                                                .getHandle()
                                                                                .getNavigation()
                                                                                .a(player.getLocation().getX(),
                                                                                                player.getLocation().getY(),
                                                                                                player.getLocation().getZ(),
                                                                                                speed);
                                                        } else {
                                                                if (player.isOnGround())
                                                                        entity.teleport(player);
                                                        }
                                                }
                                        }, 0, 1);
                        return this;
                }
 
                /**
                 * Stop das ausgew�hlte {@link Entity} den Spieler zu folgen
                 *
                 * @param location
                 * @param speed
                 * @return
                 */
                public Builder stopFollowingPlayer() {
                        Bukkit.getScheduler().cancelTask(scheduler);
                        return this;
                }
 
                /**
                 * Gibt das modifizierte {@link Entity} zur�ck
                 *
                 * @return {@link Entity}
                 */
 
                public Entity build() {
                        return entity;
                }
 
        }
 
}
