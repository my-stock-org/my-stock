package request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
					if (Caissier.getInstance() != null) {
						CaissierRequest.getInstance().UpdateMontant(Caissier.getInstance().getNom(), total);
					}
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
					" La commande s'élève a  " + commande.getTotal() + "FCFA\n Voulez vous la valider", "Validation",
					JOptionPane.INFORMATION_MESSAGE);
			if (confirm == JOptionPane.YES_OPTION) {
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,
						" Commande validé avec succès ?", "Succès",
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

	public JTable AfficherCommande(JTable tb1, JPanel pn) {
		DefaultTableModel df = new DefaultTableModel();
		df.addColumn("# ");
		df.addColumn("Reference ");
		df.addColumn("Total ");
		df.addColumn("Fait le");
		df.addColumn("Modifié le   ");
		tb1.setModel(df);
		String sql = "select id,reference,total,create_at,update_at from commande";
		try {
			st = connection.createStatement();
			result = st.executeQuery(sql);
			while (result.next()) {
				df.addRow(new Object[] {
						result.getString("id"),
						result.getString("reference"),
						result.getString("total"),
						result.getString("create_at"),
						result.getString("update_at")
				});
			}
		} catch (SQLException ex) {
			System.out.println("Erreur lors de l'affichage");
		}
		return tb1;
	}
	
	public String[][] getCommande( ) {
		String[][] donnees = null;
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM commande");
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] entetes=new String[metaData.getColumnCount()];
			int j;
				for(int i = 0; i<metaData.getColumnCount();i++){
					j=i+1;
					entetes[i]=metaData.getColumnName(j);
				}
		    int taille=0,k=0;
		   
		    while (resultSet.next()){
		    	taille++;
		    }
		    Statement statement1 = connection.createStatement();
			ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM commande");
			ResultSetMetaData metaData1 = resultSet.getMetaData();
			donnees=new String[taille][metaData.getColumnCount()];
		    String[] tab=new String[metaData.getColumnCount()];
			while (resultSet1.next()){
				
				for(int i = 0; i<metaData1.getColumnCount();i++){
					j=i+1;
					tab[i]=resultSet1.getString(j);
					donnees[k][i]=tab[i];
				}
				k++;
			}
			return donnees;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnees;
	}

	public Choice SelectCommande(Choice nom) {
		try {
			st = connection.createStatement();
			result = st.executeQuery("SELECT reference FROM commande");

			while (result.next()) {
				nom.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	}

	public void DeleteCommande(String ref) {
		String sq = "DELETE FROM commande WHERE reference ='" + ref + "'";
		try {
			st = connection.createStatement();
			int confirm = JOptionPane.showConfirmDialog(null,
					"Voulez vous vraiment revoquer cette commande ?", "Annulation",
					JOptionPane.INFORMATION_MESSAGE);
			if (confirm == JOptionPane.YES_OPTION) {
				CaissierRequest.getInstance().UpdateMontant(Caissier.getInstance().getNom(), -GetPrice(ref));
				st.executeUpdate(sq);
				JOptionPane.showMessageDialog(null,
						" Commande revoqué avec succès ?", "Succès",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " Revocation impossible réssayer plus tard!", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public int GetPrice(String ref) {
		int nbre = 0;
		try {
			st = connection.createStatement();
			String sql = "SELECT total FROM commande WHERE reference ='" + ref + "'";
			result = st.executeQuery(sql);
			if (result.next()) {
				nbre = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur inattendue");
		}
		System.out.println(nbre);
		return nbre;
	}

}
