package Frame;

import javax.swing.JFrame;

public class Patron extends JFrame  implements Fenetre {
	private static Patron instance = null;
	// Constructeur de l'objet.
	private Patron() {
		super();
		proprieteFenetre();
	}
	static Patron getInstance() 
	{
		if (instance == null) 
			{
				instance = new Patron();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Le Patron du ciecle");
		this.setSize(485,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
		
		this.setVisible(true);
	}
	public void proprieteEtiquette(){
		
	}
	public void proprieteButton(){
		
	}
}
public class Patron {

}
