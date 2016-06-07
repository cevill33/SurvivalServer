package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;
import me.survival.elite.Command_Song;
import me.survival.methods.Radio;

public class Command_Radio implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("join")) {
				if(Command_Song.song.containsKey(p.getName())) {
					p.sendMessage(Main.prefix + "§cDu musst /song stop eingeben, um Radio zu hören!");
					return true;
				}
				
				if(Radio.sp == null) {
					return true;
				}
				if(Radio.sp.getPlayerList() == null) {
					System.out.println("The playerlist == NULL");
					Radio.sp.addPlayer(p);
					p.sendMessage(Main.prefix + "§7Du hörst jetzt erfolgreich Radio!");
					return true;
				}
				if(Radio.sp.getPlayerList().contains(p.getName())) {
					p.sendMessage(Main.prefix + "§7Du hörst bereits §3Radio§7!");
					return true;
				}
				
				Radio.sp.addPlayer(p);
				p.sendMessage(Main.prefix + "§7Du hörst jetzt erfolgreich Radio!");
				return true;
			}
			
			
			if(args[0].equalsIgnoreCase("leave")) {
				if(Radio.sp.getPlayerList().contains(p.getName())) {
					Radio.sp.removePlayer(p);
					p.sendMessage(Main.prefix + "§7Du hast den Radiosender verlassen!");
				} else {
					p.sendMessage(Main.prefix + "§cDu hörst gar kein Radio!");
				}
				return true;

			}
		}
		
		
		p.sendMessage(Main.prefix + "§cSyntax: §7/radio join | leave!");
		return false;
	}

}
