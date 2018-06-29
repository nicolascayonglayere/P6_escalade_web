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
		private String nomSecteur;
		private Site site;
		private String description;
		private String image;
		
		/**
		 * Constructeurs
		 */
		public Secteur() {}
		
		public Secteur(String pNom) {
			this.nomSecteur = pNom;
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

		@Override
		public String getNomSecteur() {
			return this.nomSecteur;
		}

		@Override
		public void setNomSecteur(String pNom) {
			this.nomSecteur = pNom;
			
		}
		
		
}
