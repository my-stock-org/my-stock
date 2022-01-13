package Proprietes;

public class Produits {
	 static Produits instance= null;
	 private String nom, description, reference;
	 private int prix, id_patron, stock, id;
	 private Produits(String nom, String reference, int stock, int prix, String description) {
		 this.nom= nom;
		 this.reference=reference;
		 this.stock=stock;
		 this.prix=prix;
		 this.description=description;
		 this.id_patron=1;
	 }
	 private Produits() {
		 super();
	 }
		
	 public static Produits getInstance(String nom, String reference, int stock, int prix, String description) {
		if(instance == null)
				instance = new Produits(nom, reference, stock, prix, description);
		return instance;
	}
	    //Accesseurs

	 public int getId_Produits() {
	     return this.id;
	 }
	 
	 public int getId_Patron() {
	     return this.id;
	 }

	 public String getNom_Produits() {
	     return this.nom;
	 }

	 public String getReference() {
	     return this.reference;
	 }

	 public int getStock() {
	     return this.stock;
	 }
	 
	 public int getPrix() {
	     return this.prix;
	 }
	 
	 public String getDescription() {
	     return this.description;
	 }
	    //Modificateur
	 
	 public void setId_Patron(int id_patron) {
	     this.id_patron = id_patron;
	 }

	 public void setNom_Produits(String nom) {
	     this.nom = nom;
	 }

	 public void setReference(String reference) {
	     this.reference = reference;
	 }

	 public void setStock(int stock) {
	     this.stock = stock;
	 }
	 
	 public void setPrice(int prix) {
	     this.prix = prix;
	 }
	 
	 public void setDescription(String description) {
	     this.description = description;
	 }
	
	
}
