package my_stock;

public class User {
	static User instance= null;
	protected Integer id;
	protected String nom;
	protected String password;
	
	
	private User(Integer id,String nom, String password) {
		this.id = id;
		this.nom = nom;
		this.password = password;
	}
	
	public static User getInstance(Integer id,String nom, String password) {
		if(instance == null)
				instance = new User(id, nom, password);
		return instance;
	}
	
	Integer getId() {
		return this.id;
	}
	
	String getNom() {
		return this.nom;
	}
	
	String getPassword() {
		return this.password;
	}
	
	
	void setNom(String name) {
		this.nom = name;
	}

}
