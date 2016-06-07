package me.survival.objects;

import me.survival.api.InventoryAPI;
import me.survival.methods.VetoxRecipes;
import me.vetoxapi.api.PlayerStats;
import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

public class Sword {

	public static final String prefix = "§6[§fSword§6] ";

	public static ItemStack getSword(Player p) {
		UUID id = p.getUniqueId();
		int lvl = (Integer) new DBVetoxPlayer(id.toString()).getObject("swordlevel");
		ItemStack sword = Swords.getByLevel(lvl).getItemStack(p.getName(), Arrays.asList("§7Level: §f" + lvl));
		if(lvl == 0) {
			sword = Swords.getByLevel(lvl).getItemStack(p.getName(), Arrays.asList("§7Level: §f" + lvl, "§7Du kannst dein Schwert verbessern!", "§7Gebe dazu /sword upgrade ein!"));
		}
		return sword;
	}

	public static void openInventory(Player p) {
		int swordlvl = (Integer) new DBVetoxPlayer(p.getUniqueId().toString()).getObject("swordlevel");
		if(swordlvl >= 10) {
			p.sendMessage(Sword.prefix + "§cDu hast das maximale Schwert Level erreicht!");
			p.closeInventory();
			return;
		}
		Inventory inv = p.getServer().createInventory(null, InventoryType.HOPPER, "§a§5Sword");
		inv.setItem(4, new ItemBuilder(Material.BOOK).setDiplayname("§6Was ist das?").setLore("§7Hier kannst du dein Schwert", "§7upgraden indem du die", "§7Aufträge die im Diamanten", "§7stehen erfüllst!").build());


		inv.setItem(0, new ItemBuilder(Material.EMERALD).setDiplayname("§aBestätigen").setLore("§7Klick auf mich wenn", "§7du den Auftrag vom Diamanten", "§7erfüllt hast!").build());

		Swords sword = Swords.getByLevel(swordlvl + 1);
		inv.setItem(3, new ItemBuilder(Material.DIAMOND).setDiplayname("§aAuftrag:").setLoreInArrayList(sword.getDescription()).build());
		p.openInventory(inv);
	}

	@SuppressWarnings("static-access")
	public static void spawnEntity(Main main) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			
			@Override
			public void run() {
				Location loc = new Location(Main.spawn.getWorld(), -242.5, 40, 433.5);
				loc.setYaw(135); 
				loc.setPitch(0);
				Chunk ck = loc.getChunk();
				if(!ck.isLoaded()) {ck.load();}
				for(Entity e : ck.getEntities()) {
					if(e.getType().equals(EntityType.VILLAGER)) {
						return;
					}
				}
				
				
				Entity entity = loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
				entity.setCustomName("§7Schmied");
				EntityModifier eM = new EntityModifier(entity, main);
				eM.modify().setCanDespawn(false).setNoAI(true).setInvulnerable(true);



			}
		},20*3);
	}

	public static void delete(Player p) {

		for(ItemStack s : p.getInventory()) {
			if(s == null) continue;
			ItemMeta meta = s.getItemMeta();
			if(meta != null) {

				String name = s.getItemMeta().getDisplayName();
				if(name != null) {

					if(name.startsWith("§f§6")) {

						p.getInventory().removeItem(s);

					}
				}
			}
		}
	}

	private static void upgrade(Player p) {
		DBVetoxPlayer dV = new DBVetoxPlayer(p.getUniqueId().toString());
		int lastlvl = (Integer) dV.getObject("swordlevel");
		dV.setObject("swordlevel", lastlvl + 1);

		int slot = (Integer) dV.getObject("swordslot");
		delete(p);
		ItemStack i = p.getInventory().getItem(slot -1);
		if(i == null || i.getType().equals(Material.WOOD_SWORD) || i.getType().equals(Material.STONE_SWORD) || i.getType().equals(Material.IRON_SWORD) || i.getType().equals(Material.DIAMOND_AXE)) {
			p.getInventory().setItem(slot - 1, Swords.getByLevel(lastlvl + 1).getItemStack(p.getName(), Arrays.asList("§7Level: §f" + (lastlvl + 1))));
			p.sendMessage(Sword.prefix + "§6Du hast dein §5Schwert §6erfolgreich verbessert!");
			Level.addXp(p, (lastlvl + 1) * 5 );
			p.closeInventory();
			return;
		}

		p.sendMessage(Sword.prefix + "§cDer Slot §3" + slot + " §cist besetzt bitte mache diesen frei und klicke erneut!");
		p.closeInventory();
	}

	public static void onEmeraldKlick(Player p) {
		int newlvl = (Integer) new DBVetoxPlayer(p.getUniqueId().toString()).getObject("swordlevel") + 1;
		String sm = "m" + newlvl;
		System.out.println("Die Methode : " + sm);
		try {
			Method m = Class.forName("me.survival.objects.Sword").getMethod(sm , new Class[]{Player.class});
			m.invoke(null, p);
		} catch (Exception e) { p.sendMessage("§4FEHLER"); e.printStackTrace();}
	}



	public static void m1(Player p) {
		PlayerStats stats = new PlayerStats(p.getUniqueId().toString());
		//Ca. 8.000 Blocks
		long toreach = 100*6000;

		if(stats.getDistanceByHorse() >= toreach) {
			upgrade(p);
		} else {
			p.closeInventory();
			double x = 8000.0/toreach;
			long y = toreach - stats.getDistanceByHorse();
			String blocks = "" + (x*y);
			System.out.println(x);
			System.out.println(y);
			System.out.println(toreach);
			System.out.println(stats.getDistanceByHorse());

			p.sendMessage(Sword.prefix + "§cDir fehlen noch §3" + blocks  + "§c Blöcke!");
		}

	}

	public static void m2(Player p) {

		double money = MoneyManager.getMoney(p.getUniqueId());

		if(money >= 100) {
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDir fehlen noch §3" + (100 - money) + " §cCoins!" );
	}
	
	public static void m3(Player p) {

		VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
		if(vP.getLvl() >= 3) {
			upgrade(p);
		} else p.sendMessage(Sword.prefix + "§cDu bist noch nicht Level 3 ---> §3/stats§c!");

	}

	public static void m4(Player p) {

		VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
		if(vP.getCoins() >= 300) {
			upgrade(p);
		} else p.sendMessage(Sword.prefix + "§cDir fehlen noch §3" + (300 - vP.getCoins()) + " §cCoins!" );

	}

	public static void m5(Player p) {

		VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
		if(vP.getHours() >= 7) {
			upgrade(p);
		} else p.sendMessage(Sword.prefix + "§cDu bist noch nicht 7 Stunden auf diesem Server!" );

	}

	public static void m6(Player p) {
		int ammount = InventoryAPI.getItemAmmount(p, Material.IRON_INGOT).intValue();
		if (ammount == 64) {
			p.getInventory().remove(Material.IRON_INGOT);
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDu musst genau 64 Eisen mithaben, nichtmehr und  nichtweniger.");
		p.sendMessage(Sword.prefix + "§3Info: §7Du hast " + ammount + "Eisen!");
	}
	
	public static void m7(Player p) {
		int votes = (Integer) new DBVetoxPlayer(p.getUniqueId().toString()).getObject("votes");
		if(votes >= 10) {
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDu hast erst §3" + votes + " §cvon 10 mal gevotet!");
	}

	public static void m8(Player p) {
		VetoxPlayer vP = VetoxPlayer.stats.get(p.getUniqueId());
		int hour = vP.getHours();
		if(hour >= 30) {
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDu hast erst §3" + hour + " §cStunden gespielt(Benötigt: 30)!");
	}
	
	public static void m9(Player p) {
		PlayerStats stats = new PlayerStats(p.getUniqueId().toString());
		long kills = stats.getMobKills(EntityType.SPIDER);
		if(kills >= 300) {
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDu hast erst §3" + kills + " §cSpinnen getötet(Benötigt: 300)!");
	}

	public static void m10(Player p) {

		int ammount = InventoryAPI.getItemAmmount(p, Material.FIREBALL, VetoxRecipes.fireball.getItemMeta().getDisplayName()).intValue();
		if (ammount == 32) {
			p.getInventory().remove(Material.FIREBALL);
			upgrade(p);
			return;
		}
		p.sendMessage(Sword.prefix + "§cDu musst genau 32 Enchanter mithaben, nichtmehr und  nichtweniger.");
		p.sendMessage(Sword.prefix + "§3Info: §7Du hast " + ammount + "Enchanter!");

	}


	
}
