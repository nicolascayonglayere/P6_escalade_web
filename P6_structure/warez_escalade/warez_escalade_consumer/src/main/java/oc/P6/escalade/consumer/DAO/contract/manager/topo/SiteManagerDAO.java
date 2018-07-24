package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Interface SiteManagerDAO et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface SiteManagerDAO {

	/**
	 * Méthode pour créer un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return
	 * @throws SiteException 
	 */
	Site create(Site pSite) throws SiteException;

	/**
	 * Méthode pour supprimer un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return
	 * @throws SiteException 
	 */
	boolean delete (Site pSite) throws SiteException;
	
	/**
	 * Méthode pour modifier un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return
	 * @throws SiteException 
	 */
	boolean update (Site pSite) throws SiteException;
	
	/**
	 * Méthode pour obtenir la liste des {@link Site} d'un {@link Topo} dont le nom est donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return la liste des {@link Site}
	 */
	ArrayList<Site> find(String pNom);
	
	/**
	 * Méthode pour obtenir la liste des {@link Site} d'un {@link Topo} dont l'id est donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return la liste des {@link Site}
	 */
	ArrayList<Site> find(int pId);
	
	/**
	 * Méthode pour obtenir la {@link Site} à partir d'une partie du nom donné en paramètre
	 * @param pNom
	 * @return la liste des {@link Site}
	 */
	ArrayList<Site> rechercheSite(String pNom);
	
	/**
	 * Méthode pour obtenir la liste des {@link Site} de la base de donnée à partir d'un nom et contenant des {@link Voie} d'une difficulté définie dans un intervalle donné en paramètre
	 * @param pNom
	 * @param pDiffMin
	 * @param pDiffMax
	 * @return
	 */
	ArrayList<Site> rechercheMultiSite(String pNom, String pDiffMin, String pDiffMax);
	
	/**
	 * Méthode pour trouver le {@link Site}	 de nom pNom du {@link Topo} pTopo dans la base de donnée
	 * @param pNom
	 * @param pIdTopo
	 * @return {@link Site}
	 */
	Site find(String pNom, int pIdTopo);
	/**
	 * Méthode pour trouver le {@link Site}	d'id pId dans la base de donnée
	 * @param pNom
	 * @param pIdTopo
	 * @return {@link Site}
	 */
	Site get(int pId);
}
