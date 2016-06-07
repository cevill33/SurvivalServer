package me.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class Command_Answer implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player) cs;
		if(!p.hasPermission("vetox.answer")) {
			
			return true;
		}
		
		
		
		
		
		if(args.length == 2) {
			if(args[0].equals("answer")) {
				
			
			String playername = args[1];
			if(Command_Ask.asking.contains(playername)) {
				Command_Ask.asking.remove(playername);
				Player target = Bukkit.getPlayer(playername);
				if(target != null) {
					target.sendMessage("§6[§7Ask§6] §6Ein Teammitglied hat reagiert und schreib dir in kürze eine Antwort...");
					for(Player supp : Bukkit.getOnlinePlayers()) {
						if(supp.hasPermission("vetox.support")) {
							if(supp.equals(p)) {
								supp.sendMessage("§6[§7Ask§6] §3Du §fkümmerts dich nun um: " + playername);
								TextComponent text1 = new TextComponent("§6[§7Ask§6] §3Template: ");
								TextComponent template = new TextComponent("§f§lCOPY!");
								template.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/answer " + playername + " "));
								text1.addExtra(template);
								p.spigot().sendMessage(text1);
								continue;
							}
							
							supp.sendMessage("§6[§7Ask§6] §3" + p.getName() + " §fkümmert sich nun um: " + playername);
						}
					}
					return true;
				} else {
					p.sendMessage("§6[§7Ask§6] §cDer Spieler " + playername + " ist offline!");
					return true;
				}
				
			} else {
				p.sendMessage("§cJemand beantwortet bereits seine Frage!");
				return true;
			}
			
			}
			
		}
		
		
		
		if(args.length > 1) {
			Player target = Bukkit.getPlayerExact(args[0]);
			if(target != null) {
				String msg = "";
				for(int i = 1; i < args.length; i++) {
					msg = msg + " " + args[i];
				}
				target.sendMessage("§6[§7Ask§6] §aAntwort: §7" + msg);
				target.playSound(target.getLocation(), Sound.LEVEL_UP, 1, 1);
				p.sendMessage("§6[§7Ask§6] §aAntwort: §7" + msg);
			} else {
				p.sendMessage("§6[§7Ask§6] §cDer Spieler ist offline!");
			}
			
			
			
			
			
		}
		
		
		
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
