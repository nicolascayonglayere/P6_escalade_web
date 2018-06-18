package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("topo")
public class Topo {
	
	private String nom;
	private int id;
	private Utilisateur auteur;
	private String image;
	private double longitude;
	private double latitude;
	private String description;
	private int nbreEx;
	private int nombreSite, nombreSecteur, nombreVoie;
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		try {
			this.longitude = Double.parseDouble(longitude);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		} 
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		try {
			this.latitude = Double.parseDouble(latitude);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbreEx() {
		return nbreEx;
	}

	public void setNbreEx(String nbreEx) {
		try {
			this.nbreEx = Integer.parseInt(nbreEx);
		}catch (Exception e){
			System.out.println(this.getClass().toString() + e);
		}
	}

	public int getNombreSite() {
		return nombreSite;
	}

	public void setNombreSite(int nombreSite) {
		this.nombreSite = nombreSite;
	}

	public int getNombreSecteur() {
		return nombreSecteur;
	}

	public void setNombreSecteur(int nombreSecteur) {
		this.nombreSecteur = nombreSecteur;
	}

	public int getNombreVoie() {
		return nombreVoie;
	}

	public void setNombreVoie(int nombreVoie) {
		this.nombreVoie = nombreVoie;
	}
	
	

}
