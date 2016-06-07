package me.survival.magic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.vetoxapi.mongodb.DBVetoxPlayer;
import me.vetoxapi.objects.MoneyManager;
import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.api.ActionBar;
import me.survival.api.ItemBuilder;

public class MagicManager {

	public static final String prefix = "§6[§3Magic§6] ";
	public static HashMap<String, List<Magic>> mymagics = new HashMap<>();
	public static ItemStack stick = new ItemBuilder(Material.BLAZE_ROD).setDiplayname("§aMagic").setLore("§3Ausführen§7<Linksklick>", "§3Auswahl§7<Rechtsklick>").build();
	
	/**
	 * @param1 = Playername.
	 * @param2 = Classname where the method is wich we want
	 * 			 to execute! 
	 */
	public static HashMap<String, String> magic = new HashMap<>();
	public static HashMap<String, Integer> mana = new HashMap<>();//MaxMana: 100
	public static List<String> loading = new ArrayList<>();
	
	public static void loadMagics(Player p) {
		String UUID = p.getUniqueId().toString();
		ArrayList<Integer> list = (ArrayList<Integer>) new DBVetoxPlayer(UUID).getObject("magics");
		List<Magic> magics = new ArrayList<>();
		if(list == null) {
			magics.add(Magic.SPEED1);
			mymagics.put(p.getName(), magics);
			mana.put(p.getName(), 100);
			return;
		}
		mana.put(p.getName(), 100);
		for(int i : list) {
			magics.add(Magic.values()[i]);
		}
		mymagics.put(p.getName(), magics);
		return;
	}
	
	
	
	public static void openMagics(Player p) {
		if(!mymagics.containsKey(p.getName())) {
			p.sendMessage(prefix + "§cWarte §7einen kleinen Moment, deine Magics werden geladen...");
			loadMagics(p);
		}
		
		Inventory inv = p.getServer().createInventory(null, 27, "§aMagics:");
		List<Magic> magiclist = mymagics.get(p.getName());
		for(Magic magic : Magic.values()) {
			List<String> dec = new ArrayList<>();
			for(String desc : magic.getDescription()) {
				dec.add(desc);
			}
			dec.add("§3Ab: §7" + magic.getType().getName());
			dec.add("§3Kosten: §7" + magic.getCost());
			if(magiclist.contains(magic)) {
				short subid = (short) magic.getSubid();
				inv.setItem(magic.getId(), new ItemBuilder(magic.getMat(), 1, subid).setDiplayname(magic.getName()).setLoreInArrayList(dec).build());
			} else {
				inv.setItem(magic.getId(), new ItemBuilder(Material.DEAD_BUSH).setDiplayname(magic.getName()).setLoreInArrayList(dec).build());
			}
			
		}
		
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1, 1);
	}
	
	public static void executeMagic(Player p) {
		String className = magic.get(p.getName());
		//Kein Zauber ausgew§hlt.
		if(className == null) {
			p.sendMessage(prefix + "§cDu musst einen Magic auswählen§7(Mache Rechtsklick)§c!");
			return;
		}
		
		//In Cooldown.
		int cdown = 0;
		int manas = mana.get(p.getName());
		try{
		Field field = Class.forName(className).getField("cooldown");
		cdown = field.getInt(null); } catch(Exception ex) {
			
		}
		if(manas < cdown )  {
			p.sendMessage(prefix + "§7Dir fehlt noch §c" +  (cdown - manas) + " §7Mana!");
			return;
		}
		
		mana.put(p.getName(), manas - cdown);
		ActionBar.sendActionBar(p, "§c- §3" + cdown + "% §7Mana");
		int value = manas - cdown;
		p.setExp(value / 100f); 
		
		
		
		//Methode ausf§hren.
		try {
			
			Method m = Class.forName(className).getMethod("fire", new Class[]{Player.class});
			
			try {
				
				m.invoke(null, p);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			p.sendMessage("§cDie Methode wurde nicht gefunden bitte Kontaktiere ein Teammitglied. (MagicManager:executeMagic)");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			p.sendMessage("§cDie Klasse wurde nicht gefunden bitte Kontaktiere ein Teammitglied. (MagicManager:executeMagic)");
		}
		
	}
	
	public static void startLoadinMana(Player p) {
		if(!loading.contains(p.getName())) {
			loading.add(p.getName());
			final int[] ID = new int[15];
			ID[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
				
				@Override
				public void run() {
					int manai = mana.get(p.getName());
					manai = manai + 5;
					if(manai >= 100) {
						ActionBar.sendActionBar(p, "§aMana aufgeladen!");
						mana.put(p.getName(), 100);
						p.setExp(0.99f);
						loading.remove(p.getName());
						Bukkit.getScheduler().cancelTask(ID[0]);
						return;
						
					}
					//ActionBar.sendActionBar(p, "§3" + manai + "% §8Mana");
					float value = (manai / 100f);
					p.setExp(value);
					loading.remove(p.getName());
					mana.put(p.getName(), manai);
					
				}
			}, 20*5, 20*5);
		}
	}
	
	public static void onKlick(Player p, int slot, ItemStack current) {
		if(Magic.values().length - 1 < slot) {
			return;
		}
		Magic magic = Magic.values()[slot];
		List<Magic> magiclist = mymagics.get(p.getName());
		if(magiclist.contains(magic)) {
			MagicManager.magic.put(p.getName(), magic.getClassName());
			p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1, 1);
			p.sendMessage(prefix + "§7Du hast den Magie §3ausgewählt§7!");
			p.closeInventory();
		} else {
			Inventory inv = p.getServer().createInventory(null, 9, "§aMagic Shop:§8 " + slot);
			inv.setItem(1, new ItemBuilder(Material.EMERALD).setDiplayname("§aKaufen").setLore("§3Preiß: §7" + magic.getCost()).build());
			current.setType(magic.getMat());
			inv.setItem(0, current);
			inv.setItem(8, new ItemBuilder(Material.BARRIER).setDiplayname("§4Zurück").build());
			p.openInventory(inv);
		}
		
		
		
	}
	
	public static void onBuyMagic(Player p, int id) {
		p.closeInventory();
		Magic magic = Magic.values()[id];
		UUID UUID = p.getUniqueId();
		VetoxPlayer vP = VetoxPlayer.stats.get(UUID);
		if(magic.getType() == MagicType.ELITE) {
			if(!p.hasPermission("vetox.magic.elite")) {
				p.closeInventory();
				p.sendMessage(prefix + "§cDieser Magic ist erst ab §6§lELITE §cverfügbar!");
				return;
			}
		}
		
		if(magic.getType() == MagicType.TITAN) {
			if(!p.hasPermission("vetox.magic.titan")) {
				p.closeInventory();
				p.sendMessage(prefix + "§cDieser Magic ist erst ab §a§lTITAN §cverfügbar!");
				return;
			}
		}
		
		if(magic.getType() == MagicType.GOD) {
			if(p.hasPermission("vetox.magic.elite")) {
				int lvl = vP.getLvl();
				if(lvl < 50) {
					p.closeInventory();
					p.sendMessage(prefix + "§cDu ben§tigst Level 50 um God- Magics zu kaufen");
					return;
				}
			} else {
				p.sendMessage(prefix + "§cDu ben§tigst Level 50 und §6§lELITE §coder §a§lTITAN§c!");
				p.closeInventory();
				return;
			}
		}
		
		if(vP.getCoins() >= magic.getCost()) {
			vP.setCoins(vP.getCoins() - magic.getCost());
			DBVetoxPlayer dV = new DBVetoxPlayer(UUID.toString());
			ArrayList<Integer> magics = (ArrayList<Integer>) dV.getObject("magics");
			if(magics == null) {
				magics = new ArrayList<Integer>();
			}
			magics.add(magic.getId());
			dV.setObject("magics", magics);
			List<Magic> list = mymagics.get(p.getName());
			list.add(magic);
			mymagics.put(p.getName(), list);
			p.sendMessage(MagicManager.prefix + "§7Du hast den Magic §3"  + magic.getName() + " §7gekauft!");
		} else {
			p.sendMessage(MagicManager.prefix + "§cDir fehlen noch §3" + (magic.getCost() - vP.getCoins()) + " §cCoins!");
		}
		p.closeInventory();
	}
	
	
}
