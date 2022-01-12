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
	public int  getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	//Setters
	public void setNom(String name) {
		this.nom = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
