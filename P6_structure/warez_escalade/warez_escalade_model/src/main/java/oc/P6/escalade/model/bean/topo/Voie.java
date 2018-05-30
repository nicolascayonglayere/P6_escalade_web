package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

@Named("voie")
public class Voie {

	private int id;
	private String nom;
	private String cotation;
	private int hauteur;
	private int nbLgueur;
	private int nbPoint;
	private Secteur secteur;
	
	public Voie() {}
	
	public Voie(String pNom) {
		this.nom = pNom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getNbLgueur() {
		return nbLgueur;
	}

	public void setNbLgueur(int nbLgueur) {
		this.nbLgueur = nbLgueur;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public void setNbPoint(int nbPoint) {
		this.nbPoint = nbPoint;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
	
}
