package me.survival.commands;

import me.survival.Main;
import me.survival.npc.MessageThread;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.survival.methods.Tutorial;

public class Command_Tutorial implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) cs;
		if(args.length == 0) {
			Tutorial.startTutorial(p);
			return true;
		}

		if(args[0].equalsIgnoreCase("test")) {
			p.sendMessage("GO");
			new MessageThread("Bürger", "Hallo ich bin der Bürgermeißter. Ich lebe hier seit ein paar Jahren. Es ist ein kleiner Test also denke nicht dass dies was ich sage wichtig ist. Ich grüße meinen Feind Gomme!", p).run();
			return true;
		}
		
		p.sendMessage(Main.prefix + "§cSyntax: §7/tutorial!");
		return false;
	}	
	
}