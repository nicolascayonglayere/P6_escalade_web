package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("topo")
public class Topo {
	
	private String nom;
	private int id;
	private Utilisateur auteur;
	
	public Topo() {}
	
	public Topo(String pNom) {
		this.nom = pNom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

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
	
	

}
