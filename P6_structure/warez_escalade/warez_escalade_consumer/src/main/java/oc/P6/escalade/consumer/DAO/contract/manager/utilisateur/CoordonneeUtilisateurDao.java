package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface CoordonneeUtilisateurDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface CoordonneeUtilisateurDao {

	/**
	 * Méthode pour créer les {@link CoordonneeUtilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} créees dans la base de donnée
	 * @throws CoordonneeUtilisateurException 
	 */
	CoordonneeUtilisateur create(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException;

	/**
	 * Méthode pour supprimer les {@link CoordonneeUtilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @throws CoordonneeUtilisateurException 
	 */
	boolean delete (CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException;
	
	/**
	 * Méthode pour modifier l'adresse des {@link CoordonneeUtilisateur} données en paramètre dans la base de donnée
	 * @param pCoordonneeUtilisateur
	 * @return les {@link CoordonneeUtilisateur} modifiées dans la base de donnée
	 * @throws CoordonneeUtilisateurException 
	 */
	CoordonneeUtilisateur update (CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException;
	
	/**
	 * Méthode pour obtenir les {@link CoordonneeUtilisateur} de {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pAuteur
	 * @return {@link CoordonneeUtilisateur}
	 */
	CoordonneeUtilisateur find(Utilisateur pAuteur);
}
