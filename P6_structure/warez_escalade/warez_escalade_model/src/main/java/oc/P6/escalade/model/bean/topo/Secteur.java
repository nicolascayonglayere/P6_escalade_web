package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

@Named("secteur")
public class Secteur {

		private int id;
		private String nom;
		private Site site;
		private String descritpion;
		private String image;
		
		public Secteur() {}
		
		public Secteur(String pNom) {
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

		public Site getSite() {
			return site;
		}

		public void setSite(Site site) {
			this.site = site;
		}

		public String getDescritpion() {
			return descritpion;
		}

		public void setDescritpion(String descritpion) {
			this.descritpion = descritpion;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
		
}
