package Frame;

import Proprietes.Caissier;
import Proprietes.Caissiers;
import Proprietes.Patron;
import request.UserRequest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class LoginPage extends JFrame implements Fenetre, ActionListener {

	private String accountType;
	private String email;
	private String password;
	private String telephone;

	private static LoginPage instance = null;
	Patron patron = null;
	Caissier caissier = null;
	UserRequest requestUser = UserRequest.getInstance();

	Container container = getContentPane();

	JLabel userLabel = new JLabel("Email");
	JLabel telLabel = new JLabel("Telephone");
	JLabel passwordLabel = new JLabel("Mot de passe ");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("Connexion");
	JButton cancelButton = new JButton("Fermer");
	JCheckBox showPassword = new JCheckBox("voir le mot de passe");
	ImageIcon background = new ImageIcon("img/vente.jpg");
	ImageIcon background2 = new ImageIcon("img/avatar.png");
	ImageIcon background3 = new ImageIcon("img/cle.jpg");
	ImageIcon background4 = new ImageIcon("img/mail.png");

	private LoginPage(String accountType) {
		super();
		this.accountType = accountType;
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		proprieteFenetre();

	}

	static LoginPage getInstance(String accountType) {
		if (instance == null)
			instance = new LoginPage(accountType);
		return instance;
	}

	public void proprieteFenetre() {
		this.setTitle("Connexion");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 420);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		Image img = background.getImage();
		Image temp = img.getScaledInstance(400, 500, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel back = new JLabel(background);
		back.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(back);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Coding Part of LOGIN button
		if (e.getSource() == loginButton) {
			email = userTextField.getText();
			password = passwordField.getText();
			telephone = userTextField.getText();
			if (accountType == "patron") {
				patron = requestUser.loginPatron(email, password);
				if (patron.getInstance()!=null) {
					Accueil.getInstance();
					this.dispose();
				}
			} else {
				caissier = requestUser.loginCaissier(telephone, password);
				if (caissier.getInstance()!=null) {
					Accueil.getInstance();
					this.dispose();
				}
			}
		}
		
		if (e.getSource() == cancelButton) {
			this.dispose();
			Welcome.getInstance().openFrame();
		}
		if (e.getSource() == showPassword)
			if (showPassword.isSelected())
				passwordField.setEchoChar((char) 0);
			else
				passwordField.setEchoChar('*');

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
		if (accountType == "patron")
			userLabel.setBounds(50, 150, 100, 30);
		if (accountType == "caissier")
			telLabel.setBounds(50, 150, 100, 30);

		Image img = background2.getImage();
		Image temp = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		background2 = new ImageIcon(temp);
		JLabel back = new JLabel(background2);
		back.setBounds(100, 2, 150, 150);
		this.add(back);

		img = background4.getImage();
		temp = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		background4 = new ImageIcon(temp);
		back = new JLabel(background4);
		back.setBounds(151, 140, 320, 50);
		this.add(back);

		img = background3.getImage();
		temp = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		background3 = new ImageIcon(temp);
		back = new JLabel(background3);
		back.setBounds(151, 210, 320, 50);
		this.add(back);

		telLabel.setForeground(Color.WHITE);
		userLabel.setForeground(Color.WHITE);
		passwordLabel.setForeground(Color.WHITE);

		passwordLabel.setBounds(50, 220, 100, 30);
		userTextField.setBounds(150, 150, 150, 30);
		passwordField.setBounds(150, 220, 150, 30);
		showPassword.setBounds(150, 250, 150, 30);
		loginButton.setBounds(50, 300, 100, 30);
		cancelButton.setBounds(200, 300, 100, 30);

	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(userLabel);
		container.add(telLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(showPassword);
		container.add(loginButton);
		container.add(cancelButton);
	}

	public void addActionEvent() {
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		showPassword.addActionListener(this);
	}

	public void openFrame() {
		this.setVisible(true);
	}
}
