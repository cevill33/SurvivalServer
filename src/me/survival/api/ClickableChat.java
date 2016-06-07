package me.survival.api;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class ClickableChat {

	public static void send(Player p, String normalmessage, String extramessage, String hovermessage, String command) {
		
		IChatBaseComponent chat = ChatSerializer.a("{\"text\":\"" + normalmessage + "\", \"extra\":[{\"text\":\"" + extramessage + "\",\"hoverEvent\":{\"action\":\"show_text\", \"value\":\"" + hovermessage +"\"}, \"clickEvent\": {\"action\":\"run_command\", \"value\":\"" + command + "\"}  }]}");
		
		
		PacketPlayOutChat packet = new PacketPlayOutChat(chat);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		
		
		
	}
	
	
	
	public static void send(Player p, String normalmessage, String extramessage, String hovermessage) {
		
		IChatBaseComponent chat = ChatSerializer.a("{\"text\":\"" + normalmessage + "\", \"extra\":[{\"text\":\"" + extramessage + "\",\"hoverEvent\":{\"action\":\"show_text\", \"value\":\"" + hovermessage +"\"} }]}");
		
		
		PacketPlayOutChat packet = new PacketPlayOutChat(chat);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
