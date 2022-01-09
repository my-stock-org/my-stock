package my_stock;
import java.sql.*;  

public final class BdConnection {

	private static BdConnection instance= null ;
	private Connection connect;
	private static String driverType = "com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost/mystock";
	private static String userName="root";
	private static String password="";
	
	
	private BdConnection(String driverType, String url, String username, String password) {
		try {
			Class.forName(driverType);  
			this.connect = DriverManager.getConnection(url, username, password);
			System.out.println("Connexion a la base de donnee etablie");
		}catch(Exception e){ 
			System.out.println("Echec de la connexion a la base de donnees\n" +e);
			}  
	}
	
	public static BdConnection getInstance() {
		
		if(instance == null)
				instance = new BdConnection(driverType, url, userName,password);
		return instance;
	}
	
	Connection getConnection() {
		 return this.connect;
	}
	
	void setConnection(Connection con) {
		this.connect = con;
	}
	
}
