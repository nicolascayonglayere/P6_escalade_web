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
	private String description;
	
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

	public void setHauteur(String hauteur) {
		try {
			this.hauteur = Integer.parseInt(hauteur);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		}
	}

	public int getNbLgueur() {
		return nbLgueur;
	}

	public void setNbLgueur(String nbLgueur) {
		try {
			this.nbLgueur = Integer.parseInt(nbLgueur);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		}
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public void setNbPoint(String nbPoint) {
		try {
			this.nbPoint = Integer.parseInt(nbPoint);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		};
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
