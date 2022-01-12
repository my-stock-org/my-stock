package request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import Frame.Accueil;
import Proprietes.Caissier;
import Proprietes.Commandes;
import Proprietes.Patron;

public class CommandeRequest {
	
	static CommandeRequest instance= null;
	Commandes commande;
	 Connection connection = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	 Statement st;
	 ResultSet result;
	 
	private CommandeRequest() {
			super();
		}
		
	public static CommandeRequest getInstance() {
			if(instance == null)
					instance = new CommandeRequest();
			return instance;
		}
		
	public void  createCommande(String ref, int clientId, int produitId, int total, String date) {
		
		try {
			String 
			reference=ref,
			dateCreation= date;
			
			int prixTotal=Integer.valueOf(total), 
			clientID= Integer.valueOf(clientId),
			produitID = Integer.valueOf(produitId);
			
			commande= Commandes.getInstance(reference, clientID,produitID, prixTotal, dateCreation);
			
			st = connection.createStatement();
			String sql = "INSERT INTO `commande` (`reference` , `id_client` , `id_produit` , `total` , `create_at` , `update_at`)" +
	                " values ('"+commande.getReference()+"','"+commande.getClientId()+"',"+commande.getProduitId()+","+commande.getTotal()+",'"+commande.getDateCreation()+"',"+commande.getDateModification()+")";
			
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,
		            " Commande validée avec succès", "SUCCES", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null," Validation impossible!",null,JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

}
