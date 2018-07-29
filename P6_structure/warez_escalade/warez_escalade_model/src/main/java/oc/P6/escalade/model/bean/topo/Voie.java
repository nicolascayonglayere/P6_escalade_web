package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

import oc.P6.escalade.model.contract.topo.IntVoie;

/**
 * Objet métier représentant une voie implémente {@link IntVoie}
 * @author nicolas
 *
 */
@Named("voie")
public class Voie implements IntVoie{

	private int id;
	private String nomVoie;
	private String cotation;
	private int hauteur;
	private int nbLgueur;
	private int nbPoint;
	private Secteur secteur;
	private String description;
	
	/**
	 * Constructeurs
	 */
	public Voie() {}
	
	public Voie(String pNom) {
		this.nomVoie = pNom;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getNomVoie() {
		return this.nomVoie;
	}

	@Override
	public void setNomVoie(String pNom) {
		this.nomVoie = pNom;
		
	}
	
	
}
