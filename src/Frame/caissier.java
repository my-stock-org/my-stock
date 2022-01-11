package my_stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class caissier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					caissier frame = new caissier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public caissier() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
