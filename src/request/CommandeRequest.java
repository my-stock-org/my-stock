package request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;
import java.awt.Choice;

import Frame.Accueil;
import Proprietes.Caissier;
import Proprietes.Commandes;
import Proprietes.Patron;

public class CommandeRequest {

	static CommandeRequest instance = null;
	Commandes commande;
	Connection connection = BdConnection
			.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	Statement st;
	ResultSet result;

	private CommandeRequest() {
		super();
	}

	public static CommandeRequest getInstance() {
		if (instance == null)
			instance = new CommandeRequest();
		return instance;
	}

	public void createCommande(String ref, String clientName, String produitName, String qte) {
		int productQte = Integer.valueOf(qte);
		int clientId = ClientRequest.getInstance().getClientId(clientName);
		// int patronId = Patron.getInstance().getId();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String date = dtf.format(LocalDateTime.now());
		try {
			st = connection.createStatement();
			String req = "SELECT * FROM produit WHERE nom ='" + produitName + "'";
			result = st.executeQuery(req);

			if (result.next()) {
				int productId = result.getInt("id");
				int productPrice = result.getInt("prix");
				int productStock = result.getInt("stock");
				if (productQte > productStock) {
					JOptionPane.showMessageDialog(null,
							" Stock insuffisant ", "ERREUR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int total = productQte * productPrice;
					int reste = productStock - productQte;
					InsertData(ref, clientId, productId, total, date);
					ProduitRequest.getInstance().UpdateQuantite(produitName, reste);
//					if (Caissier.getInstance() != null) {
//
//					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Choice SelectProduit(Choice nom) {
		try {
			st = connection.createStatement();
			result = st.executeQuery("SELECT nom FROM produit");

			while (result.next()) {
				nom.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	}

	public Choice SelectClient(Choice nom) {
		try {
			st = connection.createStatement();
			result = st.executeQuery("SELECT nom FROM client");

			while (result.next()) {
				nom.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	}

	public void InsertData(String ref, int clientId, int produitId, int total, String date) {

		try {
			int prixTotal = Integer.valueOf(total),
					clientID = Integer.valueOf(clientId),
					produitID = Integer.valueOf(produitId);

			commande = Commandes.getInstance(ref, clientID, produitID, prixTotal, date);
			System.out.println(commande.getDateModification());
			st = connection.createStatement();
			String sql = "INSERT INTO `commande` (`reference` , `id_client` ,`id_produit` , `total` , `create_at` , `update_at`)"
					+
					" values ('" + commande.getReference() + "'," + commande.getClientId() +
					","
					+ commande.getProduitId() + "," + commande.getTotal() + ",'" +
					commande.getDateCreation() + "','"
					+ commande.getDateModification() + "')";

			int confirm = JOptionPane.showConfirmDialog(null,
					" La commande s'éléve à " + commande.getTotal() + "FCFA\n Voulez vous la valider", "Validation",
					JOptionPane.INFORMATION_MESSAGE);
			if (confirm == JOptionPane.YES_OPTION) {
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,
						" Commande validée avec succès ?", "Succès",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
					"ERROR !!! verifier les donnes entres", "Erreur fatale",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " Validation impossible!", null,
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
