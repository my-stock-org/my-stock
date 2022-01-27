package request;

import java.sql.*;

public final class BdConnection {

	private static BdConnection instance = null;
	private Connection connect;

	private BdConnection(String driverType, String url, String username, String password) {
		try {
			Class.forName(driverType);
			this.connect = DriverManager.getConnection(url, username, password);
			System.out.println("Connexion etabli");
		} catch (Exception e) {
			System.out.println("Echec de la connexion a la base de donnees\n" + e);
		}
	}

	public static BdConnection getInstance(String driverType, String url, String username, String password) {
		if (instance == null)
			instance = new BdConnection(driverType, url, username, password);
		return instance;
	}

	public Connection getConnection() {
		return this.connect;
	}

	void setConnection(Connection con) {
		this.connect = con;
	}

}
