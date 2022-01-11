package request;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import Frame.Produit;
import Proprietes.Produits;

public class ProduitRequest {
	 static ProduitRequest instance= null;
	 Produits produit;
	 Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	
	private ProduitRequest() {
		super();
	}
	public static ProduitRequest getInstance() {
		if(instance == null)
				instance = new ProduitRequest();
		return instance;
	}
	
	public  void InsertData(String NomProduit, String Reference, String Stock, String Prix, String Description) {
		try {
			String nom,reference,description;
			int prix,stock;
			nom = NomProduit;
			reference = Reference;
			stock=Integer.valueOf(Stock);
			prix= Integer.valueOf(Prix);
			description = Description;
			
			produit= Produits.getInstance(nom, reference, stock, prix, description);
			
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO `produit` (`reference` , `nom` , `stock` , `prix` , `description` , `id_patron`)" +
	                " values ('"+produit.getReference()+"','"+produit.getNom_Produits()+"',"+produit.getStock()+","+produit.getPrix()+",'"+produit.getDescription()+"',"+1+")";
			
			stmt.executeUpdate(sql);
			System.out.println("Success");
			
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null," Impossible d'ajouter !",null,JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public  void Delete(String nom) {
		try {
			String sq="delete from produit where nomprod='"+nom+"'";

			Statement st= conn.createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				st.executeUpdate(sq);
				JOptionPane.showMessageDialog(null,"Suppréssion réussie !");
//				dispose();
//				Produit_crud pr=new Produit_crud();
//				pr.setVisible(true);
			}
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			  JOptionPane.showMessageDialog(null," Impossible de supprimer !",null,JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public  void AjouterQuantite(String nom, int quantite) {
		try {
			String sq="update produit set stock=stock+'"+quantite+"' where nomprod='"+nom+"'";
			
			Statement st= conn.createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez cette quantité?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
			   st.executeUpdate(sq);
			   JOptionPane.showMessageDialog(null,"Ajout reussie !");
			}
			 
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			  JOptionPane.showMessageDialog(null," Impossible de supprimer !",null,JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
