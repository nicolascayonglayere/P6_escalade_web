package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

import oc.P6.escalade.model.contract.topo.IntSecteur;
/**
 * Objet métier représentant le secteur d'un topo
 * @author nicolas
 *
 */
@Named("secteur")
public class Secteur implements IntSecteur {

		private int id;
		private String nom;
		private Site site;
		private String description;
		private String image;
		
		/**
		 * Constructeurs
		 */
		public Secteur() {}
		
		public Secteur(String pNom) {
			this.nom = pNom;
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
		
}
