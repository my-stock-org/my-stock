package my_stock;

//Importing all necessary Packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPage extends JFrame  implements Fenetre, ActionListener   {
	private String email;
	private String password;
	private static LoginPage instance = null;
	Patron patron = null;
	BdConnection connection = BdConnection.getInstance();
	
	Container container=getContentPane();
	
	JLabel userLabel=new JLabel("Email");
    JLabel passwordLabel=new JLabel("Mot de passe ");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("Connexion");
    JButton resetButton=new JButton("Reset");
    JCheckBox showPassword=new JCheckBox("voir le mot de passe");
	 
	 private LoginPage() {
			super();
			setLayoutManager();
	        setLocationAndSize();
	        addComponentsToContainer();
	        addActionEvent();
			proprieteFenetre();
		}
	 
	static LoginPage getInstance() 
		{
			if (instance == null) 
					instance = new LoginPage();
			return instance;
		}
	
	String getEmail() {
		return this.email;
	}
	
	String getPassword() {
		return this.password;
	}
	void setEmail(String email) {
		this.email = email;
	}
	
	void setPassword(String password) {
		this.password = password;
	}
	
	 public void proprieteFenetre(){
		    this.setTitle("Connexion");
	        this.setVisible(true);
	        this.setBounds(10,10,370,600);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setResizable(false);
	 
			
			this.setVisible(true);
		}
	 
	 @Override
	    public void actionPerformed(ActionEvent e) {
		   //Coding Part of LOGIN button
	        if (e.getSource() == loginButton) {
	            email = userTextField.getText();
	            password = passwordField.getText();
	            
	            PreparedStatement st;
				try {
					st = (PreparedStatement) connection.getConnection().prepareStatement("Select * from patron where email=? and password=?");
					 st.setString(1, email);
		                st.setString(2, password);
		                ResultSet result = st.executeQuery();
		                
		                if(result.next()) {
		                	String nom = result.getString("nom");
		                	System.out.println(nom);
		                }
		                else {
		                	JOptionPane.showMessageDialog(this, "Email ou mot de passe invalide");
		                }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	           
                
//	            if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
//	                JOptionPane.showMessageDialog(this, "Login Successful");
//	            } else {
//	                JOptionPane.showMessageDialog(this, "Email ou mot de passe invalide");
//	            }
	 
	        }
	        //Coding Part of RESET button
	        if (e.getSource() == resetButton) {
	            userTextField.setText("");
	            passwordField.setText("");
	        }
	       //Coding Part of showPassword JCheckBox
	        if (e.getSource() == showPassword) {
	            if (showPassword.isSelected()) {
	                passwordField.setEchoChar((char) 0);
	            } else {
	                passwordField.setEchoChar('*');
	            }
	        }
	 
	 
	    }
	 
	  public void setLayoutManager()
	   {
	       //Setting layout manager of Container to null
	       container.setLayout(null);
	   }
	  
	  public void setLocationAndSize()
	   {
	       //Setting location and Size of each components using setBounds() method.
	       userLabel.setBounds(50,150,100,30);
	       passwordLabel.setBounds(50,220,100,30);
	       userTextField.setBounds(150,150,150,30);
	       passwordField.setBounds(150,220,150,30);
	       showPassword.setBounds(150,250,150,30);
	       loginButton.setBounds(50,300,100,30);
	       resetButton.setBounds(200,300,100,30);
	 
	 
	   }
	   public void addComponentsToContainer()
	   {
	      //Adding each components to the Container
	       container.add(userLabel);
	       container.add(passwordLabel);
	       container.add(userTextField);
	       container.add(passwordField);
	       container.add(showPassword);
	       container.add(loginButton);
	       container.add(resetButton);
	   }
	   public void addActionEvent() {
	        loginButton.addActionListener(this);
	        resetButton.addActionListener(this);
	        showPassword.addActionListener(this);
	    }

}
