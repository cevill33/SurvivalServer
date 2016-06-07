package me.survival.methods;

import java.io.File;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

public class Radio {
	
	static Song s = NBSDecoder.parse(new File("plugins/Survival/Songs/All Star.nbs"));
	public static SongPlayer sp = new RadioSongPlayer(s);
	static {
		sp.setPlaying(true);
	}
	
	public static void onDisable() {
		sp.setAutoDestroy(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
