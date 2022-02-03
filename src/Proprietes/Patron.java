package Proprietes;

public class Patron extends User {

	static Patron instance = null;
	private String email;

	private Patron(int id, String nom, String password, String email) {
		super(id, nom, password);
		this.email = email;
	}

	public static Patron getInstance(int id, String nom, String password, String email) {
		if (instance == null)
			instance = new Patron(id, nom, password, email);
		return instance;
	}

	public static Patron getInstance() {
		return instance;
	}

	public String getEmail() {
		return this.email;
	}

	void setEmail(String email) {
		this.email = email;
	}

}
