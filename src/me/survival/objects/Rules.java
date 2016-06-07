package me.survival.objects;

public enum Rules {

	HACKEN(1, "Keine modifizierte Clients(außer Optifine)!"),
	GRIEFEN(2, "Griefen ist verboten!"),
	TEAMEN(3, "Teamen ist max. in 2er Teams erlaubt!"),
	MOBFARM(4, "Mobfarmen sind verboten!"),
	CLOCKS(5, "Redstone- Clocks sind verboten!");
	
	
	
	private String name;
	private int id;

	private Rules(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	
}
