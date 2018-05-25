package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

@Named("secteur")
public class Secteur {

		private int id;
		private String nom;
		private Site site;
		
		public Secteur() {}

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
		
		
}
