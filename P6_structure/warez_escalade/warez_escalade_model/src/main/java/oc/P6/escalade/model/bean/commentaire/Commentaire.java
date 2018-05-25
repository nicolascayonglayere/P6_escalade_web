package oc.P6.escalade.model.bean.commentaire;

import java.util.Date;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public abstract class Commentaire {

	private int id;
	private Utilisateur auteur;
	private Date date;
	private String vCommentaire;
	
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

	public String getvCommentaire() {
		return vCommentaire;
	}

	public void setvCommentaire(String vCommentaire) {
		this.vCommentaire = vCommentaire;
	}
	
	
}
