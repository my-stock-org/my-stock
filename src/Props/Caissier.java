package Props;

public class Caissier {

    private String nom;
    private String password;
    private int montant_vendu_en_caisse;
    private int telephone;
    private int id_patron;
    //Accesseurs
    
    public String getNom() {
        return this.nom;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public int getMontant_vendu_en_caisse() {
        return this.montant_vendu_en_caisse;
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
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setMontant_vendu_en_caisse(int montant_vendu_en_caisse) {
    	this.montant_vendu_en_caisse = montant_vendu_en_caisse;
    }
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    
    
}
