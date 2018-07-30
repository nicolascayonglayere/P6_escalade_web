package oc.P6.escalade.model.bean.topo;

import java.util.ArrayList;

import javax.inject.Named;

import oc.P6.escalade.model.contract.topo.IntSecteur;
/**
 * Objet métier représentant le secteur implémente {@link IntSecteur}
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
		private ArrayList<Voie> listVoie;
		
		/**
		 * Constructeurs
		 */
		public Secteur() {}
		
		public Secteur(String pNom) {
			this.nomSecteur = pNom;
		}

		//--Getter et Setter--//
		@Override
		public int getId() {
			return id;
		}
		@Override
		public void setId(int id) {
			this.id = id;
		}
		@Override
		public Site getSite() {
			return site;
		}
		@Override
		public void setSite(Site site) {
			this.site = site;
		}
		@Override
		public String getDescription() {
			return description;
		}
		@Override
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public String getImage() {
			return image;
		}
		@Override
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

		public ArrayList<Voie> getListVoie() {
			return listVoie;
		}

		public void setListVoie(ArrayList<Voie> listVoie) {
			this.listVoie = listVoie;
		}
		
		
}
