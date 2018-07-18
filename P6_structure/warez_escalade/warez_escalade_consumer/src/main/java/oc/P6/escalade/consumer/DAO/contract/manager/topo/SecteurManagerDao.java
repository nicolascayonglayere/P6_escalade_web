package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

/**
 * Interface SecteurManagerDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface SecteurManagerDao {
	
	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre dans la base de donnée
	 * @param pSecteur
	 * @return
	 * @throws SecteurException 
	 */
	boolean create(Secteur pSecteur) throws SecteurException;
	
	/**
	 * Méthode pour supprimer un {@link Secteur} donné en paramètre dans la base de donnée
	 * @param pSecteur
	 * @return
	 * @throws SecteurException 
	 */
	boolean delete (Secteur pSecteur) throws SecteurException;
	
	/**
	 * Méthode pour modifier un {@link Secteur} donné en paramètre dans la base de donnée
	 * @param pSecteur
	 * @return
	 * @throws SecteurException 
	 */
	boolean update (Secteur pSecteur) throws SecteurException;
	
	/**
	 * Méthode pour trouver un {@link Secteur} nommé pNom dans le {@link Site} d'id donné en paramètre dans la base de donnée
	 * @param pSecteur
	 * @return {@link Secteur}
	 */
	Secteur find(String pNom, int pIdSite);
	
	/**
	 * Méthode pour trouver un {@link Secteur} d'id donné en paramètre dans la base de donnée
	 * @param id
	 * @return {@link Secteur}
	 */
	Secteur find(int id);
	
	/**
	 * Méthode pour obtenir la liste des {@link Secteur} d'un {@link Site} donné en paramètre dans la base de donnée
	 * @param pSite
	 * @return liste de {@link Secteur}
	 */
	ArrayList<Secteur> getListeSecteur(Site pSite);
}
