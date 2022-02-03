
package Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import request.BdConnection;


public class Welcome extends JFrame  implements Fenetre, ActionListener {
	private static Welcome instance = null;
	 Connection connection = BdConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mystock", "root", "").getConnection();
	

	Container container=getContentPane();
	JLabel welcomeLabel=new JLabel("Bienvenue sur Mystock !");
    JLabel chooseLabel=new JLabel("Veuillez choisir votre type de compte ");
    JButton caissierButton=new JButton("Caissier");
    JButton patronButton=new JButton("Patron");
    ImageIcon background=new ImageIcon("img/welcome1.jpg");
	 
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
//        this.setVisible(true);
        this.setBounds(10,10,370,370);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        

        Image img=background.getImage();
   	    Image temp=img.getScaledInstance(380,380,Image.SCALE_SMOOTH);
   	    background=new ImageIcon(temp);
   	    JLabel back=new JLabel(background);
   	    back.setBounds(0,0,this.getWidth(),this.getHeight());
   	    this.add(back);
		
		this.setVisible(true);
	}
 
 @Override
    public void actionPerformed(ActionEvent e) {
	 this.dispose();
        if (e.getSource() == caissierButton) 
        	LoginPage.getInstance("caissier");
        if (e.getSource() == patronButton) 
        	LoginPage.getInstance("patron");
        
        	
        
 
    }
 
 public void setLayoutManager()
 {
     container.setLayout(null);
     
 }

public void setLocationAndSize()
 {
     //Setting location and Size of each components using setBounds() method.
     welcomeLabel.setBounds(10,65,300,30);
     chooseLabel.setBounds(30,120,300,30);
     caissierButton.setBounds(50,200,100,30);
     patronButton.setBounds(200,200,100,30);

    

 }
 public void addComponentsToContainer()
 {
    //Adding each components to the Container
	 welcomeLabel.setForeground(Color.WHITE);
	 chooseLabel.setForeground(Color.WHITE);
	 welcomeLabel.setFont(new Font("Arial",Font.TRUETYPE_FONT,24).deriveFont(Font.BOLD|Font.ITALIC));
	 chooseLabel.setFont(new Font("Arial",Font.TRUETYPE_FONT,15).deriveFont(Font.BOLD|Font.ITALIC));
     container.add(welcomeLabel);
     container.add(chooseLabel);
     container.add(caissierButton);
     container.add(patronButton);
     
     
 }
 public void addActionEvent() {
	 caissierButton.addActionListener(this);
	 patronButton.addActionListener(this);
  }

public void proprieteButton() {
	// TODO Auto-generated method stub
	
}
 
}
