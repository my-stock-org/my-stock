package Frame;

import java.awt.Color;
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
import request.CaissierRequest;
import request.ProduitRequest;


public class Caissier extends  JFrame  implements Fenetre {
	
	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPassword;
	//private JTextField textField_2;
	private JTextField textTelephone;
	 private static Caissier instance = null;
		// Constructeur de l'objet.
		private Caissier() {
			super();
			proprieteFenetre();
		}
		static Caissier getInstance() 
		{
			if (instance == null) 
				{
					instance = new Caissier();
				}
			return instance;
		}
	    
		 public void proprieteFenetre(){
				setBounds(100, 100, 450, 443);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				contentPane.setBackground(Color.LIGHT_GRAY);
				this.setTitle("caissier");
			this.setVisible(true);
			
			JLabel NomLabel = new JLabel("nom");
			NomLabel.setBounds(28, 29, 90, 33);
			contentPane.add(NomLabel);
			
			textNom = new JTextField();
			textNom.setBounds(156, 30, 128, 32);
			contentPane.add(textNom);
			textNom.setColumns(10);
			
			JLabel PassLabel = new JLabel("Password");
			PassLabel.setBounds(28, 117, 90, 33);
			contentPane.add(PassLabel);
			
			textPassword = new JTextField();
			textPassword.setBounds(156, 117, 128, 33);
			contentPane.add(textPassword);
			textPassword.setColumns(10);
			
			
			JLabel TelephoneLabel = new JLabel("Telephone");
			TelephoneLabel.setBounds(28, 211, 90, 33);
			contentPane.add(TelephoneLabel);
			
			textTelephone = new JTextField();
			textTelephone.setBounds(156, 211, 128, 33);
			contentPane.add(textTelephone);
			textTelephone.setColumns(10);
			
			JButton CreerButton = new JButton("CREER");
			CreerButton.addActionListener(new ActionListener() {
				
				
				public  void actionPerformed(  ActionEvent e) {
					try {
						CaissierRequest.getInstance().InsertData(textNom.getText(), textPassword.getText(), textTelephone.getText());
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null," Impossible d'ajouter cet element !",null,JOptionPane.ERROR_MESSAGE);
						
					}
				}
			});
			CreerButton.setBounds(100, 373, 90, 33);
			contentPane.add(CreerButton);
			
			JButton btnNewButton_1 = new JButton("ANNULER");
			btnNewButton_1.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Close_actionPerformed( e);
				}
				
			});
			btnNewButton_1.setBounds(213, 373, 90, 33);
			contentPane.add(btnNewButton_1);
			
		}public void Close_actionPerformed(ActionEvent e){
			this.dispose();
		}

}
