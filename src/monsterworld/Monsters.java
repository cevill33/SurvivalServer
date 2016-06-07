package monsterworld;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public enum Monsters {

	
	
	
	LVL1(1, 2, RegionLvl1.monsterlv1),
	LVL2(2, 2, RegionLvl2.monsterlv2),
	LVL3(3, 3, RegionLvl3.monster),
	LVL4(4, 3, RegionLvl4.monster),
	LVL5(5, 6, RegionLvl5.monster),
	LVL6(6, 7, RegionLvl6.monster);
//	LVL8(8, 8),
//	LVL9(9, 9),
//	LVL10(10, 10),
//	LVL11(11, 11),
//	LVL12(12, 12);
	
	
	
	
	
	
	public static HashMap<UUID, Monsters> monster = new HashMap<>();
	private int dropcoins;
	private int lvl;
	private List<Integer> list;
	
	
	
	
	private Monsters(int lvl, int dropcoins, List<Integer> list) {
		this.lvl = lvl;
		this.dropcoins = dropcoins;
		this.list = list;
	}
	
	
	public int getLvl() {
		return lvl;
	}
	
	public int getDropcoins() {
		return dropcoins;
	}
	
	public List<Integer> getList() {
		return list;
	}
	
	
	public static void deleteMobs() {
		for(int x = 175; x > -272; x = x -16) {
			for(int z = -96; z < 959; z = z +16) {
				Location loc = new Location(Monsterworld.w, x, 1, z);
				Chunk c = loc.getChunk();
				if(!c.isLoaded()) c.load();
				for(Entity e : c.getEntities()) {
					if(!(e instanceof Player)) {
						e.remove();
					}
				}
				
				
			}
		}
	}
	
	
	
}
