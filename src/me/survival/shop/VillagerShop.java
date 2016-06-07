package me.survival.shop;

import java.util.HashMap;

import me.vetoxapi.objects.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.survival.Main;
import me.survival.api.EntityModifier;
import me.survival.api.ItemBuilder;
import me.survival.objects.Level;

public class VillagerShop {

	
	
	public static HashMap<String, VillagerShop> shophm = new HashMap<>();
	private Location loc;
	private BuyObject[] buy;
	private String name;

	public VillagerShop(String name, Location loc, BuyObject...buy) {
		this.name = name;
		this.loc = loc;
		this.buy = buy;
		shophm.put(name, this);
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public BuyObject[] getBuy() {
		return buy;
	}
	
	@SuppressWarnings("static-access")
	public void spawn() {
		if(!getLoc().getChunk().isLoaded()) {
			getLoc().getChunk().load(true);
		}
		for(Entity e : getLoc().getChunk().getEntities()) {
			if(e.getCustomName() != null) {
				if(e.getCustomName().equals("§a§lShop§7: " + name)) {
					return;
				}
			}
		}
		Entity e = getLoc().getWorld().spawnEntity(getLoc(), EntityType.VILLAGER);
		e.setCustomName("§a§lShop§7: " + name);
		EntityModifier eM = new EntityModifier(e, Main.main);
		eM.modify().setInvulnerable(true).setNoAI(true);
		
	}
	
	public static void openInventory(Player p, String name) {
		VillagerShop shop = shophm.get(name);
		if(shophm.containsKey(name)) {
			
		} else {
			return;
		}
		Inventory inv = p.getServer().createInventory(null, 27, "§a§lShop§7: " + name);
		for(int i = 0; i < shop.getBuy().length; i++) {
			BuyObject buy = shop.getBuy()[i];
			ItemStack stack = new ItemBuilder(buy.getStack().getType()).setDiplayname("§7Verkaufpreiß: §3" + buy.getPrice() + "Coins§8/§3ProStück").build();
			inv.addItem(stack);
		}
		
		p.openInventory(inv);
	}
	
	public static void onKlick(Player p, ItemStack stack) {
		String itemname = stack.getItemMeta().getDisplayName();
		String finish = itemname.substring(18, itemname.length() -18);
		double price = Double.parseDouble(finish);
		Inventory inv = p.getServer().createInventory(null, 9, "§aVerkaufen: " + price + "Coins/Stück");
		inv.setItem(0,new ItemBuilder(stack.getType()).setDiplayname("§31 §7Stück").setLore("§7" + price + "§7Coins.").build());
		inv.setItem(2,new ItemBuilder(stack.getType()).setDiplayname("§34 §7Stück").setLore("§7" + (price*4) + "§7Coins.").build());
		inv.setItem(4,new ItemBuilder(stack.getType()).setDiplayname("§38 §7Stück").setLore("§7" + (price*8) + "§7Coins.").build());
		inv.setItem(6,new ItemBuilder(stack.getType()).setDiplayname("§332 §7Stück").setLore("§7" + (price*32) + "§7Coins.").build());
		inv.setItem(8,new ItemBuilder(stack.getType()).setDiplayname("§364 §7Stück").setLore("§7" + (price*64) + "§7Coins.").build());
		
		p.openInventory(inv);
		
	}
	
	
	public static void onSell(Player p, ItemStack stack, double price) {
		if(stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()) {
			String itemname = stack.getItemMeta().getDisplayName();
			int ammount = Integer.parseInt(itemname.substring(2, itemname.length() - 8));
			if(p.getInventory().contains(stack.getType(), ammount)) {
				int i = 0;
				for(ItemStack inventory : p.getInventory().getContents()){ 
					if(inventory == null) continue;
		            if(inventory.getType() != stack.getType()){ 
		                continue;
		            }

		            if((inventory.getAmount() <= ammount)){ 
		            	i = i + inventory.getAmount();
		            	System.out.println("Es tritt ein!");
		            	inventory.setType(Material.SPONGE);
		            	if(i >= ammount) break;
		            	continue;
		            }

		            if(inventory.getAmount() == ammount) {
		            	inventory.setType(Material.SPONGE);
		            	i = i + ammount; }
		            else
		            	inventory.setAmount(inventory.getAmount() - ammount); 
		            break;
		        }
				p.getInventory().remove(Material.SPONGE);
				int xp = (int) (price*ammount)/8;
				Level.addXp(p, xp);
				p.sendMessage("§a§lShop§7: §fDu hast erfolgreich §6" + (price*ammount) + " §fverdient!");
				p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1);
				MoneyManager.addMoney(p.getUniqueId(), (price*ammount));
				
			} else {
				p.closeInventory();
				p.sendMessage("§a§lShop§7: §cDu hast zu wenig bzw. garnichts von diesem Material zu verf§gung!");
				p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
			}
		}

	}
	
	
	public static void onEnable() {
		Location locrecurce = new Location( Bukkit.getWorld("Mainworld"), -266.5, 41, 435.5);
		locrecurce.setYaw(90f);
		
		Location locfood = new Location( Bukkit.getWorld("Mainworld"), -266.5, 42, 448.5);
		locfood.setYaw(Float.parseFloat("90"));
		
		Location locblocks1 = new Location( Bukkit.getWorld("Mainworld"), -265.5, 42, 459.5);
		locblocks1.setYaw(137f);
		VillagerShop recurce = new VillagerShop("Recourcen", locrecurce
							   	,new BuyObject(new ItemStack(Material.COBBLESTONE), 0.3)
							   	,new BuyObject(new ItemStack(Material.STONE), 0.5)
							   	,new BuyObject(new ItemStack(Material.COAL_BLOCK), 3)
								,new BuyObject(new ItemStack(Material.DIAMOND), 40)
								,new BuyObject(new ItemStack(Material.EMERALD), 15)
								,new BuyObject(new ItemStack(Material.GOLD_INGOT), 15));
								recurce.spawn();
								
								VillagerShop food = new VillagerShop("Essen", locfood
									   	,new BuyObject(new ItemStack(Material.COOKED_CHICKEN),1.5)
									   	,new BuyObject(new ItemStack(Material.COOKED_BEEF), 1.5)
									   	,new BuyObject(new ItemStack(Material.COOKED_FISH), 5)
										,new BuyObject(new ItemStack(Material.COOKED_MUTTON), 1.5)
										,new BuyObject(new ItemStack(Material.BAKED_POTATO), 1.5)
										,new BuyObject(new ItemStack(Material.BREAD), 3)
										,new BuyObject(new ItemStack(Material.CARROT), 2));
										food.spawn();
										
										VillagerShop blocks1 = new VillagerShop("Stein/Holz", locblocks1
											   	,new BuyObject(new ItemStack(Material.COBBLESTONE), 0.31)
											   	,new BuyObject(new ItemStack(Material.STONE), 0.51)
											   	,new BuyObject(new ItemStack(Material.WOOD), 0.5)
												,new BuyObject(new ItemStack(Material.GRAVEL), 2)
												,new BuyObject(new ItemStack(Material.DIRT), 0.1)
												,new BuyObject(new ItemStack(Material.SAND), 2));
										
												blocks1.spawn();
		
		

		
	}
	
	
	
	
}
