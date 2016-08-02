package me.survival.magic;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public enum Magic {

	
	
	
	SPEED1(MagicType.NORMAL, "Speed I", 0, Material.SUGAR, 0, Arrays.asList("§7Diese Magie gibt dir für", "§710 Sekunden Speed 1", "§3Mana: §745"), "me.survival.magic.magics.Speed1", 0, 0),
	BLITZ1(MagicType.NORMAL, "Blitz I", 200, Material.BLAZE_ROD, 0,Arrays.asList("§7Diese Magie schießt einen Blitz", "§7in die Richtung wo du hinschaust!", "§3Mana: §740"), "me.survival.magic.magics.Blitz1", 1, 1),
	HEALER1(MagicType.NORMAL, "Healer I", 150, Material.INK_SACK, 1, Arrays.asList("§7Diese Magie heilt dich", "§7und fällt ein bisschen","§7dein Essen auf!", "§3Mana: §745"), "me.survival.magic.magics.Healer1", 2, 2),
	JUMPER1(MagicType.NORMAL, "Jumper I", 400, Material.FIREWORK, 0, Arrays.asList("§7Diese Magie lässt dich mehr", "§7als 15 Blöcke hoch Springen", "§3Mana: §735"), "me.survival.magic.magics.Jumper1", 3, 3),
	BOW1(MagicType.NORMAL, "Minigun I", 500, Material.ARROW, 0, Arrays.asList("§7Diese Magie lässt dich 4", "§7Pfeile schnell abfeuern!", "§3Mana: §740"), "me.survival.magic.magics.Bow1", 4, 4),
	FIREBALL1(MagicType.NORMAL, "Fireball I", 700, Material.FIREBALL, 0, Arrays.asList("§7Diese Magie lässt dich einen", "§7Feuerball schießen auf" ,"§7dem du reitest!", "§3Mana: §760"), "me.survival.magic.magics.Fireball1", 5, 5),
	COBWEB1(MagicType.NORMAL, "Spinne I", 750, Material.WEB, 0, Arrays.asList("§7Schleudert Spinnweben aus deinem Zauberstab!", "§3Mana: §735"), "me.survival.magic.magics.CobWeb1", 6, 6),
	MEDIC(MagicType.NORMAL, "Medic", 650, Material.BEACON, 0, Arrays.asList("§7Heilt die Spieler in", "§7einem Umkreis von 3 Blöcken", "§7für 10 Sekunden!", "§3Mana: §780"), "me.survival.magic.magics.Medic", 7, 7),
	THOR1(MagicType.NORMAL, "Thor I", 550, Material.IRON_AXE, 0, Arrays.asList("§7Alle Spieler im Radius von", "§73 Blöcken werden" ,"§7wegkatapultiert.", "§3Mana: §740"), "me.survival.magic.magics.Thor1", 8,8),
	FREEZE(MagicType.NORMAL, "Freeze", 445, Material.PACKED_ICE, 0, Arrays.asList("§7Bei dieser", "§7Magie machst du alle Spieler " ,"§7im Radius von 3 Blöcken","§7fast bewegungsunfähig", "§3Mana: §745"), "me.survival.magic.magics.Freeze", 12, 9),
	DELFIN(MagicType.NORMAL, "Delfin", 850, Material.WATER_LILY, 0, Arrays.asList("§7Du wirst mit der", "§7Magie dich schneller" ,"§7über das Wasser bewegen/springen!", "§3Mana: §740"), "me.survival.magic.magics.Delfin", 13, 11),
	WITHERSKULL(MagicType.NORMAL, "Witherskull", 500, Material.SKULL_ITEM, 3, Arrays.asList("§7Die Magie lässt", "§7dich einen" ,"§7Witherskull schießen!", "§3Mana: §760"), "me.survival.magic.magics.Witherskull", 14, 10),

	AKBAR(MagicType.ELITE, "Rage QUIT!", 450, Material.TNT, 0, Arrays.asList("§7Du wirst bei dieser", "§7Magie dich selbst in die" ,"§7luft jagen.(GEHT NOCH NICHT)", "§3Mana: §730"), "me.survival.magic.magics.Akbar", 9, 12),
	PHOENIX(MagicType.ELITE, "Phönix Regen", 700, Material.ARROW, 0, Arrays.asList("§7Ein Pfeilregen wird", "§7herabfallen.", "§3Mana: §750"), "me.survival.magic.magics.Pheonix", 11, 14),
	ROCKET(MagicType.ELITE, "Flying Rocket", 500, Material.FIREWORK, 0, Arrays.asList("§7Mit dieser", "§7Magie fliegst du mit" ,"§7einer Rakete als dein Begleiter","§7in die Luft!", "§3Mana: §735"), "me.survival.magic.magics.FlyingRocket", 10, 13);


	private MagicType type;
	private String name;
	private double cost;
	private Material mat;
	private int subid;
	private List<String> description;
	private String className;
	private int id;
	private int slot;
	
	private Magic(MagicType type, String name, double cost, Material mat, int subid, List<String> description, String className, int id, int slot) {
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.mat = mat;
		this.description = description;
		this.className = className;
		this.id = id;
		this.subid = subid;
		this.slot = slot;
	}
	
	public static Magic getBySlot(int slot) {
		for (Magic m : Magic.values()) {
			if(slot == m.getSlot()) {
				return m;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
	
	public String getClassName() {
		return className;
	}
	
	public double getCost() {
		return cost;
	}
	public List<String> getDescription() {
		return description;
	}
	public Material getMat() {
		return mat;
	}
	public MagicType getType() {
		return type;
	}
	public int getId() {
		return id;
	}
	public int getSubid() {
		return subid;
	}
	public int getSlot() {
		return slot;
	}
}
