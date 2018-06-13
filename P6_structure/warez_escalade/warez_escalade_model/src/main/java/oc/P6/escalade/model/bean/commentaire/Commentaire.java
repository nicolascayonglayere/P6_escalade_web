package oc.P6.escalade.model.bean.commentaire;

import java.util.Date;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public abstract class Commentaire {

	private int id;
	private Utilisateur auteur;
	private Date date;
	private String message;
	
	public Commentaire() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	
}
