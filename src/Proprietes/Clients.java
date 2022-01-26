package Proprietes;

public class Clients {
	private int id, idPatron;
	private String nom, telephone, addresse;
	static Clients instance=null;
	
	private Clients(String nom, String telephone, String addresse, int idP) {
		this.nom = nom;
		this.telephone = telephone;
		this.addresse = addresse;
		this.id= idP;
		
	}
	public static Clients getInstance(String nom, String telephone,String addresse, int idPatron){
		if(instance == null)
			instance = new Clients(nom, telephone, addresse, idPatron);
		return instance;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getAddresse() {
		return addresse;
	}
	
	public int getPatronId() {
		return idPatron;
	}
	
	public void setNom(String name) {
		this.nom = name;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setAddresse(String addr) {
		this.addresse= addr;
	}
	
	public void setIdPatron(int id) {
		this.idPatron= id;
	}
	

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

