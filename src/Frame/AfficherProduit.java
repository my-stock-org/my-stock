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
import javax.swing.SwingUtilities;

import request.BdConnection;
import request.ProduitRequest;

public class AfficherProduit extends JFrame implements Fenetre {

	private static AfficherProduit instance = null;
	private JButton annuler, valider, supprimer;
	private JTextField stock = new JTextField();
	private Choice nomproduit = new Choice();
	private Choice nomproduit1 = new Choice();
	private JFrame frame = this;
	Connection conn = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "")
			.getConnection();

	JLabel labelHead = new JLabel("Liste des produits");
	JLabel label1 = new JLabel("Modifier la quantiter d'un produit");

	JLabel title;
	JTable tb1 = new JTable();
	JPanel monpanel = new JPanel();
	JPanel monpanel2 = new JPanel();
	JPanel panel3 = new JPanel();

	// Constructeur de l'objet.
	private AfficherProduit() {
		super();
		this.proprieteFenetre();
		// this.setLayout(null);
		this.proprieteButton();

		tb1 = ProduitRequest.getInstance().AfficherProduit(tb1, monpanel);
		tb1.setAutoCreateRowSorter(true);
		tb1.setFillsViewportHeight(true);

		labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20).deriveFont(Font.BOLD | Font.ITALIC));
		label1.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15).deriveFont(Font.BOLD | Font.ITALIC));

		nomproduit = ProduitRequest.getInstance().SelectProduit(nomproduit);
		nomproduit1 = ProduitRequest.getInstance().SelectProduit(nomproduit1);

		monpanel.add(label1);
		monpanel.add(nomproduit1);
		monpanel.add(stock);
		monpanel.add(panel3);
		panel3.add(valider);
		panel3.add(annuler);

		monpanel2.add(new JLabel(" "));
		monpanel2.add(supprimer);
		monpanel2.add(nomproduit);
		monpanel2.add(labelHead);
		monpanel2.add(new JLabel(" "));

		monpanel.setLayout(new GridLayout(2, 2));
		monpanel2.setLayout(new GridLayout(2, 3));

		this.getContentPane().add(monpanel2, BorderLayout.PAGE_START);
		this.getContentPane().add(new JScrollPane(tb1), BorderLayout.CENTER);
		this.getContentPane().add(monpanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	static AfficherProduit getInstance() {
		if (instance == null) {
			instance = new AfficherProduit();
		}
		return instance;
	}

	public void proprieteFenetre() {
		this.setTitle("Produits");
		this.setSize(550, 400);
		this.setResizable(false);// pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);// position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void proprieteButton() {
		annuler = new JButton("Annuler");
		valider = new JButton("Valider");
		supprimer = new JButton("Supprimer");

		this.add(annuler);
		this.add(valider);
		this.add(supprimer);

		this.annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fermer_actionPerformed(e);
			}
		});

		this.supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProduitRequest.getInstance().Delete(nomproduit.getSelectedItem());
				setVisible(false);
				tb1 = ProduitRequest.getInstance().AfficherProduit(tb1, monpanel);
				setVisible(true);
			}
		});
		this.valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProduitRequest.getInstance().AjouterQuantite(nomproduit1.getSelectedItem(),
						Integer.valueOf(stock.getText()));
				setVisible(false);
				tb1 = ProduitRequest.getInstance().AfficherProduit(tb1, monpanel);
				setVisible(true);
			}
		});

	}

	public void openFrame() {
		this.setVisible(true);
	}

	// fermer
	public void Fermer_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
