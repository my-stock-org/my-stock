package Proprietes;

public class Clients {
	private String nom;
    private String addresse;
    private int telephone;
    private int id_patron;
    //Accesseurs
    
    public String getNom() {
        return this.nom;
    }
    
    public String getAddresse() {
        return this.addresse;
    }
    
   
    public int getTelephone() {
        return this.telephone;
    }
    
    public int getId_patron() {
        return this.id_patron;
    }
    //Modificateur

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setAddresse (String addresse) {
        this.addresse = addresse;
    }
    
    
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}

