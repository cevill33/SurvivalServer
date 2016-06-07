package chunkgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuilderRegion {
	
	public static HashMap<String, List<BuilderRegion>> buildeRegion = new HashMap<>();
	private String name;
	private int x1;
	private int x2;
	private int z1;
	private int z2;
	private String world;
	private String message;
	private int rang;
	
	public BuilderRegion(String name, int x1, int x2, int z1, int z2, String world, int rang, String message) {
		this.name = name;
		this.x1 = x1;
		this.x2 = x2;
		this.z1 = z1;
		this.z2 = z2;
		this.world = world;
		this.rang = rang;
		this.message = message;
	}
	
	static {
		//Register Regions :D
		List<BuilderRegion> listMainworld = new ArrayList<>();
		listMainworld.add(new BuilderRegion("Spawn", -317, -171, 345, 496, "Mainworld", 11, "Am Spawn darfst du kein Gs haben laufe 500 Blöcke weg vom Spawn dort gibt es bestimmt ein Gs für dich"));
		listMainworld.add(new BuilderRegion("SpawnUmgebung", -403, -147, 274, 592, "Mainworld", 5, "Laufe doch ein bisschen weite dort gibt es sicher ein sch§nes Gs für dich!"));
		listMainworld.add(new BuilderRegion("IceDorf", 1643, 1693, -1544, -1422, "Mainworld", 11, "Laufe doch ein bisschen weite dort gibt es sicher ein sch§nes Gs für dich!"));
		listMainworld.add(new BuilderRegion("Rohan", -910, -810, 670, 860, "Mainworld", 11, "Laufe doch ein bisschen weite dort gibt es sicher ein sch§nes Gs für dich!"));
		listMainworld.add(new BuilderRegion("Jungle", -115, 270, 640, 1000, "Mainworld", 11, "Hier wird einmal eine Stadt gebaut, deswegen kannst du hier nicht bauen!"));
		
		
		
		buildeRegion.put("Mainworld", listMainworld);
		
		
		
		
		
		
		
	}
	
	
	public String getName() {
		return name;
	}
	public int getRang() {
		return rang;
	}
	public String getWorld() {
		return world;
	}
	public int getX1() {
		return x1;
	}
	public int getX2() {
		return x2;
	}
	public int getZ1() {
		return z1;
	}
	public int getZ2() {
		return z2;
	}
	public String getMessage() {
		return message;
	}
	
	
	
}
