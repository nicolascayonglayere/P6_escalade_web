package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface UtilisateurManagerDAO et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface UtilisateurManagerDAO {
	
	/**
	 * Méthode pour créer un {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return {@link} crée
	 * @throws UtilisateurException 
	 */
	Utilisateur create(Utilisateur pUtilisateur) throws UtilisateurException;

	/**
	 * Méthode pour supprimer un {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return
	 * @throws UtilisateurException 
	 */
	boolean delete (Utilisateur pUtilisateur) throws UtilisateurException;
	
	/**
	 * Méthode pour modifier le mot de passe de {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return {@link} modifié
	 */
	Utilisateur updatePass (Utilisateur pUtilisateur);
	
	/**
	 * Méthode pour modifier le pseudo du {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return
	 */
	Utilisateur updatePseudo (Utilisateur pUtilisateur);
	
	/**
	 * Méthode pour modifier le role de {@link Utilisateur} donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return
	 */
	boolean updateRole(Utilisateur pUtilisateur);
	
	/**
	 * Méthode pour obtenir un {@link Utilisateur} avec son pseudo donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return {@link Utilisateur}
	 */	
	Utilisateur find(String pPseudo);
	
	/**
	 * Méthode pour obtenir un {@link Utilisateur} avec son id donné en paramètre dans la base de donnée
	 * @param pUtilisateur
	 * @return {@link Utilisateur}
	 */
	Utilisateur find(int pId);
	
	/**
	 * Méthode pour obtenir un {@link Utilisateur} avec son pseudo et son mot de passe donnés en paramètre dans la base de donnée
	 * Objectif authentification
	 * @param pUtilisateur
	 * @return {@link Utilisateur}
	 */
	Utilisateur findPass(String pPassword, String pPseudo);
	
	/**
	 * Méthode pour obtenir la liste des {@link Utilisateur} dont le role est défini en paramètre dans la base de donnée
	 * @param pIdRole
	 * @return liste des {@link Utilisateur}
	 */
	ArrayList<Utilisateur> getList(int pIdRole);
	
	/**
	 * ??????
	 * @param pPseudo
	 * @return
	 */
	ArrayList<Utilisateur> getList(String pPseudo);

}
