package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
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
	 * @throws SiteException 
	 */
	ArrayList<Secteur> getListSecteur(Site pSite) throws SiteException;
	
	/**
	 * Retourne le {@link Secteur} nommé pNom du {@link Site} donné en paramètre
	 * @param pNom
	 * @param pSite
	 * @return {@link Secteur}
	 * @throws SecteurException 
	 */
	Secteur getSecteur(String pNom, Site pSite) throws SecteurException;
	
	/**
	 * Retourne le {@link Secteur} d'id donné en paramètre
	 * @param pId
	 * @return {@link Secteur}
	 * @throws SecteurException 
	 */
	Secteur getSecteur(int pId) throws SecteurException;
	
	/**
	 * Crée le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 * @throws SecteurException 
	 */
	Secteur creerSecteur(Secteur pSecteur) throws SecteurException;
	
	/**
	 * Modifie le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 * @throws SecteurException 
	 */
	void modifierSecteur(Secteur pSecteur) throws SecteurException;
	
	/**
	 * Supprime le {@link Secteur} donné en paramètre
	 * @param pSecteur
	 * @throws SecteurException 
	 */
	void supprimerSecteur(Secteur pSecteur) throws SecteurException;
}
