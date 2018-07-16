package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface CoordonneeUtilisateurDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface CoordonneeUtilisateurDao {

	/**
	 * Méthode pour créer les {@link Coordonneeutilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} créees dans la base de donnée
	 */
	CoordonneeUtilisateur create(CoordonneeUtilisateur pCoordonneeUtilisateur);

	/**
	 * Méthode pour supprimer les {@link Coordonneeutilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return
	 */
	boolean delete (CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	/**
	 * Méthode pour modifier l'adresse des {@link Coordonneeutilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} modifiées dans la base de donnée
	 */
	CoordonneeUtilisateur updateAdresse (CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	/**
	 * Méthode pour modifier l'email des {@link Coordonneeutilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} modifiées dans la base de donnée
	 */
	CoordonneeUtilisateur updateEmail (CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	/**
	 * Méthode pour obtenir les {@link Coordonneeutilisateur} de {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return {@link CoordonneeUtilisateur}
	 */
	CoordonneeUtilisateur find(Utilisateur pAuteur);
}
