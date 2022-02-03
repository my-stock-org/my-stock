package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Caissiers extends  JFrame  implements Fenetre {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	 private static Caissiers instance = null;
		// Constructeur de l'objet.
		private Caissiers() {
			super();
			proprieteFenetre();
		}
		static Caissiers getInstance() 
		{
			if (instance == null) 
					instance = new Caissiers();
			return instance;
		}
	    
		 public void proprieteFenetre(){
//				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 450, 443);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
			this.setVisible(true);
			
			JLabel NomLabel = new JLabel("nom");
			NomLabel.setBounds(28, 29, 90, 33);
			contentPane.add(NomLabel);
			
			textField = new JTextField();
			textField.setBounds(156, 30, 128, 32);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel PassLabel = new JLabel("Passwordl");
			PassLabel.setBounds(28, 117, 90, 33);
			contentPane.add(PassLabel);
			
			textField_1 = new JTextField();
			textField_1.setBounds(156, 117, 128, 33);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel TotalLabel = new JLabel("total des ventes");
			TotalLabel.setBounds(28, 211, 90, 33);
			contentPane.add(TotalLabel);
			
			textField_2 = new JTextField();
			textField_2.setBounds(156, 211, 128, 33);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
			
			JLabel TelephoneLabel = new JLabel("telephone");
			TelephoneLabel.setBounds(28, 294, 90, 33);
			contentPane.add(TelephoneLabel);
			
			textField_3 = new JTextField();
			textField_3.setBounds(156, 301, 128, 33);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
			
			JButton CreerButton = new JButton("CREER");
			CreerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			CreerButton.setBounds(100, 373, 90, 33);
			contentPane.add(CreerButton);
			
			JButton btnNewButton_1 = new JButton("ANNULER");
			btnNewButton_1.setBounds(213, 373, 90, 33);
			contentPane.add(btnNewButton_1);
		}

}
