package request;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	 static ProduitRequest instance= null;
	 Produits produit;
	 Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	 Statement st;
	ResultSet rst;
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
			
			st = conn.createStatement();
			String sql = "INSERT INTO `produit` (`reference` , `nom` , `stock` , `prix` , `description` , `id_patron`)" +
	                " values ('"+produit.getReference()+"','"+produit.getNom_Produits()+"',"+produit.getStock()+","+produit.getPrix()+",'"+produit.getDescription()+"',"+1+")";
			
			st.executeUpdate(sql);
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
			String sq="delete from produit where nom='"+nom+"'";

			st= conn.createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				st.executeUpdate(sq);
				JOptionPane.showMessageDialog(null,"Suppréssion réussie !");
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
			String sq="update produit set stock=stock+'"+quantite+"' where nom='"+nom+"'";
			
			st= conn.createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez la quantité de ce produit?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
			   st.executeUpdate(sq);
			   JOptionPane.showMessageDialog(null,"Ajout reussie !");
			}
			 
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null," Impossible de mettre a jour cette valeur !",null,JOptionPane.ERROR_MESSAGE);
		}
	}
	public  JTable AfficherProduit(JTable tb1, JPanel pn) {
		try{
		DefaultTableModel df=new DefaultTableModel();
		df.addColumn("Nom ");
		df.addColumn("Reference ");
		df.addColumn("Quantite ");
		df.addColumn("Prix ($)");
		df.addColumn("Description   ");
		tb1.setModel(df);
		String sql="select nom,reference,stock,prix,description from produit";
		
			st= conn.createStatement();
			rst=st.executeQuery(sql);
			while(rst.next()){
				df.addRow(new Object[]{
					rst.getString("nom"),
					rst.getString("reference"),
					rst.getString("stock"),
					rst.getString("prix"),
					rst.getString("description")
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null," Impossible d'afficher la liste de produit !",null,JOptionPane.ERROR_MESSAGE);
		}
		return tb1;
	}
	public Choice SelectProduit( Choice nom) {
		try {
			st= conn.createStatement();
			rst = st.executeQuery("SELECT nom FROM produit");
			
			while (rst.next()){
				nom.add(rst.getString(1));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	}
	
	public String[][] getProduit( ) {
		String[][] donnees = null;
		try {
			
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM produit");
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
		    Statement statement1 = conn.createStatement();
			ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM produit");
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
	
}
