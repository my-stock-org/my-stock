package Proprietes;

public class Caissier extends User {
	static Caissier instance = null;
	private String telephone;

	private Caissier(int id, String nom, String password, String telephone) {
		super(id, nom, password);
		this.telephone = telephone;
	}

	public static Caissier getInstance(int id, String nom, String password, String email) {
		if (instance == null)
			instance = new Caissier(id, nom, password, email);
		return instance;
	}

	public static Caissier getInstance() {
		return instance;
	}

	String getTelephone() {
		return this.telephone;
	}

	void setTelephone(String tel) {
		this.telephone = tel;
	}

}
