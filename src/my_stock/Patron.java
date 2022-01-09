package my_stock;

public class Patron {
	static Patron instance= null;
	private Integer id;
	private String nom;
	private String email;
	private String password;
	
	
	private Patron(String nom,String email, String password) {
		this.nom = nom;
		this.email = email;
		this.password = password;
	}
	
	public static Patron getInstance(String nom,String email, String password) {
		if(instance == null)
				instance = new Patron(nom, email, password);
		return instance;
	}
	
	Integer getId() {
		return this.id;
	}
	
	String getNom() {
		return this.nom;
	}
	
	String getEmail() {
		return this.email;
	}
	
	String getPassword() {
		return this.password;
	}
	
	
	void setNom(String name) {
		this.nom = name;
	}
	
	void setEmail(String email) {
		this.email = email;
	}

}
