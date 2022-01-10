package Proprietes;

public class Caissiers extends User {
	static Caissiers instance= null;
	private String telephone;
	
	
private Caissiers(int id, String nom, String password,String telephone) {
		super(id, nom, password);
		this.telephone = telephone;
	}
	
	public static Caissiers getInstance(int id, String nom, String password,String email) {
		if(instance == null)
				instance = new Caissiers(id, nom,  password,email);
		return instance;
	}
	
	String getTelephone() {
		return this.telephone;
	}
	
	void setTelephone(String tel) {
		this.telephone = tel;
	}

}
