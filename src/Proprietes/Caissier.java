package Proprietes;

public class Caissier  {
	static Caissier instance= null;
	private String telephone;
	int id;
	private String name;
	private String password;
	
	private Caissier( String name, String password,String telephone) {
		
		this.telephone = telephone;
		this.name= name;
		this.password=password;
		
	}
	
	private Caissier( ) {
		super();
		
		
	}
	
	public static Caissier getInstance( String name, String password,String telephone) {
		if(instance == null)
				instance = new Caissier( name, password, telephone);
		return instance;
	}
	
	public int getId() {
		return this.id;
		
	}
	
	public String getNom() {
		return this.name;
		
	}
	
	public String getPassword() {
		return this.password;
		
	}
	
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setNom(String name) {
		this.name=name;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}