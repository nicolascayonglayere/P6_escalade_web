package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
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
	 */
	boolean create(Site pSite);

	/**
	 * Méthode pour supprimer un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return
	 */
	boolean delete (Site pSite);
	
	/**
	 * Méthode pour modifier un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return
	 */
	boolean update (Site pSite);
	
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
