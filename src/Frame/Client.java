package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Proprietes.Patron;
import request.ClientRequest;

public class Client extends JFrame  implements Fenetre {
	
    private static Client instance = null;
    Patron patron ;
    ImageIcon background=new ImageIcon("img/vente.jpg");
	// Constructeur de l'objet.
	private Client() {
		super();
		this.proprieteFenetre();
//		this.setLayout(null);
		this.proprieteEtiquette();
		this.proprieteButton();
		this.proprieteChamptext();
		
		Image img=background.getImage();
	    Image temp=img.getScaledInstance(400,500,Image.SCALE_SMOOTH);
	    background=new ImageIcon(temp);
	    JLabel back=new JLabel(background);
	    back.setBounds(0,0,this.getWidth(),this.getHeight());
	    this.add(back);
		
		this.setVisible(true);
	}
	static Client getInstance() 
	{
		if (instance == null) 
				instance = new Client();
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Création client");
		this.setSize(400,320);
		this.setLocationRelativeTo(null);
		this.setResizable(false); //pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null); //position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);	
	}
	 
	private JLabel 
	nom=new JLabel("Nom"),
	tel= new JLabel("Téléphone"),
	addr= new JLabel("Adresse ");
	
	private JButton 
	annuler=new JButton("Annuler"),
	valider =new JButton("Valider");
	
	private JTextField 
	nomClient=new JTextField(),
	telephoneClient= new JTextField(),
	addresseClient=new JTextField();
	
	public void proprieteChamptext() {
		
		this.nomClient.setBounds(160, 40, 200, 20);
		this.add(nomClient);
		
		this.telephoneClient.setBounds(160, 80, 200, 20);
		this.add(telephoneClient);
		
		this.addresseClient.setBounds(160, 110, 200, 20);
		this.add(addresseClient);
		
		this.resetField();
		
	}
	
	public void proprieteEtiquette() {
		Font myfont= new Font("Arial",Font.TRUETYPE_FONT,13).deriveFont(Font.ITALIC|Font.BOLD);
		
	    this.nom.setBounds(50, 40, 100, 20);//position et dimension 
	    nom.setFont(myfont);
		this.add(nom);
		
		this.tel.setBounds(50, 80, 100, 20);
		tel.setFont(myfont);
		this.add(tel);
		
		this.addr.setBounds(50, 110, 100, 20);
		addr.setFont(myfont);
		this.add(addr);
		

		nom.setForeground(Color.WHITE);
		tel.setForeground(Color.WHITE);
		addr.setForeground(Color.WHITE);

	}
	public void proprieteButton() {
		
		
		this.valider.setBounds(90, 230, 100, 30);
		this.add(valider);
		
		this.annuler.setBounds(200, 230, 100, 30);
		this.add(annuler);
		
		this.annuler.addActionListener(new ActionListener()  {
		   public void actionPerformed(ActionEvent e) {
		     Fermer_actionPerformed(e);
		   }
		 });

		this.valider.addActionListener(new ActionListener() {
			@Override
			public  void actionPerformed(  ActionEvent e) {
				try {
					ClientRequest.getInstance().createClient(nomClient.getText(),addresseClient.getText(),telephoneClient.getText(),1);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"Impossible d'ajouter cet element !",null,JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		
	}
	
//fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
	
	public void resetField(){
		this.nomClient.setText(" ");
		this.telephoneClient.setText(" ");
		this.addresseClient.setText(" ");
	}
}
