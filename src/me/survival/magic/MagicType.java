package me.survival.magic;

public enum MagicType {

	
	NORMAL("Normal"),
	ELITE("Elite"),
	TITAN("Titan"),
	GOD("God");
	
	private String name;

	MagicType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	
}
