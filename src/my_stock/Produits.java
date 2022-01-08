package my_stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import prdt.Connectage;
//import prdt.Produit_crud;
//import prdt.Vente;

public class Produits extends JFrame  implements Fenetre{
	
    private static Produits instance = null;
	// Constructeur de l'objet.
	private Produits() {
		super();
		proprieteFenetre();
	}
	static Produits getInstance() 
	{
		if (instance == null) 
			{
				instance = new Produits();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Produits");
		this.setSize(485,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Produits prd = new Produits();
//		prd.setVisible(true);

	}
}
