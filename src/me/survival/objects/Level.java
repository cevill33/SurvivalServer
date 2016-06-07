package me.survival.objects;

import me.vetoxapi.objects.VetoxPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public enum Level {

	Lvl1(1, 50),
	Lvl2(2, 90),
	Lvl3(3, 110),
	Lvl4(4, 140),
	Lvl5(5, 150),
	Lvl6(6, 200),
	Lvl7(7, 280),
	Lvl8(8, 350),
	Lvl9(9, 400),
	Lvl10(10, 700),
	Lvl11(11, 800),
	Lvl12(12, 950),
	Lvl13(13, 1100),
	Lvl14(14, 1200),
	Lvl15(15, 1300),
	Lvl16(16, 1350),
	Lvl17(17, 1300),
	Lvl18(18, 1400),
	Lvl19(19, 1500),
	Lvl20(20, 1600),
	Lvl21(21, 1700),
	Lvl22(22, 1750),
	Lvl23(23, 1800),
	Lvl24(24, 1850),
	Lvl25(25, 1900),
	Lvl26(26, 2000),
	Lvl27(27, 2050),
	Lvl28(28, 2100),
	Lvl29(29, 2150),
	Lvl30(30, 2150),
	Lvl31(31, 2150),
	Lvl32(32, 2150),
	Lvl33(33, 2200),
	Lvl34(34, 2200),
	Lvl35(35, 2200),
	Lvl36(36, 2250),
	Lvl37(36, 2250),
	Lvl38(38, 2250),
	Lvl39(39, 2250),
	Lvl40(30, 2300);
	
	
	
	
	
	
	private int level;
	private double xp;

	private Level(int level, double xp) {
		this.level = level;
		this.xp = xp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getXp() {
		return xp;
	}
	
	public static Level getLevel(int level) {
		Level l = Level.values()[level];
		if(l!=null) return l;
		return Lvl30;
		
		
	}
	
	
	
	public static void addXp(Player p, int exp) {
		UUID id = p.getUniqueId();
		VetoxPlayer vP = VetoxPlayer.stats.get(id);
		double currentxp = vP.getExp() + exp * (1 + vP.getBoost());
		int currentlevel = vP.getLvl();
		if(currentxp >= getLevel(currentlevel).getXp()) {
			vP.setLvl(currentlevel + 1);
			vP.setExp(currentxp - getLevel(currentlevel).getXp());
			p.sendMessage("");
			p.sendMessage("§a§k#######################################");
			p.sendMessage("");
			p.sendMessage("  §6Levelaufstieg: §7Du bist nun Lvl: §5" + vP.getLvl());
			p.sendMessage("");
			p.sendMessage("§a§k#######################################");
			p.sendMessage("");
			p.setLevel(vP.getLvl());

		} else {
			vP.setExp(currentxp);
		}

	}
		
		
		
	}
		
		