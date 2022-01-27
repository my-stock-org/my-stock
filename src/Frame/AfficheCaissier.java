package Frame;


import java.awt.BorderLayout;
import java.awt.Button;
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
import request.CaissierRequest;
public class AfficheCaissier extends JFrame  implements Fenetre {

	private static AfficheCaissier instance = null;
	private JButton annuler,valider,supprimer;
	//private JTextField stock = new JTextField();
	private JTextField caisse = new JTextField();
	private Choice nomCaissier=new Choice();
	private Choice nomCaissier1 =new Choice();
	Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	
	JLabel labelHead = new JLabel("Liste des Caissiers");
    JLabel label1 = new JLabel("Modifier la caisse d'un caissier");
    
    JLabel title;
	JTable tb1 =new JTable();
    JPanel monpanel=new JPanel();
    JPanel monpanel2=new JPanel();
    JPanel panel3=new JPanel();
    
 // Constructeur de l'objet.
 	private AfficheCaissier() {
 		super();
 		this.proprieteFenetre();
// 		this.setLayout(null);
 		this.proprieteButton();
 		
 		tb1 = CaissierRequest.getInstance().AfficheCaissier(tb1,monpanel);
 		tb1.setAutoCreateRowSorter(true);
 		tb1.setFillsViewportHeight(true);
 		
 		labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20).deriveFont(Font.BOLD|Font.ITALIC));
	    label1.setFont(new Font("Arial",Font.TRUETYPE_FONT,15).deriveFont(Font.BOLD|Font.ITALIC));
	    
	    nomCaissier = CaissierRequest.getInstance().SelectCaissier(nomCaissier);
	    nomCaissier1 = CaissierRequest.getInstance().SelectCaissier(nomCaissier1);

		monpanel.add(panel3);
		panel3.add(annuler);

		monpanel2.add(new JLabel(" "));
		monpanel2.add(supprimer);
		monpanel2.add(nomCaissier);
		monpanel2.add(labelHead);
		monpanel2.add(new JLabel(" "));
		

		monpanel.setLayout(new GridLayout(2, 2));
		monpanel2.setLayout(new GridLayout(2, 3));
		
        this.getContentPane().add(monpanel2,BorderLayout.PAGE_START);
		this.getContentPane().add(new JScrollPane(tb1), BorderLayout.CENTER);
	    this.getContentPane().add(monpanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
 	static AfficheCaissier getInstance() 
	{
		if (instance == null) 
			{
				instance = new AfficheCaissier();
			}
		return instance;
	}
 	 public void proprieteFenetre(){
 		this.setTitle("caissiers");
 		this.setSize(550,400);
// 		this.setResizable(false);//pouvoir ou non redefinir la fenetre
 		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
 		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
 	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		
 	}
 	public void proprieteButton() {
		annuler =new JButton("Annuler");
		valider =new JButton("Valider");
		supprimer =new JButton("Supprimer");
		
		this.annuler.addActionListener(new ActionListener()  {
		   public void actionPerformed(ActionEvent e) {
		     Fermer_actionPerformed(e);
		   }
		 });

		this.supprimer.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				CaissierRequest.getInstance().Delete(nomCaissier.getSelectedItem());
			}
		});
		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
			CaissierRequest.getInstance().AjouterMontant(nomCaissier1.getSelectedItem(),Integer.valueOf(caisse.getText()));
			}
		});
		
	}
 	public void openFrame() {
		this.setVisible(true);
	}
 	
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
    
}
