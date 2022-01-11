package Frame;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import request.BdConnection;
import request.ProduitRequest;

public class AfficherProduit extends JFrame  implements Fenetre {
	
    private static AfficherProduit instance = null;
	private JButton annuler,valider;
	private Choice nomproduit =new Choice();;
	Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	
	JLabel title;
	JTable tb1 =new JTable();
    JPanel monpanel=new JPanel();
    JPanel monpanel2=new JPanel();
	// Constructeur de l'objet.
	private AfficherProduit() {
		super();
		this.proprieteFenetre();
//		this.setLayout(null);
		this.proprieteButton();
		tb1 = ProduitRequest.getInstance().AfficherProduit(tb1,monpanel);
		tb1.setAutoCreateRowSorter(true);
		tb1.setFillsViewportHeight(true);
		
		JLabel labelHead = new JLabel("Liste des produits");
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
	    

	    nomproduit = ProduitRequest.getInstance().choixMatiere(nomproduit);
//	    this.nomproduit.setBounds(270, 245, 100, 20);
//		this.add(nomproduit);
//		monpanel2.add(nomproduit, BorderLayout.EAST);
		

        this.getContentPane().add(labelHead,BorderLayout.PAGE_START);
//	    this.getContentPane().add(monpanel, BorderLayout.NORTH);
		
		this.getContentPane().add(new JScrollPane(tb1), BorderLayout.CENTER);
	    this.getContentPane().add(monpanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	static AfficherProduit getInstance() 
	{
		if (instance == null) 
			{
				instance = new AfficherProduit();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Produits");
		this.setSize(550,400);
//		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void proprieteButton() {
		annuler =new JButton("Annuler");
		valider =new JButton("Valider");
		
		this.annuler.setBounds(90, 330, 100, 30);
		this.add(annuler);
		
		this.valider.setBounds(200, 330, 100, 30);
		this.add(valider);
		
		this.annuler.addActionListener(new ActionListener()  {
		   public void actionPerformed(ActionEvent e) {
		     Fermer_actionPerformed(e);
		   }
		 });

		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				
			}
		});
		monpanel.add(annuler);
		monpanel.add(valider);
		
	}
	
//	fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
}
