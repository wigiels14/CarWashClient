package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private String url = "jdbc:postgresql://localhost:5432/CarWash";
	private String username = "postgres";
	private String password = "admin";

	public boolean connectToDatabase() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return true;
	}
}
