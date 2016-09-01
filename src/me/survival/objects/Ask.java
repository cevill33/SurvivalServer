package me.survival.objects;

import java.util.ArrayList;
import java.util.List;

public class Ask {

	private String firstname;
	private String secoundsname;
	private String question;
	private String answer;
	
	
	public Ask(String firstname, String secoundname, String question, String answer) {
		this.firstname = firstname;
		this.secoundsname = secoundname;
		this.question = question;
		this.answer = answer;
		
		
		
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getSecoundsname() {
		return secoundsname;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	
	
	public static List<Ask> ask = new ArrayList<>();
	public static void registerQuestions() {
		ask.add(new Ask("bedeute", "gs", "Was bedeutet Gs?", "Das ist die Abkürzund für Grundstück"));
		ask.add(new Ask("bedeute", "plugin", "Was bedeutet Plugin?", "Durch Plugins kann der Server mehr Sachen machen!"));
		ask.add(new Ask("gibt", "job", "Gibt es Jobs?", "Zurzeit noch nicht!"));
		ask.add(new Ask("bewerben", "kann", "Kann ich mich als Teammitglied bewerben?", "Gebe /bewerben ein :)"));
		ask.add(new Ask("abo", "yt", "Wie viel Abos braucht man für Youtuber?", "Wenn du 500 Abos hast kannst du ein Teammitglied anschreiben!"));
		ask.add(new Ask("abo", "youtub", "Wie viel Abos braucht man für Youtuber?", "Gebe §7/youtube §aein!"));
		ask.add(new Ask("geld", "ha", "Wo kann man sehen wie viel Coins man hat?", "Gebe §7/geld §aein!"));
		ask.add(new Ask("coins", "ha", "Wo kann man sehen wie viel Coins man hat?", "Gebe §7/geld §aein!"));
		ask.add(new Ask("coins", "verdienen", "Wie kann man Coins verdienen?", "Möglichkeiten: In Monsterworld mobs töten, voten..."));
		ask.add(new Ask("geld", "verdienen", "Wie kann man Coins verdienen?", "Möglichkeiten: In Monsterworld mobs töten, voten..."));
		ask.add(new Ask("coins", "bekom", "Wie kann man Coins verdienen?", "Möglichkeiten: In Monsterworld mobs töten, voten..."));
		ask.add(new Ask("geld", "bekom", "Wie kann man Coins verdienen?", "Möglichkeiten: In Monsterworld mobs töten, voten..."));
		ask.add(new Ask("wo", "bau", "Wo kann man bauen?", "In der Hauptwelt wenn man sich ein Gs kauft ---> §7/gs§a!"));
		ask.add(new Ask("wer", "owner", "Wer ist der Owner von diesem Server?", "Zurzeit ist cevill33 Owner."));
		ask.add(new Ask("wer", "besi", "Wer ist der Owner von diesem Server?", "Zurzeit ist cevill33 Owner."));
		ask.add(new Ask("wie", "landschaf", "Wie habt ihr die Landschaft gebaut?", "Das ist leider geheim!"));
		ask.add(new Ask("wie", "berg", "Wie habt ihr die Landschaft gebaut?", "Das ist leider geheim!"));
		ask.add(new Ask("pl", "selbs", "Wurden die Plugins am Sever selbst pogrammiert?", "Alle Plugins außer WorldEdit u. Pex. wurden von cevill33 pogrammiert!"));
		ask.add(new Ask("welche", "ränge", "Welche Ränge gibt es auf diesem Server?", "Ränge: Owner, Admin, Mod, Supporter, Youtuber, Titan, Elite, Spieler"));
		ask.add(new Ask("ts", "ts", "Habt ihr einen TeamSpeakServer?", "Ja, die IP ist VetoxMC.de!"));
		ask.add(new Ask("gehostet", "wo", "Wo wird der Server gemietet?", "Beim Serveranbieter ims-hosting.de"));
		ask.add(new Ask("pferd", "wie", "Wie bekommt man ein Pferd?", "Gebe §7/ride §aein!"));
		ask.add(new Ask("farm", "farm", "Wie komme ich in die Farmwelt", "Durch einen Ballon. Einer ist z.B am Spawn!"));
		ask.add(new Ask("slot", "was", "Was ist ein Slot?", "Ein Slot ist ein Kästchen in deinem Minecraft Inventar."));
		ask.add(new Ask("op", "op", "Bekomme ich Op?", "Nein!"));
		ask.add(new Ask("chunk", "groß", "Wie groß ist ein Chunk!", "Ein Chunk ist 16*16 Blöcke groß!"));
		ask.add(new Ask("chunk", "breit", "Wie groß ist ein Chunk!", "Ein Chunk ist 16*16 Blöcke groß!"));
		ask.add(new Ask("chunk", "lang", "Wie groß ist ein Chunk!", "Ein Chunk ist 16*16 Blöcke groß!"));
		ask.add(new Ask("level", "level", "Wie werde ich ein höheres Level!", "Du musst Exp bekommen, siehe /stats!"));
		ask.add(new Ask("boost", "lange", "Wie lange hätl der EXP- Boost!", "Er dauert 90 minuten!"));
		ask.add(new Ask("server", "restart", "Wie offt gibt es einen Server Restart!", "Ca. jede Woche"));
		ask.add(new Ask("stunden", "stamm", "Wie viel Stunden braucht man um den Rang Stammspielr in Ts zu bekommen?", "30 Stunden."));
		ask.add(new Ask("lange", "stamm", "Wie viel Stunden braucht man um den Rang Stammspielr in Ts zu bekommen?", "30 Stunden."));
		ask.add(new Ask("ballon", "wo", "Wie komme ich zum Ballon?", "Ein Ballon befindet sich ca. 40 Blöcke vom Spawn entfernt(neben Markt)"));
		ask.add(new Ask("ballon", "komme", "Wie komme ich zum Ballon?", "Ein Ballon befindet sich ca. 40 Blöcke vom Spawn entfernt(neben Markt)"));
	}
	
	
	public static Ask getAsk(String tolowmsg) {
		
		for(Ask questions : Ask.ask) {
			
			if(tolowmsg.contains(questions.getFirstname())) {
				if(tolowmsg.contains(questions.getSecoundsname())) {
					return questions;
				}
			}
		}
		
		
		
		
		return new Ask("notfoudn", "nofound", "§cKeine Fragen gefunden!", "§cKeine Fragen gefunden!");
	}
	
	
	
}
