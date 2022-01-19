package request;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Frame.Produit;
import Proprietes.Produits;

public class ProduitRequest {
	static ProduitRequest instance = null;
	Produits produit;
	Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "")
			.getConnection();
	Statement st;
	ResultSet rst;

	private ProduitRequest() {
		super();
	}

	public static ProduitRequest getInstance() {
		if (instance == null)
			instance = new ProduitRequest();
		return instance;
	}

	public void InsertData(String NomProduit, String Reference, String Stock, String Prix, String Description) {
		try {
			String nom, reference, description;
			int prix, stock;
			nom = NomProduit;
			reference = Reference;
			stock = Integer.valueOf(Stock);
			prix = Integer.valueOf(Prix);
			description = Description;

			produit = Produits.getInstance(nom, reference, stock, prix, description);

			st = conn.createStatement();
			String sql = "INSERT INTO `produit` (`reference` , `nom` , `stock` , `prix` , `description` , `id_patron`)"
					+
					" values ('" + produit.getReference() + "','" + produit.getNom_Produits() + "',"
					+ produit.getStock() + "," + produit.getPrix() + ",'" + produit.getDescription() + "'," + 1 + ")";

			st.executeUpdate(sql);
			System.out.println("Success");

		} catch (InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
					"ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " Impossible d'ajouter !", null, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void Delete(String nom) {
		try {
			String sq = "delete from produit where nom='" + nom + "'";

			st = conn.createStatement();
			if (JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ?", null,
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				st.executeUpdate(sq);
				JOptionPane.showMessageDialog(null, "Suppr�ssion r�ussie !");
				// dispose();
				// Produit_crud pr=new Produit_crud();
				// pr.setVisible(true);
			}
		} catch (InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
					"ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, " Impossible de supprimer !", null, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void AjouterQuantite(String nom, int quantite) {
		try {
			String sq = "update produit set stock=stock+'" + quantite + "' where nom='" + nom + "'";

			st = conn.createStatement();
			if (JOptionPane.showConfirmDialog(null, "Voulez-vous ajoutez la quantit� de ce produit?", null,
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				st.executeUpdate(sq);
				JOptionPane.showMessageDialog(null, "Ajout reussie !");
			}

		} catch (InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
					"ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			JOptionPane.showMessageDialog(null, " Impossible de mettre a jour cette valeur !", null,
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void UpdateQuantite(String nom, int qte) {
		try {
			String sq = "update produit set stock=+'" + qte + "' where nom='" + nom + "'";
			st = conn.createStatement();
			st.executeUpdate(sq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JTable AfficherProduit(JTable tb1, JPanel pn) {
		DefaultTableModel df = new DefaultTableModel();
		df.addColumn("Nom ");
		df.addColumn("Reference ");
		df.addColumn("Quantite ");
		df.addColumn("Prix ($)");
		df.addColumn("Description   ");
		tb1.setModel(df);
		String sql = "select nom,reference,stock,prix,description from produit";
		try {
			st = conn.createStatement();
			rst = st.executeQuery(sql);
			while (rst.next()) {
				df.addRow(new Object[] {
						rst.getString("nom"),
						rst.getString("reference"),
						rst.getString("stock"),
						rst.getString("prix"),
						rst.getString("description")
				});
			}
		} catch (SQLException ex) {

		}
		return tb1;
	}

	public Choice SelectProduit(Choice nom) {
		try {
			st = conn.createStatement();
			rst = st.executeQuery("SELECT nom FROM produit");

			while (rst.next()) {
				nom.add(rst.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	}

	public int getProducId(String name) {
		int nbre = 0;
		try {
			st = conn.createStatement();
			String req = "SELECT id FROM produit WHERE nom ='" + name + "'";
			rst = st.executeQuery(req);

			if (rst.next()) {
				nbre = rst.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbre;
	}
}
