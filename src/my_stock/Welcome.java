package my_stock;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Welcome extends JFrame  implements Fenetre, ActionListener {
	private static Welcome instance = null;
	LoginPage login = null;
	

	Container container=getContentPane();
	JLabel welcomeLabel=new JLabel("Bienvenue sur Mystock !");
    JLabel chooseLabel=new JLabel("Veuillez choisir votre type de compte ");
    JButton caissierButton=new JButton("Caissier");
    JButton patronButton=new JButton("Patron");
	 
	 private Welcome() {
			super();
			setLayoutManager();
	        setLocationAndSize();
	        addComponentsToContainer();
	        addActionEvent();
			proprieteFenetre();
		}
	 
	static Welcome getInstance() 
		{
			if (instance == null) 
					instance = new Welcome();
			return instance;
		}
	
	public void proprieteFenetre(){
	    this.setTitle("Bienvenue");
        this.setVisible(true);
        this.setBounds(10,10,370,370);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
 
 @Override
    public void actionPerformed(ActionEvent e) {
	 	this.dispose();
        if (e.getSource() == caissierButton) 
        	 login = LoginPage.getInstance("caissier");
        if (e.getSource() == patronButton) 
        	 login = LoginPage.getInstance("patron");
        
 
    }
 
 public void setLayoutManager()
 {
     container.setLayout(null);
 }

public void setLocationAndSize()
 {
     //Setting location and Size of each components using setBounds() method.
     welcomeLabel.setBounds(110,80,300,30);
     chooseLabel.setBounds(80,150,300,30);
     caissierButton.setBounds(50,200,100,30);
     patronButton.setBounds(200,200,100,30);


 }
 public void addComponentsToContainer()
 {
    //Adding each components to the Container
     container.add(welcomeLabel);
     container.add(chooseLabel);
     container.add(caissierButton);
     container.add(patronButton);
 }
 public void addActionEvent() {
	 caissierButton.addActionListener(this);
	 patronButton.addActionListener(this);
  }
 
}
