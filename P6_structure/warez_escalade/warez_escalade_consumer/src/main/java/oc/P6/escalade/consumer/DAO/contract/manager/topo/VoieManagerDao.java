package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

/**
 * Interface VoieManagerDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface VoieManagerDao {

	/**
	 * Méthode pour créer la {@link Voie} donnée en paramètre dans la base de donnée
	 * @param pVoie
	 * @return
	 * @throws VoieException 
	 */
	Voie create(Voie pVoie) throws VoieException;

	/**
	 * Méthode pour supprimer la {@link Voie} donnée en paramètre dans la base de donnée
	 * @param pVoie
	 * @return
	 * @throws VoieException 
	 */
	boolean delete (Voie pVoie) throws VoieException;
	
	/**
	 * Méthode pour modifier la {@link Voie} donnée en paramètre dans la base de donnée
	 * @param pVoie
	 * @return
	 * @throws VoieException 
	 */
	boolean update (Voie pVoie) throws VoieException;
	
	/**
	 * Méthode pour obtenir la {@link Voie} d'id donnée en paramètre
	 * @param pId
	 * @return {@link Voie}
	 */
	Voie find(int pId);
	
	/**
	 * Méthode pour obtenir la {@link Voie} de nom pNom du {@link secteur} d'id pId dans la base de donnée
	 * @param pNom
	 * @param pIdSecteur
	 * @return {@link Voie}
	 */
	Voie find(String pNom, int pIdSecteur);
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donnée en paramètre dans la base de donné
	 * @param pSecteur
	 * @return la liste des {@link Voie}
	 * @throws VoieException 
	 */
	ArrayList<Voie> getlistVoie(Secteur pSecteur) throws VoieException;
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} dont la difficulté est donnée en paramètre dans la base de donné
	 * @param pDiffMin
	 * @param pDiffMax
	 * @return la liste des {@link Voie}
	 */
	ArrayList<Voie> rechercheDiffVoie(String pDiffMin, String pDiffMax);
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} de la base de donnée à partir d'un nom et d'une difficulté définie dans un intervalle donné en paramètre
	 * @param pNom
	 * @param pDiffMin
	 * @param pDiffMax
	 * @return liste de {@link Voie}
	 */
	ArrayList<Voie> rechercheMultiVoie(String pNom, String pDiffMin, String pDiffMax);
}
