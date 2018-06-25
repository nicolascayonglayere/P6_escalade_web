package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

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
	 */
	boolean create(Voie pVoie);

	/**
	 * Méthode pour supprimer la {@link Voie} donnée en paramètre dans la base de donnée
	 * @param pVoie
	 * @return
	 */
	boolean delete (Voie pVoie);
	
	/**
	 * Méthode pour modifier la {@link Voie} donnée en paramètre dans la base de donnée
	 * @param pVoie
	 * @return
	 */
	boolean update (Voie pVoie);
	
	/**
	 * Méthode pour obtenir la {@link Voie} de nom pNom du {@link secteur} d'id pId dans la base de donnée
	 * @param pVoie
	 * @return {@link Voie}
	 */
	Voie find(String pNom, int pIdSecteur);
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donnée en paramètre dans la base de donné
	 * @param pSecteur
	 * @return la liste des {@link Voie}
	 */
	ArrayList<Voie> getlistVoie(Secteur pSecteur);
}
