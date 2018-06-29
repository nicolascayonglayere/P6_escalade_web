package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

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
	 */
	ArrayList<Voie> getListVoie(Secteur pSecteur);
	
	/**
	 * Renvoie la {@link Voie} du {@link Secteur} donné en paramètre
	 * @return {@link Voie}
	 */
	Voie getVoie(String pNom, Secteur pSecteur);
	
	/**
	 * Cree la {@link Voie} donnée en paramètre
	 * @param pVoie
	 */
	void creerVoie(Voie pVoie);
	
	/**
	 * Modifie la {@link Voie} donnée en paramètre
	 * @param pVoie
	 */
	void majVoie(Voie pVoie);
	
	/**
	 * Supprime la {@link Voie} donnée en paramètre
	 * @param pVoie
	 */
	void supprimerVoie(Voie pVoie);
}
