package oc.P6.escalade.business.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

/**
 * Interface CoordonneeUtilisateurManager et ses méthodes
 * @author nicolas
 *
 */
public interface CoordonneeUtilisateurManager {

	/**
	 * Renvoie les {@link CoordonneeUtilisateur} de {@link Utilisateur} d'id pId
	 * @param pId
	 * @return {@link CoordonneeUtilisateur}
	 * @throws CoordonneeUtilisateurException 
	 * @throws UtilisateurException 
	 */
	CoordonneeUtilisateur getCoordonnee(int pId) throws CoordonneeUtilisateurException, UtilisateurException;
	
	/**
	 * Cree les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 * @throws CoordonneeUtilisateurException 
	 */
	void creerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException;

	/**
	 * Modifie les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} modifiée
	 */
	CoordonneeUtilisateur modifierAdresse(CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	/**
	 * Modifie l'email des {@link CoordonneeUtilisateur} donnée en paramètre
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} modifiée
	 */
	CoordonneeUtilisateur modifierEmail(CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	/**
	 * Supprimme les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 * @throws CoordonneeUtilisateurException 
	 */
	void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException;
}
