package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.api.ClickableChat;

public class Command_Hilfe implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		Player p = (Player) cs;
		if(args.length == 0) {
			sendHelp(p);
			return true;
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("wasgibts")) {
				p.sendMessage(" ");
				p.sendMessage("§3§l--------------- §eWas gibts? §3§l---------------");
				p.sendMessage("§a§l• " + "§fGs");
				p.sendMessage("§a§l• " + "§fMonsterwelten");
				p.sendMessage("§a§l• " + "§fFarmwelten");
				p.sendMessage("§a§l• " + "§fStraßenpferde(/horse)");
				p.sendMessage("§a§l• " + "§fBallonreisen");
				p.sendMessage("§a§l• " + "§fRadio(/radio)");
				p.sendMessage("§a§l• " + "§fMagics");
				p.sendMessage("§a§l• " + "§fStatistiken(/stats)");
				p.sendMessage(" ");
				return true;

			}
			
			if(args[0].equalsIgnoreCase("reisen") || args[0].equalsIgnoreCase("ballon")) {
				p.sendMessage("");
				p.sendMessage("§3§l--------------- §eBallone(Reisen) §3§l---------------");
				p.sendMessage("§7Gerade aus vom Spawn befindet sich ein Ballon!");
				p.sendMessage("§7Wenn du mit ihm reisen willst musst du auf ein");
				p.sendMessage("§7Schild welches auf ihm befestigt ist klicken.");
				p.sendMessage("§7Dann kann man auswählen wo man hinfliegen will!");
				p.sendMessage("§7Man kann aber auch auf der Straße mit /horse reisen!");
				p.sendMessage("");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("magics")) {
				p.sendMessage(" ");
				p.sendMessage("§3§l--------------- §eMagics §3§l---------------");
				p.sendMessage("§7Du besitzt in deinem Inventar einen Zauberstab");
				p.sendMessage("§7(Lohrenstab). Wenn du auf diesen Rechtklick machts");
				p.sendMessage("§7kannst du dir einen Zauber kaufen/auswählen. Mit Linksklick");
				p.sendMessage("§7kannst du ihn dann ausführen! Falls du keinen Zauberstab");
				p.sendMessage("§7hast, kannst du §3/magic give §7eingeben!");
				p.sendMessage("");
				return true;
				
			}
			
			

		}
		sendHelp(p);
		return false;
	}
	
	
	
	private static void sendHelp(Player p) {
		p.sendMessage(" ");
		p.sendMessage("§3§l--------------- §eHilfe §3§l---------------");
		p.sendMessage("§7Klicke auf den Bereich wo du Hilfe brauchst:");
		ClickableChat.send(p, "§3§l§ ", "§f§lWas Gibt es hier?", "§aKlick mich!", "/hilfe wasgibts");
		ClickableChat.send(p, "§3§l§ ", "§f§lGrundstücke", "§aKlick mich!", "/gs tutorial");
		ClickableChat.send(p, "§3§l§ ", "§f§lReisen(Ballon)", "§aKlick mich!", "/hilfe ballon");
		ClickableChat.send(p, "§3§l§ ", "§f§lMagics", "§aKlick mich!", "/hilfe magics");
		p.sendMessage(" ");
		
	}

}
