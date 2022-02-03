package request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Frame.Accueil;
import Proprietes.Caissier;
import Proprietes.Caissiers;
import Proprietes.Patron;

public class UserRequest {

	static UserRequest instance = null;
	Connection connection = BdConnection
			.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	Statement st;
	ResultSet result;

	private UserRequest() {
		super();
	}

	public static UserRequest getInstance() {
		if (instance == null)
			instance = new UserRequest();
		return instance;
	}

	public Patron loginPatron(String email, String password) {
		Patron pat = null;
		String sqlR = "Select * from patron where email='" + email + "' and password='" + password + "'";
		try {
			st = connection.createStatement();
			this.result = st.executeQuery(sqlR);

			if (result.next()) {
				String nom = result.getString("nom");
				int id = result.getInt("id");
				pat = Patron.getInstance(id, nom, password, email);
				return pat;
			} else {
				JOptionPane.showMessageDialog(null,
						"Email ou mot de passe invalide", "ERREUR", JOptionPane.ERROR_MESSAGE);
				
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Patron.getInstance();
	}

	public Caissier loginCaissier(String telephone, String password) {
		Caissier caiss = null;
		String sqlR = "Select * from caissier where telephone='" + telephone + "' and password='" + password + "'";
		try {
			st = connection.createStatement();
			this.result = st.executeQuery(sqlR);

			if (result.next()) {
				String nom = result.getString("nom");
				int id = result.getInt("id");
				caiss = Caissier.getInstance(nom, password, telephone);
				caiss.setId(id);
				return caiss;
			} else
				JOptionPane.showMessageDialog(null,
						"Telephone ou mot de passe invalide", "ERREUR", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Caissier.getInstance();
	}

}
