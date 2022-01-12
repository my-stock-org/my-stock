package Proprietes;

public class Clients {
	private int id;
	private String nom, telephone;
	static Clients instance=null;
	
	private Clients(String nom, String telephone) {
		this.nom = nom;
		this.telephone = telephone;
		
	}
	static Clients getInstance(String nom, String telephone){
		if(instance == null)
			instance = new Clients(nom, telephone);
		return instance;
	}
	
	

}
