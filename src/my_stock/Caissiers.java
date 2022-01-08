package my_stock;

import javax.swing.JFrame;

public class Caissiers extends  JFrame  implements Fenetre {
	
	 private static Caissiers instance = null;
		// Constructeur de l'objet.
		private Caissiers() {
			super();
			proprieteFenetre();
		}
		static Caissiers getInstance() 
		{
			if (instance == null) 
				{
					instance = new Caissiers();
				}
			return instance;
		}
	    
		 public void proprieteFenetre(){
			this.setTitle("Caissiers");
			this.setSize(485,600);
			this.setLocationRelativeTo(null);
			this.setResizable(false);//pouvoir ou non redefinir la fenetre
			this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
			this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
			
			this.setVisible(true);
		}

}
