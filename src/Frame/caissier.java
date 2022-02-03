package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import request.BdConnection;


public class caissier extends  JFrame  implements Fenetre {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	//private JTextField textField_2;
	private JTextField textField_3;
	 private static caissier instance = null;
		// Constructeur de l'objet.
		private caissier() {
			super();
			proprieteFenetre();
		}
		static caissier getInstance()
		{
			if (instance == null) 
				{
					instance = new caissier();
				}
			return instance;
		}
	    
		 public void proprieteFenetre(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 350, 280);
			setLocationRelativeTo(null);// position de la fenetre a l'ecran
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			this.setVisible(true);
			
			JLabel NomLabel = new JLabel("nom");
			NomLabel.setBounds(28, 19, 90, 33);
			contentPane.add(NomLabel);
			
			textField = new JTextField();
			textField.setBounds(120, 20, 160, 32);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel PassLabel = new JLabel("Password");
			PassLabel.setBounds(28, 70, 90, 33);
			contentPane.add(PassLabel);
			
			textField_1 = new JTextField();
			textField_1.setBounds(120, 70, 160, 33);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			
			
			JLabel TelephoneLabel = new JLabel("Telephone");
			TelephoneLabel.setBounds(28, 110, 90, 33);
			contentPane.add(TelephoneLabel);
			
			textField_3 = new JTextField();
			textField_3.setBounds(120, 110, 160, 33);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
			
			JButton CreerButton = new JButton("CREER");
			CreerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent argo) {
	                 try {
	                	 String req =" insert into caissier( nom, password, telephone) values ('"+textField.getText()+"','"+textField_1.getText()+"' ,'"+textField_3.getText()+"') ";

	                	 Connection connection = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	                	 
	                	 Statement aj =  connection.createStatement();
	                	  aj.executeUpdate(req);
	    
	                	 JOptionPane.showMessageDialog(null, "caissier bien ajouter ");
	                	  setVisible(false);

	                 }catch(Exception e) {
	                	 System.out.println("erreur"+e); 
	                 }
	                 
  
				}
			});
			
			CreerButton.setBounds(50, 160, 90, 33);
			contentPane.add(CreerButton);
			
			JButton btnNewButton_1 = new JButton("ANNULER");
			btnNewButton_1.setBounds(170, 160, 90, 33);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Fermer_actionPerformed(e);
				}
			});
			contentPane.add(btnNewButton_1);
		}
	public void openFrame() {
		this.setVisible(true);
	}
			
	// fermer
	public void Fermer_actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
