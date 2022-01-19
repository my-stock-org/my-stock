package Proprietes;

public class Commandes {
	static Commandes instance = null;
	private int id, clientId, produitId, total;
	private String dateCreation, dateModifie = null, reference;

	private Commandes(String reference, int clientId, int produitId, int total, String date) {
		this.reference = reference;
		this.clientId = clientId;
		this.produitId = produitId;
		this.total = total;
		this.dateCreation = date;
	}

	public static Commandes getInstance(String reference, int clientId, int produitId, int total, String date) {
		if (instance == null)
			instance = new Commandes(reference, clientId, produitId, total, date);
		return instance;
	}

	public int getId() {
		return id;
	}

	public int getClientId() {
		return clientId;
	}

	public int getProduitId() {
		return produitId;
	}

	public int getTotal() {
		return total;
	}

	public String getReference() {
		return reference;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public String getDateModification() {
		if (dateModifie == null)
			dateModifie = dateCreation;
		// setDateModification(dateCreation);
		return dateModifie;

	}

	public void setId(int v) {
		this.id = v;
	}

	public void setDateModification(String v) {
		this.dateModifie = v;
	}

}
