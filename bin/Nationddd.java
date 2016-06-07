package me.survival.nation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Nationddd {
	
	public static List<Nationddd> nations = new ArrayList<>();
	static {
		
		
	}
	private String name;
	List<String> description;
	private List<String> players;
	private int points;
	private int kills;
	private int deaths;
	private Location spawnloc;
	
	public Nationddd(String name, List<String> description, Location spawnloc) {
		this.name = name;
		this.description = description;
		this.spawnloc = spawnloc;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	public Location getSpawnloc() {
		return spawnloc;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public int getKills() {
		return kills;
	}
	
	public List<String> getPlayers() {
		return players;
	}
	
	public int getPoints() {
		return points;
	}
	
	
	public void addDeath() {
		this.deaths = this.deaths + 1;
	}
	
	public void addKill() {
		kills = kills + 1;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points) {
		this.points = this.points + points;
	}
	 
	public void removePoints(int points) {
		this.points = this.points + points;
	}
	
	
	
	
	
	
}
