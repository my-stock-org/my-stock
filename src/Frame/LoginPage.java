package Frame;

import Proprietes.Caissier;
import Proprietes.Patron;
import request.UserRequest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


public class LoginPage extends JFrame  implements Fenetre, ActionListener   {

	private String accountType; 
	private String email;
	private String password;
	private String telephone;
	
	private static LoginPage instance = null;
	Patron patron = null;
	Caissier caissier = null;
	UserRequest requestUser = UserRequest.getInstance();
	
	Container container=getContentPane();
	
	JLabel userLabel=new JLabel("Email");
	JLabel telLabel = new JLabel("Téléphone");
    JLabel passwordLabel=new JLabel("Mot de passe ");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("Connexion");
    JButton cancelButton=new JButton("Annuler");
    JCheckBox showPassword=new JCheckBox("voir le mot de passe");
	 
	 private LoginPage(String accountType) {
			super();
			this.accountType = accountType;
			setLayoutManager();
	        setLocationAndSize();
	        addComponentsToContainer();
	        addActionEvent();
			proprieteFenetre();
			
		}
	 
	static LoginPage getInstance(String accountType) 
		{
			if (instance == null) 
					instance = new LoginPage(accountType);
			return instance;
		}
	

	
	 public void proprieteFenetre(){
		    this.setTitle("Connexion");
	        this.setVisible(true);
	        this.setBounds(10,10,370,500);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	 
			
			this.setVisible(true);
		}
	 
	 @Override
	    public void actionPerformed(ActionEvent e) {
		   //Coding Part of LOGIN button
	        if (e.getSource() == loginButton) {
	            email = userTextField.getText();
	            password = passwordField.getText();
	            telephone =userTextField.getText();
	            if(accountType == "patron")
	            	patron = requestUser.loginPatron(email, password);
	            else
	            	caissier = requestUser.loginCaissier(telephone, password);
	        
	 
	        }
	        if (e.getSource() == cancelButton) {
	        	this.dispose();
	            Welcome.getInstance();
	        }
	        if (e.getSource() == showPassword) 
	            if (showPassword.isSelected()) 
	                passwordField.setEchoChar((char) 0);
	            else 
	                passwordField.setEchoChar('*');
	        
	 
	 
	    }
	 
	  public void setLayoutManager()
	   {
	       container.setLayout(null);
	   }
	  
	  public void setLocationAndSize()
	   {
	       //Setting location and Size of each components using setBounds() method.
	       if(accountType == "patron")
	    	   userLabel.setBounds(50,150,100,30);
	       if(accountType == "caissier")
	    	   telLabel.setBounds(50,150,100,30);
	       
	       passwordLabel.setBounds(50,220,100,30);
	       userTextField.setBounds(150,150,150,30);
	       passwordField.setBounds(150,220,150,30);
	       showPassword.setBounds(150,250,150,30);
	       loginButton.setBounds(50,300,100,30);
	       cancelButton.setBounds(200,300,100,30);
	 
	 
	   }
	   public void addComponentsToContainer()
	   {
	      //Adding each components to the Container
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

	@Override
	public void proprieteButton() {
		// TODO Auto-generated method stub
		
	}

}
