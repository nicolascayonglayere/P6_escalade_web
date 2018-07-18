package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

/**
 * Interface VoieManager et ses méthodes
 * @author nicolas
 *
 */
public interface VoieManager {
	
	/**
	 * Renvoie la liste des {@link Voie} du {@link Secteur} donné en paramètre
	 * @param pSecteur
	 * @return la liste des {@link Voie} du {@link Secteur} pSecteur
	 * @throws SecteurException 
	 */
	ArrayList<Voie> getListVoie(Secteur pSecteur) throws SecteurException;
	
	/**
	 * Renvoie la {@link Voie} du {@link Secteur} donné en paramètre
	 * @return {@link Voie}
	 * @throws VoieException 
	 */
	Voie getVoie(String pNom, Secteur pSecteur) throws VoieException;
	
	/**
	 * Renvoie la {@link Voie} d'id donné en paramètre
	 * @param pId
	 * @return {@link Voie}
	 */
	Voie getVoie(int pId);
	
	/**
	 * Cree la {@link Voie} donnée en paramètre
	 * @param pVoie
	 * @throws VoieException 
	 */
	void creerVoie(Voie pVoie) throws VoieException;
	
	/**
	 * Modifie la {@link Voie} donnée en paramètre
	 * @param pVoie
	 * @throws VoieException 
	 */
	void majVoie(Voie pVoie) throws VoieException;
	
	/**
	 * Supprime la {@link Voie} donnée en paramètre
	 * @param pVoie
	 * @throws VoieException 
	 */
	void supprimerVoie(Voie pVoie) throws VoieException;
}
