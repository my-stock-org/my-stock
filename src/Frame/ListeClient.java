package Frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;

import request.ClientRequest;

public class ListeClient extends JFrame implements Fenetre {

	private static ListeClient instance = null;
	private JButton supprimer;
	private Choice nomClient = new Choice();
	JLabel labelHead = new JLabel("Liste des clients");

	JLabel title;
	JTable tb1 = new JTable();
	JPanel monpanel = new JPanel();
	JPanel monpanel2 = new JPanel();
	JPanel panel3 = new JPanel();

	// Constructeur de l'objet.
	private ListeClient() {
		super();
		this.proprieteFenetre();
		// this.setLayout(null);
		this.proprieteButton();

		tb1 = ClientRequest.getInstance().ListClient(tb1, monpanel);
		tb1.setAutoCreateRowSorter(true);
		tb1.setFillsViewportHeight(true);

		labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20).deriveFont(Font.BOLD | Font.ITALIC));
		nomClient = ClientRequest.getInstance().SelectClient(nomClient);

		monpanel.add(panel3);

		monpanel2.add(new JLabel(" "));
		monpanel2.add(supprimer);
		monpanel2.add(nomClient);
		monpanel2.add(labelHead);
		monpanel2.add(new JLabel(" "));

		monpanel.setLayout(new GridLayout(2, 2));
		monpanel2.setLayout(new GridLayout(2, 3));

		this.getContentPane().add(monpanel2, BorderLayout.PAGE_START);
		this.getContentPane().add(new JScrollPane(tb1), BorderLayout.CENTER);
		this.getContentPane().add(monpanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	static ListeClient getInstance() {
		if (instance == null)
			instance = new ListeClient();
		return instance;
	}

	public void proprieteFenetre() {
		this.setTitle("Liste des Clients");
		this.setSize(550, 400);
		// this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);// position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void proprieteButton() {
		supprimer = new JButton("Supprimer");
		this.add(supprimer);

		this.supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientRequest.getInstance().DeleteClient(nomClient.getSelectedItem());
			}
		});

	}

	// fermer
	public void Fermer_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
