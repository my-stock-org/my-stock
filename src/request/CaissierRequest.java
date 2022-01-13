package request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import Proprietes.Caissiers;
import Proprietes.Produits;

public class CaissierRequest {
      static CaissierRequest instance= null;
      Caissiers caissier ;
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
			
			
			caissier= Caissiers.getInstance( nom, pass, telephones);
			
			cs = conn.createStatement();
			String sql = "INSERT INTO `caissier` (`nom` , `password` , `telephone`)" +
	                " values ('"+caissier.getTelephone()+"','"+caissier.getPassword()+"','"+caissier.getTelephone()+"')";
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
				JOptionPane.showMessageDialog(null,"Suppréssion réussie !");
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
}
