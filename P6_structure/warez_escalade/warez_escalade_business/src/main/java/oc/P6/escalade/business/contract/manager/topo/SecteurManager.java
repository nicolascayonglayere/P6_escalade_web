package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

/**
 * Interface SecteurManager et ses méthodes
 * @author nicolas
 *
 */
public interface SecteurManager {
	/**
	 * Retourne la liste des {@link Secteur} du {@link Site} donné en paramètre
	 * @param pSite
	 * @return la liste des {@link Secteur}
	 */
	ArrayList<Secteur> getListSecteur(Site pSite);
	
	/**
	 * Retourne le {@link Secteur} nommé pNom du {@link Site} donné en paramètre
	 * @param pNom
	 * @param pSite
	 * @return {@link Secteur}
	 */
	Secteur getSecteur(String pNom, Site pSite);
	
	/**
	 * Crée le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 */
	void creerSecteur(Secteur pSecteur);
	
	/**
	 * Modifie le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 */
	void modifierSecteur(Secteur pSecteur);
	
	/**
	 * Supprime le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 */
	void supprimerSecteur(Secteur pSecteur);
}
