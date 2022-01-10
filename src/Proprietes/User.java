package Proprietes;

public class User {
	protected int id;
	protected String nom;
	protected String password;
	
	
	 User(int id,String nom, String password) {
		this.id = id;
		this.nom = nom;
		this.password = password;
	}
	
	
	//Getters
	int getId() {
		return this.id;
	}
	
	String getNom() {
		return this.nom;
	}
	
	String getPassword() {
		return this.password;
	}
	
	
	
	//Setters
	void setNom(String name) {
		this.nom = name;
	}
	
	void setPassword(String password) {
		this.password = password;
	}

}
