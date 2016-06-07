package me.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.Main;

public class Command_CC implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			if(p.hasPermission("vetox.chatclear")) {
				for(int i = 0; i <= 100; i++) {
					p.sendMessage("");
				}
			} else {
				p.sendMessage(Main.prefix + "§cDieser Befehl ist für Spieler nicht verf§gbar!");
			}
		}
		
		if(args.length == 1) {
			if(p.hasPermission("vetox.chatclear.all")) {
				for(Player all : Bukkit.getOnlinePlayers()) {
					for(int i = 0; i <= 100; i++) {
						all.sendMessage("");
					}
				}
			} else {
				p.sendMessage(Main.prefix + "§cDieser Befehl ist nur für Teammitglieder verf§gbar!");
			}
		}
		
		
		return false;
	}

}
