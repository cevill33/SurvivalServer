package me.survival.elite;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import me.survival.Main;
import me.survival.api.ItemBuilder;


public class Command_Song implements CommandExecutor {

	
	public static HashMap<String, SongPlayer> song = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) cs;
		if(args.length == 0) {
			if(!p.hasPermission("vetox.song")) {
				p.sendMessage(Main.prefix + "§7Diesen Befehl kannst du nur mit §6Elite §7und §aTitan §7benutzen!");
				return true; }
			openInventory(p);			
			return true;
		}
		
		if(args[0].equals("stop")) {
			if(song.containsKey(p.getName())) {
				song.get(p.getName()).destroy();
				p.sendMessage(Main.prefix + "§7Der Song wurde §aerfolgreich§7 gestoppt!");
				song.remove(p.getName());
			} else {
				p.sendMessage(Main.prefix + "§cDu spielst zurzeit keinen Song ab!");
			}
			return true;
		}
		
		
		p.sendMessage(Main.prefix + "§cSyntax: §7/song (stop)");
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void openInventory(Player p) {
		Inventory inv = p.getServer().createInventory(null, 27, "§aMusik: ");
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aParadise").setLore("§8:Paradise").build());
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aA Kind of Magic").setLore("§8:A Kind of Magic").build());
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aAll Star").setLore("§8:All Star").build());
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aDas A Team").setLore("§8:The A Team").build());
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aRadioactive").setLore("§8:Radioactive").build());
		inv.addItem(new ItemBuilder(Material.NOTE_BLOCK).setDiplayname("§aPirate").setLore("§8:Hes A Pirate").build());
		
		p.openInventory(inv);
	}
	
	
	
	public static void onKlick(Player p, ItemStack current) {
		if(current.getType().equals(Material.NOTE_BLOCK)) {
			if(current.hasItemMeta()) {
				if(current.getItemMeta().hasLore()) {
					if(song.containsKey(p.getName())) {
						song.get(p.getName()).destroy();
						song.remove(p.getName());
					}
					
					try{
					String lorename = current.getItemMeta().getLore().get(0);
					String songname = lorename.split(":")[1];
					String path = "plugins/Survival/Songs/" + songname + ".nbs";
					p.closeInventory();
					p.sendMessage(Main.prefix + "§7Der §6Song §7geht los!");
					
					Song s = NBSDecoder.parse(new File(path));
					SongPlayer sp = new RadioSongPlayer(s);
					sp.addPlayer(p);
					System.out.println(sp.getPlayerList());
					sp.setPlaying(true);
					song.put(p.getName(), sp);
					}catch(Exception ex) {
						p.sendMessage(Main.prefix + "§cEs gibt einen Fehler: CannNotFoundPath, Kontaktiere bitte ein Teammitglied!");
					}
					
				}
			}
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
