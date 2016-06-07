package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.api.ClickableChat;

public class Command_Shop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) cs;
		
		if(args.length == 0) {
			
			p.sendMessage("§5[]------------------- §fShop §5-------------------[]");
			p.sendMessage(" ");
			p.sendMessage(" §e§lELITE§f:");
			p.sendMessage("   §3Kosten: §f20Euro");
			p.sendMessage("   §3Dauer : §fFür immer");
			ClickableChat.send(p, "   §3Vorteile: ", "§f§lKLICKE HIER", "Klick mich!", "/shop elite");
			p.sendMessage(" ");
			p.sendMessage(" §a§lTITAN§f:");
			p.sendMessage("   §3Kosten: §f40Euro");
			p.sendMessage("   §3Dauer : §fFür immer");
			ClickableChat.send(p, "   §3Vorteile: ", "§f§lKLICKE HIER", "Klick mich!", "/shop titan");
			p.sendMessage("§7Um dir einen Rang zu kaufen musst du cevill33 in Skype,");
			p.sendMessage("§7Ts oder Ingame anschreiben. Zahlungsmöglichkeit: PaySafeCard");
			p.sendMessage("§5[]------------------- §fShop §5-------------------[]");
			
			return true;
		}
		
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("elite")) {
				
				p.sendMessage("§5[]------------------- §eELITE §5-------------------[]");
				p.sendMessage(" ");
				p.sendMessage(" §3Kosten: §f20Euro");
				p.sendMessage(" §3Dauer : §fFür immer");
				p.sendMessage(" §3Vorteile:");
				p.sendMessage("   §3- §fElite Magics");
				p.sendMessage("   §3- §fSchnellerer teleport");
				p.sendMessage("   §3- §f/wb(Mobile Werkbank)");
				p.sendMessage("   §3- §f/enderchest(Mobile Enderchest");
				p.sendMessage("   §3- §f/song (Höre Songs)");
				p.sendMessage("   §3- §f1x GodKey");
				p.sendMessage("   §3- §fDauerhafter EXP-Bonus(50%)");
				p.sendMessage("   §3- §f/firework");
				p.sendMessage(" ");
				p.sendMessage("§7Um dir einen Rang zu kaufen musst du cevill33 in Skype,");
				p.sendMessage("§7Ts oder Ingame anschreiben. Zahlungsmöglichkeit: PaySafeCard");
				p.sendMessage("§5[]------------------- §eELITE §5-------------------[]");
				
				
			}
			
			if(args[0].equalsIgnoreCase("titan")) {
				
				p.sendMessage("§5[]------------------- §aTITAN §5-------------------[]");
				p.sendMessage(" ");
				p.sendMessage(" §3Kosten: §f40Euro");
				p.sendMessage(" §3Dauer : §fFür immer");
				p.sendMessage(" §3Vorteile:");
				p.sendMessage("   §3- §fElite & Titan Magics");
				p.sendMessage("   §3- §fSchnellerer teleport");
				p.sendMessage("   §3- §f/wb(Mobile Werkbank)");
				p.sendMessage("   §3- §f/enderchest(Mobile Enderchest");
				p.sendMessage("   §3- §f/song (Höre Songs)");
				p.sendMessage("   §3- §f1x GodKey");
				p.sendMessage("   §3- §fDauerhafter EXP-Bonus(100%)");
				p.sendMessage("   §3- §f/firework");
				p.sendMessage(" ");
				p.sendMessage("§7Um dir einen Rang zu kaufen musst du cevill33 in Skype,");
				p.sendMessage("§7Ts oder Ingame anschreiben. Zahlungsmöglichkeit: PaySafeCard");
				p.sendMessage("§5[]------------------- §aTITAN §5-------------------[]");
				
				
			}
			
			
			
		}
		
		
		
		
		
		
		
		return false;
	}

}
