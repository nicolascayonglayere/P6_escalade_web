package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
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
	 * @throws SiteException 
	 */
	Site getSite(String pNom, Topo pTopo) throws SiteException;
	
	/**
	 * Retroune la liste des {@link Site} du {@link Topo} donné en paramètre
	 * @param pTopo
	 * @return la liste des {@link Site}
	 * @throws TopoException 
	 */
	ArrayList<Site> getSite (Topo pTopo) throws TopoException;
	
	/**
	 * Retourne le {@link Site} à partir de son id donné en paramètre
	 * @param pId
	 * @return {@link Site}
	 * @throws SiteException 
	 */
	Site getSite(int pId) throws SiteException;
	
	/**
	 * Crée le {@link Site} donné en paramètre
	 * @param pSite
	 * @throws SiteException 
	 */
	void creerSite(Site pSite) throws SiteException;
	
	/**
	 * Modifie le {@link Site} donné en paramètre
	 * @param pSite
	 * @throws SiteException 
	 */
	void modifierSite(Site pSite) throws SiteException;
	
	/**
	 * Supprime le {@link Site} donné en paramètre
	 * @param pSite
	 * @throws SiteException 
	 */
	void supprimmerSite(Site pSite) throws SiteException;
}
