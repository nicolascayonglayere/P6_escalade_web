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
	void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur);

	/**
	 * Modifie les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 */
	void modifierCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur);
	
	/**
	 * Supprimme les {@link CoordonneeUtilisateur} donnee en parametre
	 * @param pCoordinneeUtilisateur
	 */
	void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur);
}
