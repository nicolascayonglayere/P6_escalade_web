package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Interface SiteManager et ses méthodes
 * @author nicolas
 *
 */
public interface SiteManager {
	/**
	 * Retourne la liste des {@link Site}
	 * @return
	 */
	ArrayList<Site> getListSite();
	
	/**
	 * Retourne le {@link Site} nommé pNom du {@link Topo} donné en paramètre
	 * @param pNom
	 * @param pTopo
	 * @return {@link Site}
	 */
	Site getSite(String pNom, Topo pTopo);
	
	/**
	 * Retroune la liste des {@link Site} du {@link Topo} donné en paramètre
	 * @param pTopo
	 * @return la liste des {@link Site}
	 */
	ArrayList<Site> getSite (Topo pTopo);
	
	/**
	 * Retourne le {@link Site} à partir de son id donné en paramètre
	 * @param pId
	 * @return {@link Site}
	 */
	Site getSite(int pId);
	
	/**
	 * Crée le {@link Site} donné en paramètre
	 * @param pSite
	 */
	void creerSite(Site pSite);
	
	/**
	 * Modifie le {@link Site} donné en paramètre
	 * @param pSite
	 */
	void modifierSite(Site pSite);
	
	/**
	 * Supprime le {@link Site} donné en paramètre
	 * @param pSite
	 */
	void supprimmerSite(Site pSite);
}
