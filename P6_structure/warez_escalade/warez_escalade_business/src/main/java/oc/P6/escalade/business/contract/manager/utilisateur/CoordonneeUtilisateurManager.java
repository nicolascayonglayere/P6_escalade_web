package oc.P6.escalade.business.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;

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
	 */
	CoordonneeUtilisateur getCoordonnee(int pId);
	
	/**
	 * Cree les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 */
	void creerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur);

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
	 */
	void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur);
}
