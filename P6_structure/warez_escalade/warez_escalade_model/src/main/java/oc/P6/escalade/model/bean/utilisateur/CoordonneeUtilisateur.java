package oc.P6.escalade.model.bean.utilisateur;

import javax.inject.Named;

@Named ("coordonneeUtilisateur")
public class CoordonneeUtilisateur {

	private int id;
	private String email;
	private String adresse;
	private Utilisateur utilisateur;
	private int idUtilisateur;
	
	public CoordonneeUtilisateur() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	
}
