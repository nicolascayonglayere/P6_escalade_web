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
	Secteur create(Secteur pSecteur) throws SecteurException;
	
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
	 * @param pNom
	 * @param pIdSite
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
	
	/**
	 * Méthode pour obtenir la liste des {@link Secteur} de la base de donnée à partir d'un nom et contenant des {@link Voie} d'une difficulté définie dans un intervalle donné en paramètre
	 * @param pNom
	 * @param pDiffMin
	 * @param pDiffMax
	 * @return liste de {@link Secteur}
	 */
	ArrayList<Secteur> rechercheMultiSecteur(String pNom, String pDiffMin, String pDiffMax);
}
