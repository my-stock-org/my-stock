package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import Proprietes.Caissier;
import Proprietes.Patron;
import request.CaissierRequest;
import request.ProduitRequest;

public class Accueil extends JFrame  implements Fenetre {
	
    private static Accueil instance = null;
    private Image image;
    private String montant="0";
	private JLabel nom,ref,stck,id,prix,desc,lab;
	private JButton fermer,valider,consulter,rechercher,commande,consultCommande,ajouterPro,updatePro,addCaissier;
	private JTextField NomProduit,Reference,Stock,Prix,Description;

    JPanel panel=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
	// Constructeur de l'objet.
	private Accueil() {
		super();
		this.proprieteFenetre();
		// this.setLayout(null);
		this.proprieteEtiquette();
		this.proprieteButton();

		GridLayout gl = new GridLayout(10, 1, 15, 10);
		panel.setLayout(gl);
		panel2.setLayout(gl);

		this.getContentPane().add(panel, BorderLayout.EAST);
		this.getContentPane().add(panel3, BorderLayout.CENTER);
		this.getContentPane().add(panel2, BorderLayout.WEST);

		background();
		this.setVisible(true);
	}

	public static Accueil getInstance() {
		if (instance == null) {
			instance = new Accueil();
		}
		return instance;
	}

	public void proprieteFenetre() {
		this.setTitle("Accueil");
		this.setSize(950, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
	}

	public void proprieteEtiquette() {
		lab = new JLabel(" ");
	    lab.setPreferredSize(new Dimension(550, 500));
	    Border lineborder = BorderFactory.createLoweredBevelBorder();
	    //associer � JLabel
	    lab.setBorder(lineborder);
	    panel3.add(new JLabel("Effectuer toutes vos transactions avec assurance"));
	    panel3.add(lab);
	    
	    this.getContentPane().setLayout(new BorderLayout());
		panel.add(new JLabel(" "));
	}

	public void proprieteButton() {
		fermer =new JButton("Quitter");
		consulter =new JButton("Consulter les produits");
		valider =new JButton("Valider");
		rechercher =new JButton("Evolution du systeme");
		commande =new JButton("Commander");
		ajouterPro =new JButton("Creer un produit");
		updatePro =new JButton("Consulter les caissiers");
		consultCommande =new JButton("Consulter les commandes");
		addCaissier =new JButton("Ajouter un caissier");
		
		fermer.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		// rechercher.setMargin(new Insets(10,10,10,10));
		// consulter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		// rechercher.setBorder(null);

		this.fermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fermer.setBackground(Color.RED);
					// changer la couleur de text du bouton
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
				} catch (InputMismatchException E) {
					JOptionPane.showMessageDialog(null,
							"ERROR !!! verifier les donnes entres", "Erreur fatale", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		this.ajouterPro.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				Produit.getInstance().openFrame();
			}
		});

		this.valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ProduitRequest.getInstance().InsertData(NomProduit.getText(),
				// Reference.getText(), Stock.getText(), Prix.getText(), Description.getText());
			}
		});

		this.consulter.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				AfficherProduit.getInstance().openFrame();
			}
		});
		
		this.updatePro.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				AfficheCaissier.getInstance().openFrame();
			}
		});
		
		this.addCaissier.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				caissier.getInstance().openFrame();
			}
		});
		this.rechercher.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				CourbeEvolution.getInstance().openFrame();
			}
		});
		this.commande.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateCommande.getInstance().openFrame();
			}
		});
		this.consultCommande.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AfficheCommande.getInstance().openFrame();
			}
		});
		panel.add(consultCommande);
		panel.add(consulter);
		if (Patron.getInstance() != null && Caissier.getInstance() == null) {
			panel.add(updatePro);
		}

		if (Caissier.getInstance() != null) {
			montant=CaissierRequest.getInstance().currentCaissier(Caissier.getInstance().getId());
		}
		lab.setText("Total vendu: "+montant+"FCFA");
		lab.setFont(new Font("Arial", Font.TRUETYPE_FONT, 30).deriveFont(Font.BOLD | Font.ITALIC));
		lab.setBackground(new Color(18, 199, 192));
//		lab.setForeground(Color.BLUE);
		
		panel2.add(new JLabel(" "));
		panel.add(fermer);
		panel2.add(commande);
		if (Patron.getInstance() != null && Caissier.getInstance() == null) {
			panel2.add(ajouterPro);
			panel2.add(addCaissier);
			panel2.add(rechercher);
		}
		
	}

	// fermer
	public void Fermer_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void background() {
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setSize(500, 400);
		pn.setBackground(new Color(198, 199, 192));
		panel3.add(pn);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Fenetre produit = Accueil.getInstance();
		Fenetre welcome = Welcome.getInstance();
	}
}
