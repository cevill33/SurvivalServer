package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.survival.Main;

@Deprecated
//MySQL we dont use any more!
public class MySQL {

	private String HOST = "";
	private String DATABASE = "";
	private String USER = "";
	private String PASSWORD = "";
	
	private  Connection con;
	
	public MySQL(String host, String DataBase, String User, String Password) {
		this.HOST = host;
		this.DATABASE = DataBase;
		this.USER = User;
		this.PASSWORD = Password;
		
		connect();
		
	}
	
	public void connect() {
		try {
		con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
		System.out.println("MYSQL verbindung hergestellt!"); } catch (SQLException e) {
			System.err.println("MYSQL Die Verbindun wurde fehlgeschlagen!");
			e.printStackTrace();
		}
		
	}
	
	
	public void close() {
		try {
			if(con != null) {
				con.close();
				System.out.println("MYSQ verbindung erfolgreich geschlossen!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("MYSQL Fehler bei beenden der verbindung zur MYSQL!");
		}
	}
	
	
	public void update(String qry) {
		try {
		Statement st = con.createStatement();
		st.executeUpdate(qry);
		st.close();
		} catch (SQLException e) {
			connect();
			e.printStackTrace();
		}
	}
	
	
	public ResultSet query(String qry) {
		ResultSet rs = null;
		
		try {
		Statement st = con.createStatement();
		rs = st.executeQuery(qry);
		
		} catch (SQLException e) {
			connect();
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void register() {
		
		//Main.mysql = new MySQL("localhost", "VetoxDB", "cevill33", "gsdfkFF!$e7qdatffLLFR&");
		//Main.mysql.update("CREATE TABLE IF NOT EXISTS PlayerData(UUID varchar(100), Name varchar(30), GsTutorial int(1), LocX double(30, 4), LocY double(30, 4), LocZ double(30, 4), LocWorld varchar(30), BanPoints int(20), MutePoints int(20), ReportPoints int(20), XrayPoints int(20), Level int(20), DeletePoints int(20), Hours int(100), Minutes int(100), SwordID int(100), SwordSharpness int(100), SwordSlot int(100), Kills int(100), Deaths int(100), Votes int(100), Xp int(100), Magics varchar(500), BuilderRang int(100), Tutorial1 int(2), HomeX double(30, 4), HomeY double(30, 4), HomeZ double(30, 4), HomeWorld varchar(30))");
		
	}
	
	
	public static void registerLocal() {
		
		//Main.mysql = new MySQL("localhost", "clan", "root", "");
		//Main.mysql.update("CREATE TABLE IF NOT EXISTS PlayerData(UUID varchar(100), Name varchar(30), GsTutorial int(1), LocX double(30), LocY double(30), LocZ double(30), LocWorld varchar(30), BanPoints int(20), MutePoints int(20), ReportPoints int(20), XrayPoints int(20), Level int(20), DeletePoints int(20), Hours int(100), Minutes int(100), SwordID int(100), SwordSharpness int(100), SwordSlot int(100), Kills int(100), Deaths int(100), Votes int(100), Xp int(100), Magics varchar(500), BuilderRang int(100), Tutorial1 int(2))");
	}
	
	
	 
}
