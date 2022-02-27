package Db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection createConnection() {
		if(conn == null) {
			Properties prop = loadProperties();
			String url = prop.getProperty("dburl");
			try {
				conn = DriverManager.getConnection(url,prop);
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
		}
		catch(IOException e) {
			System.out.println("Error in load properties: " + e.getMessage());
			return null;
		}
	}
	
}
