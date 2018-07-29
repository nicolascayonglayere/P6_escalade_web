package oc.P6.escalade.model.bean.utilisateur;

import javax.inject.Named;

import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateur;

/**
 * Objet métier représentant les coordonnées d'un {@link Utilisateur} , implémente {@link IntCoordonneeUtilisateur}
 * @author nicolas
 *
 */
@Named ("coordonneeUtilisateur")
public class CoordonneeUtilisateur implements IntCoordonneeUtilisateur {

	private int id;
	private String email;
	private String adresse;
	private Utilisateur utilisateur;
	private int idUtilisateur;
	
	/**
	 * Constructeur sans paramètres
	 */
	public CoordonneeUtilisateur() {}

	/**
	 * Getter et Setter
	 * @return
	 */
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
