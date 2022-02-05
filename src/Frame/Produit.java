package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import request.BdConnection;
import request.ProduitRequest;


public class Produit extends JFrame  implements Fenetre {
	
    private static Produit instance = null;
    ImageIcon background=new ImageIcon("img/vente.jpg");
	// Constructeur de l'objet.
	private Produit() {
		super();
		this.proprieteFenetre();
//		this.setLayout(null);
		this.proprieteEtiquette();
		this.proprieteButton();
		this.proprieteChamptext();
		
		Image img=background.getImage();
	    Image temp=img.getScaledInstance(400,500,Image.SCALE_SMOOTH);
	    background=new ImageIcon(temp);
	    JLabel back=new JLabel(background);
	    back.setBounds(0,0,this.getWidth(),this.getHeight());
	    this.add(back);
		
		this.setVisible(true);
	}
	static Produit getInstance() 
	{
		if (instance == null) 
			{
				instance = new Produit();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Produits");
		this.setSize(400,320);
		this.setLocationRelativeTo(null);
		this.setResizable(false); //pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null); //position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
	}
	 
	private JLabel nom,ref,stck,prix,desc;
	private JButton annuler,valider;
	private JTextField NomProduit,Reference,Stock,Prix,Description;
	
	public void proprieteChamptext() {
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
	
	public void proprieteEtiquette() {
		nom =new JLabel("Nom");
		ref = new JLabel("Reference");
		stck = new JLabel("Quantite ");
		prix = new JLabel("Prix ");
		desc = new JLabel("Description");
		Font myfont= new Font("Arial",Font.TRUETYPE_FONT,13).deriveFont(Font.ITALIC|Font.BOLD);
		
	    this.nom.setBounds(50, 40, 100, 20);//position et dimension 
	    nom.setFont(myfont);
		this.add(nom);//integre l'etiquette dans le conteneur 
		
		this.ref.setBounds(50, 80, 100, 20);
		ref.setFont(myfont);
		this.add(ref);
		
		this.stck.setBounds(50, 110, 100, 20);
		stck.setFont(myfont);
		this.add(stck);
		
		this.prix.setBounds(50, 140, 100, 20);
		prix.setFont(myfont);
		this.add(prix);
		
		this.desc.setBounds(50, 180, 100, 20);
		desc.setFont(myfont);
		this.add(desc);
		

		nom.setForeground(Color.WHITE);
		ref.setForeground(Color.WHITE);
		stck.setForeground(Color.WHITE);
		prix.setForeground(Color.WHITE);
		desc.setForeground(Color.WHITE);

	}
	public void proprieteButton() {
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

		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				try {
					ProduitRequest.getInstance().InsertData(NomProduit.getText(), Reference.getText(), Stock.getText(), Prix.getText(), Description.getText());
					setVisible(false);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null," Impossible d'ajouter cet element !",null,JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		
	}
	
	public void openFrame() {
		this.setVisible(true);
	}
	
//	fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
}
