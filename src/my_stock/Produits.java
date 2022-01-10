package my_stock;

import javax.swing.JFrame;

public class Produits extends  JFrame  implements Fenetre {
	
	 private static Produits instance = null;
		// Constructeur de l'objet.
		private Produits() {
			super();
			proprieteFenetre();
		}
		static Produits getInstance() 
		{
			if (instance == null) 
				{
					instance = new Produits();
				}
			return instance;
		}
	    
		 public void proprieteFenetre(){
			this.setTitle("Produits");
			this.setSize(485,600);
			this.setLocationRelativeTo(null);
			this.setResizable(false);//pouvoir ou non redefinir la fenetre
			this.setLocationRelativeTo(null);//position de la fenetre a l'ecran
			this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
			
			this.setVisible(true);
		}

}
