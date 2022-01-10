package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Proprietes.Produits;


public class Produit extends JFrame  implements Fenetre {
	
    private static Produit instance = null;
	// Constructeur de l'objet.
	private Produit() {
		super();
		this.proprieteFenetre();
		this.setLayout(null);
		this.propEtiquette();
		this.propButon();
		this.propChamptext();
//		connect();
		arrierePlan();
	}
	static Produit getInstance() 
	{
		if (instance == null) 
			{
				instance = new Produit();
			}
		return instance;
	}
	public Produit(Produits prt) {
		try {
			insert(BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection(),prt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Produits");
		this.setSize(400,320);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
		
		this.setVisible(true);
	}
	 
	private JLabel nom,ref,stck,id,prix,desc;
	private JButton annuler,valider;
	private JTextField NomProduit,Reference,Stock,Prix,Description;
	
	public void propChamptext() {
		NomProduit = new JTextField();
		Reference = new JTextField();
		Stock = new JTextField();
		Prix = new JTextField();
		Description = new JTextField();
		
		this.NomProduit.setBounds(160, 40, 200, 20);
		this.add(NomProduit);
		
		this.Reference.setBounds(160, 80, 200, 20);
		this.add(Reference);
		
		this.Stock.setBounds(160, 110, 200, 20);
		this.add(Stock);
		
		this.Prix.setBounds(160, 140, 200, 20);
		this.add(Prix);
		
		this.Description.setBounds(160, 180, 200, 40);
		this.add(Description);
		
		this.NomProduit.setText(" ");
		this.Reference.setText(" ");
		this.Stock.setText(" ");
		this.Prix.setText(" ");
		this.Stock.setText("");
		this.Prix.setText("");
		this.Description.setText(" ");
	}
	
	public void propEtiquette() {
		nom =new JLabel();
		ref = new JLabel();
		stck = new JLabel();
		prix = new JLabel();
		desc = new JLabel();
		id = new JLabel();
		
	    this.nom.setBounds(50, 40, 100, 20);//position et dimension 
		this.nom.setText("Nom");//texte de l'etiquette
		this.add(nom);//integre l'etiquette dans le conteneur 
		
		this.ref.setBounds(50, 80, 100, 20);
	    this.ref.setText("Reference");
		this.add(ref);
		
		this.stck.setBounds(50, 110, 100, 20);
		this.stck.setText("Quantite ");
		this.add(stck);
		
		this.prix.setBounds(50, 140, 100, 20);
		this.prix.setText("Prix");
		this.add(prix);
		
		this.desc.setBounds(50, 180, 100, 20);
		this.desc.setText("Description ");
		this.add(desc);
		
//		nom.setText(" ");
		
	}
	public void propButon() {
		annuler =new JButton("Annuler");
		valider =new JButton("Valider");
		
		this.annuler.setBounds(90, 230, 100, 30);
		this.add(annuler);
		
		this.valider.setBounds(200, 230, 100, 30);
		this.add(valider);
		
		this.annuler.addActionListener(new ActionListener()  {
		   public void actionPerformed(ActionEvent e) {
		     Fermer_actionPerformed(e);
		   }
		 });
//		this.valider.addActionListener(new ActionListener()  {
//		  public void actionPerformed(ActionEvent e) {
//		    System.exit(0);
//		  }
//		});
		
		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				try {
					String nom,reference,price,qnt,description;
					int prix,stock;
					Produits produit;
					Connection conn = null;
					nom = NomProduit.getText();
					reference = Reference.getText();
					qnt = Stock.getText();
					stock=Integer.valueOf(qnt);
					price = Prix.getText();
					prix=Integer.valueOf(price);
					description = Description.getText();
					produit= Produits.getInstance(nom, reference, stock, prix, description);
					Produit prod=new Produit(produit);
					
				}catch(InputMismatchException E) {
					JOptionPane.showMessageDialog(null,
				            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
				}
				}
			
		});
		
	}
	
	public void insert(Connection conn,Produits prt)throws SQLException {
		try
	    {
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO `produit` (`reference` , `nom` , `stock` , `prix` , `description` , `id_patron`)" +
                " values ('"+prt.getReference()+"','"+prt.getNom_Produits()+"',"+prt.getStock()+","+prt.getPrix()+",'"+prt.getDescription()+"',"+prt.getId_Patron()+")";
		
		System.out.println("Success");
//		conn.close();
	    }
	    catch(Exception e){ 
	      System.out.println(e);
	    }
		
	}

	 
//	fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
	public void arrierePlan() {
		JLabel label=new JLabel("");
		label.setOpaque(true);
		label.setBackground(Color.lightGray);
		
		label.setBounds(0, 0, 550, 400);
		this.add(label);
	}
}
