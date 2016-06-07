package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.survival.Main;

public class PlayerDataManager {

	
	
	public static boolean isPlayerDataExisting(String UUID) {
		ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID= '" + UUID + "' ");
		try {
			if(rs.next()) {
				try {
					
					return rs.getString("UUID") != null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	
	public static void createPlayerData(String UUID, String name) {
		if(!isPlayerDataExisting(UUID)) {
			Main.mysql.update("INSERT INTO PlayerData(UUID, Name, GsTutorial , LocX , LocY , LocZ, LocWorld, BanPoints, MutePoints, ReportPoints, XrayPoints, Level, DeletePoints, Hours, Minutes, SwordID, SwordSharpness, SwordSlot, Kills, Deaths, Votes, Xp, Magics, BuilderRang, Tutorial1) VALUES('" + UUID + "', '" + name + "', '0', '0', '0', '0', 'world', '0', '0', '0', '0', '1', '100', '0', '0', '272', '0', '1', '0', '0', '0', '0', '0', '2', '0')");
			setLocation(UUID, Main.spawn);
		}	
	}
	
	public static Double getX(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("LocX");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static Double getY(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("LocY");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static Double getZ(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("LocZ");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static String getWorld(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getString("LocWorld");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return "ERROR";
			}	
		}
		return "ERROR";
	}
	
	public static Location getLocation(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			return new Location(Bukkit.getWorld(getWorld(UUID)), getX(UUID),getY(UUID), getZ(UUID));
			
			
		} else {
			return Main.spawn;
		}
		
		
	}
	
	public static Double getHomeX(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("HomeX");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static Double getHomeY(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("HomeY");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static Double getHomeZ(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getDouble("HomeZ");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1D;
			}	
		}
		return -1D;
	}
	
	public static String getHomeWorld(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getString("HomeWorld");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return "ERROR";
			}	
		}
		return "ERROR";
	}
	
	public static Location getHomeLocation(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			return new Location(Bukkit.getWorld(getHomeWorld(UUID)), getHomeX(UUID),getHomeY(UUID), getHomeZ(UUID));
			
			
		} else {
			return null;
		}
		
		
	}
	
	
	public static String getName(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getString("Name");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return "ERROR";
			}	
		}
		return "ERROR";
	}
	
	public static Integer getGsTutorial(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("GsTutorial");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
		
	public static Integer getBanPoints(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("BanPoints");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getMutePoints(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("MutePoints");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getXrayPoints(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("XrayPoints");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getLevel(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Level");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	
	public static Integer getHours(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Hours");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getMinutes(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Minutes");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getSwordID(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("SwordID");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return 268;
	}
	
	public static Integer getSwordSharpness(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("SwordSharpness");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	
	public static Integer getSwordSlot(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("SwordSlot");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	
	public static Integer getKills(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Kills");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	
	public static Integer getDeaths(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Deaths");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static Integer getVotes(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Votes");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	
	public static Integer getXp(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Xp");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}	
		}
		return -1;
	}
	
	public static String getMagics(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getString("Magics");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return "ERROR";
			}	
		}
		return "ERROR";
	}
	
	public static int getBuilderRang(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("BuilderRang");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return 2;
			}	
		}
		return 2;
	}
	
	public static int getTutorial1(String UUID) {
		if(isPlayerDataExisting(UUID)) {
			ResultSet rs = Main.mysql.query("SELECT * FROM PlayerData WHERE UUID = '" + UUID + "' ");
			try {
				while((rs.next())) {
					return rs.getInt("Tutorial1");
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return 0;
			}	
		}
		return 0;
	}
	
	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * Setter:
	 * 
	 * 
	 * 
	 */
	
	
	
	
	public static void setX(String UUID, double x) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET LocX= '" + x  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setY(String UUID, double y) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET LocY= '" + y  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setZ(String UUID, double z) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET LocZ= '" + z  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setWorld(String UUID, String world) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET LocWorld= '" + world  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setLocation(String UUID, Location loc) {
		if(isPlayerDataExisting(UUID)) {
			setX(UUID, loc.getBlockX());
			setY(UUID, loc.getBlockY());
			setZ(UUID, loc.getBlockZ());
			setWorld(UUID, loc.getWorld().getName());
		}
	}
	
	///
	public static void setHomeX(String UUID, double x) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET HomeX= '" + x  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setHomeY(String UUID, double y) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET HomeY= '" + y  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setHomeZ(String UUID, double z) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET HomeZ= '" + z  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setHomeWorld(String UUID, String world) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET HomeWorld= '" + world  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setHomeLocation(String UUID, Location loc) {
		if(isPlayerDataExisting(UUID)) {
			setHomeX(UUID, loc.getBlockX());
			setHomeY(UUID, loc.getBlockY());
			setHomeZ(UUID, loc.getBlockZ());
			setHomeWorld(UUID, loc.getWorld().getName());
		}
	}
	
	
	public static void setGsTutorial(String UUID, int gs) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET GsTutorial= '" + gs  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setHours(String UUID, int hours) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Hours= '" + hours  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setMinutes(String UUID, int minutes) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Minutes= '" + minutes  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setSwordID(String UUID, int swordID) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET SwordID= '" + swordID  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setSwordSharpness(String UUID, int swordSharpness) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET SwordSharpness= '" + swordSharpness  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setSwordSlot(String UUID, int swordSlot) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET SwordSlot= '" + swordSlot  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setKills(String UUID, int kills) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Kills= '" + kills  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setDeaths(String UUID, int deaths) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Deaths= '" + deaths  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setVotes(String UUID, int votes) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Votes= '" + votes  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setXp(String UUID, int xp) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Xp= '" + xp  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setLevel(String UUID, int level) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Level= '" + level  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setMagics(String UUID, String magics) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Magics= '" + magics  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	
	public static void setBuilderRang(String UUID, int rang) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET BuilderRang= '" + rang  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	public static void setTutorial1(String UUID, int tutorial1) {
		if(isPlayerDataExisting(UUID)) {
			Main.mysql.update("UPDATE PlayerData SET Tutorial1= '" + tutorial1  + "' WHERE UUID = '" + UUID + "' ;");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
