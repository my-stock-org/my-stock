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

import Proprietes.Clients;
import Proprietes.Commandes;

public class ClientRequest {

	 static ClientRequest instance= null;
	 Connection connection = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	 Statement st;
	 ResultSet result;
	 Clients client=null;
	private ClientRequest() {
			super();
		}
		
	public static ClientRequest getInstance() {
			if(instance == null)
					instance = new ClientRequest();
			return instance;
		}
	
public void  createClient(String nom,String addr, String telephone, int idPatron) {
		
		try {
			String 
			addresse=addr,
			tel= telephone;
			
			int IDpatron = Integer.valueOf(idPatron);
			client= Clients.getInstance(nom, tel,addresse, IDpatron);
			client.setIdPatron(IDpatron);
//			System.out.println(client.getNom()+"','"+client.getTelephone()+"',"+client.getAddresse()+","+client.getPatronId());
			
			st = connection.createStatement();
			String sql = "INSERT INTO `client` (`nom` , `telephone` , `addresse` , `id_patron`)" +
	                " values ('"+client.getNom()+"','"+client.getTelephone()+"','"+client.getAddresse()+"','"+client.getPatronId()+"')";
			
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,
		            " Client ajouté avec succès", "SUCCES", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(InputMismatchException E) {
			JOptionPane.showMessageDialog(null,
		            "ERROR !!! verifier les donnes entrées", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null," Validation impossible!",null,JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

public  void DeleteClient(String nom) {
	try {
		String sq="delete from client where nom='"+nom+"'";

		st= connection.createStatement();
		if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ce client de l'entreprise?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
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

public  JTable ListClient(JTable tb1, JPanel pn) {
	DefaultTableModel df=new DefaultTableModel();
	df.addColumn("ID ");
	df.addColumn("Nom ");
	df.addColumn("Téléphone ");
	df.addColumn("Addresse ");
	tb1.setModel(df);
	String sql="select id,nom,addresse,telephone from client";
	try{
		st= connection.createStatement();
		result=st.executeQuery(sql);
		while(result.next()){
			df.addRow(new Object[]{
					result.getInt("id"),
					result.getString("nom"),
					result.getString("addresse"),
					result.getString("telephone"),
			});
		}
	}
	catch(SQLException ex){
		System.out.println("Erreur \n"+ex);
	}
	return tb1;
}

public Choice SelectClient( Choice nom) {
	try {
		st= connection.createStatement();
		result = st.executeQuery("SELECT nom FROM client");
		
		while (result.next())
			nom.add(result.getString(1));	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return nom;
}

}