package me.survival.listener;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongEndEvent;

import me.survival.Main;
import me.survival.api.GetRandom;
import me.survival.methods.Radio;

public class Listener_SongEndEvent implements Listener {

	
	@EventHandler
	public void onEnd(SongEndEvent e) {
		if(e.getSongPlayer().equals(Radio.sp)) {
			List<String> listening = Radio.sp.getPlayerList();
			Radio.sp.destroy();
			File mainfile = new File("plugins/Survival/Songs/");
			int r = GetRandom.returnRandom(0, mainfile.listFiles().length - 1);
			
			File file = mainfile.listFiles()[r];
			if(!file.exists()) {
				System.err.println("Song not Exists: SondEndEvent");
				return;
			}
			Song s = NBSDecoder.parse(file);
			Radio.sp = new RadioSongPlayer(s);
			Radio.sp.setPlaying(true);
			if(listening == null) return;
			for(String playername : listening) {
				Player p = Bukkit.getPlayer(playername);
				if(playername != null) {
					Radio.sp.addPlayer(p);
					p.sendMessage(Main.prefix + "§7Nun kommt ein anderes §3Lied§7!");
				}

			}

			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
