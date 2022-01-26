package request;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Proprietes.Caissier;
import Proprietes.Produits;

public class CaissierRequest {
      static CaissierRequest instance= null;
      Caissier caissier ;
      Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
      Statement cs;
  	ResultSet rcst;
  	private CaissierRequest() {
	super();
	}
	public static CaissierRequest getInstance() {
		if(instance == null)
				instance = new CaissierRequest();
		return instance;
	}
	
	public  void InsertData(String name, String telephone, String password) {
		try {
			String nom,telephones,pass;
		
		
			nom = name;
			pass = password;
			telephones=telephone;
			
			
			caissier= Caissier.getInstance( nom, pass, telephones);
			
			cs = conn.createStatement();
			String sql = "INSERT INTO `caissier` (`nom` , `password` , `telephone`)" +
	                " values ('"+caissier.getNom()+"','"+caissier.getPassword()+"','"+caissier.getTelephone()+"')";
			cs.executeUpdate(sql);
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
			String sq="delete from caissier where nom='"+nom+"'";

			cs= conn.createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				cs.executeUpdate(sq);
				JOptionPane.showMessageDialog(null,"Suppr�ssion r�ussie !");
//				
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

public  JTable AfficheCaissier(JTable tb1, JPanel pn) {
	try{
	DefaultTableModel df=new DefaultTableModel();
	df.addColumn("Nom ");
	df.addColumn("telephone ");
	df.addColumn("Montant Vendue En Caisse ");

	tb1.setModel(df);
	String sql="select nom,telephone,montant_vendu_Caissier from caissier";
	
	cs= conn.createStatement();
	rcst=cs.executeQuery(sql);
		while(rcst.next()){
			df.addRow(new Object[]{
					rcst.getString("nom"),
					rcst.getString("telephone"),
					rcst.getInt("montant_vendu_Caissier"),
					
			});
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		  JOptionPane.showMessageDialog(null," Impossible d'afficher la liste des caissiers !",null,JOptionPane.ERROR_MESSAGE);
	}
	return tb1;
}

public Choice SelectCaissier( Choice nom) {
	try {
		cs= conn.createStatement();
		rcst = cs.executeQuery("SELECT nom FROM caissier");
		
		while (rcst.next()){
			nom.add(rcst.getString(1));	
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return nom;
}

public  void AjouterMontant(String nom, int montant) {
	try {
		String sq="update caisier set montant_vendu_Caissier=montant_vendu_Caissier+'"+montant+"' where nom='"+nom+"'";
		
		cs= conn.createStatement();
		if(JOptionPane.showConfirmDialog(null,"Voulez-vous modifier le montant en Caisse?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
			cs.executeUpdate(sq);
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
}
