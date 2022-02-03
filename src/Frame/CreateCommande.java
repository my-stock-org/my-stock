package Frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import request.ClientRequest;
import request.CommandeRequest;
import request.ProduitRequest;

public class CreateCommande extends JFrame implements Fenetre, ActionListener {

	private static CreateCommande instance = null;
	CommandeRequest requestCmd = CommandeRequest.getInstance();
	ClientRequest requestClient = ClientRequest.getInstance();
	ProduitRequest requestProduit = ProduitRequest.getInstance();

	Container container = getContentPane();
	private Choice nomClient = new Choice();
	private Choice nomProduit = new Choice();

	JLabel clientLabel = new JLabel("Client");
	JLabel productLabel = new JLabel("Produit");
	JLabel qteLabel = new JLabel("Quantité ");
	JLabel refLabel = new JLabel("Reférence ");
	JTextField refField = new JTextField();
	JTextField qteField = new JTextField();
	JButton validateBtn = new JButton("Valider");
	JButton cancelBtn = new JButton("Annuler");
	JButton clientBtn = new JButton("Nouveau Client");

	private CreateCommande() {

		nomClient = ClientRequest.getInstance().SelectClient(nomClient);
		nomProduit = ProduitRequest.getInstance().SelectProduit(nomProduit);

		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		proprieteFenetre();

	}

	static CreateCommande getInstance() {
		if (instance == null)
			instance = new CreateCommande();
		return instance;
	}

	public void proprieteFenetre() {
		this.setTitle("Creation commande");
		this.setVisible(true);
		this.setBounds(10, 10, 650, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelBtn) {
			this.dispose();
		}
		if (e.getSource() == validateBtn) {
			// System.out.println(nomClient.getSelectedItem() + "," +
			// nomProduit.getSelectedItem());
			System.out.println(requestProduit.getProducId(nomProduit.getSelectedItem()));
			CommandeRequest.getInstance().createCommande(refField.getText(), nomClient.getSelectedItem(),
					nomProduit.getSelectedItem(),
					qteField.getText());
		}
		if (e.getSource() == clientBtn) {
			this.dispose();
			Client.getInstance();
		}

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		refLabel.setBounds(50, 40, 100, 30);
		refField.setBounds(160, 40, 300, 30);
		clientLabel.setBounds(50, 80, 100, 30);
		nomClient.setBounds(160, 80, 300, 30);
		clientBtn.setBounds(470, 80, 150, 30);
		productLabel.setBounds(50, 120, 100, 30);
		nomProduit.setBounds(160, 120, 300, 30);
		qteLabel.setBounds(50, 160, 100, 30);
		qteField.setBounds(160, 160, 300, 30);
		validateBtn.setBounds(50, 250, 100, 30);
		cancelBtn.setBounds(300, 250, 100, 30);

	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(clientLabel);
		container.add(productLabel);
		container.add(qteLabel);
		container.add(qteField);
		container.add(validateBtn);
		container.add(cancelBtn);
		container.add(nomClient);
		container.add(nomProduit);
		container.add(clientBtn);
		container.add(refLabel);
		container.add(refField);
	}

	public void addActionEvent() {
		validateBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		clientBtn.addActionListener(this);
	}

	public void proprieteButton() {
		// TODO Auto-generated method stub

	}
}
