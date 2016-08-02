package me.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.survival.api.ClickableChat;

public class Command_Enchanting implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args) {

		Player p = (Player) cs;
		if(args.length == 0) {
			p.sendMessage("§3Enchanting:");
			p.sendMessage("§7Enchanting(Verzaubern) ist auf dem VetoxServer anders. Wenn man auf den Verzauberungstisch klickt §föfnet sich ein Inventar mit"
					+ " mit Bücherregalen. Jedes Bücherregal hat einen Namen, der für eine Verzauberung steht. Wenn man auf ein Ragal klickt, öffnet sich ein"
					+ " neues Inventar links legt man das Item rein welches man Verzaubern will. Dannach wird angezeigt wie viele Feuerbälle(§aEnchanter§7) man reinlegen "
					+ "muss. Wenn man genug Enchanter hatt legt man sie in das freie Kästchen und drückt auf Bestätigen(Emeraldblock). Wenn du nicht weiß, wie man einen "
					+ "Enchanterfeuerball craftet,");
			ClickableChat.send(p, "§7Dann klicke hier drauf: ", "§f§lHIER", "§aDrück mich!", "/recipe enchanter");
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
