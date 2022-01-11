package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import request.ProduitRequest;

public class Accueil extends JFrame  implements Fenetre {
	
    private static Accueil instance = null;
    private Image image;
	private JLabel nom,ref,stck,id,prix,desc;
	private JButton fermer,valider,consulter,rechercher,commande,ajouterPro,updatePro;
	private JTextField NomProduit,Reference,Stock,Prix,Description;

    JPanel panel=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
	// Constructeur de l'objet.
	private Accueil() {
		super();
		this.proprieteFenetre();
//		this.setLayout(null);
		this.proprieteEtiquette();
		this.proprieteButton();
		
		GridLayout gl = new GridLayout(10, 1, 15, 10);
		panel.setLayout(gl);
		panel2.setLayout(gl);
		
        this.getContentPane().add(panel,BorderLayout.EAST);
		this.getContentPane().add(panel3, BorderLayout.CENTER);
	    this.getContentPane().add(panel2, BorderLayout.WEST);
		
		background();
		this.setVisible(true);
	}
	static Accueil getInstance() 
	{
		if (instance == null) 
			{
				instance = new Accueil();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Accueil");
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);

		
		
		
	}
	
	public void proprieteEtiquette() {
		JLabel label = new JLabel(" ");
	    label.setPreferredSize(new Dimension(600, 650));
	    Border lineborder = BorderFactory.createLoweredBevelBorder();
	    //associer à JLabel
	    label.setBorder(lineborder);
	    panel3.add(new JLabel("Effectuer toutes vos transaction avec assurance"));
	    panel3.add(label);
	    
	    this.getContentPane().setLayout(new BorderLayout());
		
		panel.add(new JLabel(" "));
	}
	public void proprieteButton() {
		fermer =new JButton("Quitter");
		consulter =new JButton("Consulter Produit");
		valider =new JButton("Valider");
		rechercher =new JButton("Rechercher");
		commande =new JButton("Commander");
		ajouterPro =new JButton("Creer un produit");
		updatePro =new JButton("Modifier un produit");
		
		fermer.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
//		rechercher.setMargin(new Insets(10,10,10,10));
//		consulter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//		rechercher.setBorder(null); 
		
		this.fermer.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				try {
					fermer.setBackground(Color.RED);
				    //changer la couleur de text du bouton
				    fermer.setForeground(Color.WHITE);
					int reponse = JOptionPane.showConfirmDialog(null,
				            "voulez-vous vraiment arreter le programe?", "Attention !",
				            JOptionPane.YES_NO_OPTION);
				        switch (reponse) {
				            case JOptionPane.YES_OPTION:
				            	System.exit(FRAMEBITS);
				                break;
				            case JOptionPane.NO_OPTION:
				                // ...
				                break;
				        }
				}catch(InputMismatchException E) {
					JOptionPane.showMessageDialog(null,
				            "ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
				} 
				}
			
		});
		
		this.ajouterPro.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				Produit.getInstance();
			}
		});

		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
//				ProduitRequest.getInstance().InsertData(NomProduit.getText(), Reference.getText(), Stock.getText(), Prix.getText(), Description.getText());
			}
		});
		
		this.consulter.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				AfficherProduit.getInstance();
			}
		});
		panel.add(consulter);
//		panel2.add(new JLabel(" "));
		panel2.add(rechercher);
		panel.add(fermer);
		panel2.add(commande);
		panel2.add(ajouterPro);
		panel2.add(updatePro);
		
	}
	
//	fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
	
	private void background() {
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setSize(500, 400);
		pn.setBackground(new Color(198, 199, 192));
		panel3.add(pn);
	}
}
