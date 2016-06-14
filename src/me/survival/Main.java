package me.survival;






import me.survival.commands.*;
import me.survival.elite.*;
import me.survival.listener.*;
import me.survival.methods.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import chunkgs.ChunkManager;
import chunkgs.Command_Gs;
import me.survival.api.ListenerBundle;
import me.survival.api.ListenerManager;
import me.survival.api.Listener_InteractAPI;
import me.survival.objects.Ask;
import me.survival.objects.Broadcast;
import me.survival.objects.Sword;
import me.survival.objects.TravelBallon;
import me.survival.shop.VillagerShop;
import monsterworld.Monsterworld;
import monsterworld.RegionLvl1;
import monsterworld.RegionLvl2;
import monsterworld.RegionLvl3;
import monsterworld.RegionLvl4;
import monsterworld.RegionLvl5;
import monsterworld.RegionLvl6;
import mysql.MySQL;
import worldmanager.FarmworldEuropia;
import worldmanager.Mainworld;
import worldmanager.WorldManager;

public class Main extends JavaPlugin {
	
	//public static MySQL mysql;
	public static final String prefix = "§8[§3Vetox§8] ";
	public static final String gsprefix = "§8[§bGs§8] ";
	public static final Location spawn = new Location(Bukkit.getWorld("Mainworld"), -206, 42.5, 412);
	public static Main main;
	@Override
	public void onEnable() {
		registerCommands();
		main = this;
		ListenerManager lmanager = new ListenerManager(this);
		ListenerBundle bundle = new ListenerBundle();
		bundle.add(new Listener_AchivmentEvent());
		bundle.add(new Listener_BlockBreakEvent());
		bundle.add(new Listener_BlockPlaceEvent());
		bundle.add(new Listener_EntityDeathEvent());
		bundle.add(new Listener_EntityInteractEvent());
		bundle.add(new Listener_ExplosionPrimeEvent());
		bundle.add(new Listener_InventoryClickEvent());
		bundle.add(new Listener_InteractAPI());
		bundle.add(new Listener_InventoryCloseEvent());
		bundle.add(new Listener_InventoryOpenEvent());
		bundle.add(new Listener_PlayerJoinEvent(this));
		bundle.add(new Listener_PlayerQuitEvent());
		bundle.add(new Listener_PlayerChatEvent(this));
		bundle.add(new Listener_PlayerItemDropEvent());
		bundle.add(new Listener_PlayerDeathEvent());
		bundle.add(new Listener_PlayerInteractAtEntityEvent());
		bundle.add(new Listener_PlayerInteractEvent());
		bundle.add(new Listener_PlayerExpChangeEvent());
		bundle.add(new Listener_PlayerRespawnEvent());
		bundle.add(new Listner_PlayerCommandPreprocessEvent());
		bundle.add(new Listener_SignKlickEvent());
		bundle.add(new Listener_SongEndEvent());
		bundle.add(new Listener_RecurcePack());
		bundle.add(new Listener_VehileExitEvent());
		bundle.add(new Listener_WeatherChangeEvent());
		bundle.add(new Listener_WheatProtect());
		bundle.add(new Listener_InventoryDragEvent());
		bundle.add(new Listener_EntityDamageEvent());
		bundle.add(new Listener_WaterFlowEvent());
		bundle.add(new Listener_PlayerBucketFillEvent());
		lmanager.register("survivalevents", bundle);
		
		Ask.registerQuestions();
		MySQL.register();
		Time.startClock(this);
		
		RegionLvl1.startSpawning(this);
		RegionLvl2.startSpawning(this);
		RegionLvl3.startSpawning(this);
		RegionLvl4.startSpawning(this);
		RegionLvl5.startSpawning(this);
		RegionLvl6.startSpawning(this);
		
		Monsterworld.onStart();
		FarmworldEuropia.onStart();
		
		
		Sword.spawnEntity(this);
		Mainworld.onStart();
			
		TravelBallon.registerTravelBallon();
		WorldManager.makeWorldSave();
		Broadcast.onEnable();
		WorldManager.protectWorlds();																												
		VillagerShop.onEnable();
		Radio.onDisable(); // ???
		VetoxRecipes.addFireBallRecipe();
		Bukkit.getConsoleSender().sendMessage("§3VetoxSurvival akitiviert!");
		ChunkManager.onEnable();
		ItemClear.onEnable();
		VetoxRecipes.disableRecipes(this);
		AFK.registerAFK(this);
   
	}
	





	@Override
	public void onDisable() {
		Monsterworld.onStop();
	}


	private void registerCommands() {
		getCommand("head").setExecutor(new Command_Head());
		getCommand("ride").setExecutor(new Command_Horse(this));
		getCommand("askhextori").setExecutor(new Command_Ask());
		getCommand("answer").setExecutor(new Command_Answer());
		getCommand("sword").setExecutor(new Command_Sword());
		getCommand("stats").setExecutor(new Command_Stats());
		getCommand("ping").setExecutor(new Command_Ping());
		getCommand("gs").setExecutor(new Command_Gs(this));
		getCommand("chatclear").setExecutor(new Command_CC());
		getCommand("clear").setExecutor(new Command_Clear());
		getCommand("heal").setExecutor(new Command_Heal());
		getCommand("spawn").setExecutor(new Command_Spawn(this));
		getCommand("firework").setExecutor(new Command_Firework());
		getCommand("song").setExecutor(new Command_Song());
		getCommand("radio").setExecutor(new Command_Radio());
		getCommand("enchanting").setExecutor(new Command_Enchanting());
		getCommand("msg").setExecutor(new Command_Msg());
		getCommand("r").setExecutor(new Command_R());
		getCommand("magic").setExecutor(new Command_Magic());
		getCommand("hilfe").setExecutor(new Command_Hilfe());
		getCommand("regeln").setExecutor(new Command_Rules());
		getCommand("tutorial").setExecutor(new Command_Tutorial());
		getCommand("gamemode").setExecutor(new Command_Gamemode());
		getCommand("workbench").setExecutor(new Command_WorkBench());
		getCommand("enderchest").setExecutor(new Command_EnderChest());
		getCommand("day").setExecutor(new Command_Day());
		getCommand("shop").setExecutor(new Command_Shop());
		getCommand("bewerben").setExecutor(new Command_Bewerben());
		getCommand("sethome").setExecutor(new Command_SetHome());
		getCommand("liquidclean").setExecutor(new Command_LiquidClean());
	}
	

	
	
	
}
