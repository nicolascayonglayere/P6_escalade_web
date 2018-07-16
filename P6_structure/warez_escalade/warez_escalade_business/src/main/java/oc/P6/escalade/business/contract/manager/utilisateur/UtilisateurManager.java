package oc.P6.escalade.business.contract.manager.utilisateur;

import java.util.ArrayList;


import oc.P6.escalade.model.bean.utilisateur.Utilisateur;


	/**
	 * Interface UtilisateurManager et ses méthodes
	 */
	public interface UtilisateurManager {

	    /**
	     * Renvoie la liste des {@link Utilisateur}
	     *
	     * @return List d'{@link Utilisateur}
	     */
		ArrayList<Utilisateur> getListUtilisateur(String pPseudo);

	    /**
	     * Renvoie l'{@link Utilisateur} demandé
	     *
	     * @param pPseudo le pseudo de l'Utilisateur
	     * @return Le {@link Utilisateur}
	     * @throws NotFoundException Si l'Utilisateur n'est pas trouvé
	     */
	    Utilisateur getUtilisateur(String pPseudo); //throws NotFoundException;
	    
	    /**
	     * Renvoie l'{@link Utilisateur} demandé pour l'authentification
	     * @param pPassword
	     * @param pPseudo
	     * @return Le {@link Utilisateur}
	     */
	    Utilisateur getUtilisateurPass(String pPassword, String pPseudo);

	    /**
	     * Ajoute un nouvel {@link Utilisateur}
	     *
	     * @param pUtilisateur -
	     * @throws FunctionalException Si le pseudo est déjà utilisé
	     * @return le {@link Utilisateur} crée
	     */
	    Utilisateur creerUtilisateur(Utilisateur pUtilisateur); // throws FunctionalException;

	    /**
	     * Supprime un {@link Utilisateur}
	     *
	     * @param pUtilisateur -
	     */
	    void deleteUtilisateur(Utilisateur pUtilisateur);
	    
	    /**
	     * Renvoie la liste des {@link Utilisateur} qui ont le role d'administrateur
	     * @return les liste des admin
	     */
	    ArrayList<Utilisateur> getListAdmin();
	    
	    /**
	     * Renvoie la liste des {@link Utilisateur} qui ont le role de modérateur
	     * @return la liste des modérateurs
	     */
	    ArrayList<Utilisateur> getListModo();
	    
	    /**
	     * Modifie l'{@link Utilisateur} donné en paramètre
	     * @param pUtilisateur
	     * @return {@link} modifié
	     */
	    Utilisateur modifierPseudoUtilisateur(Utilisateur pUtilisateur);
	    
	    /**
	     * Modifie le mot de passe de l'{@link Utilisateur} donné en paramètre (cryptage en bdd)
	     * @param pUtilisateur
	     * @return {@link Utilisateur}
	     */
	    Utilisateur modifierPassUtilisateur(Utilisateur pUtilisateur);
	    
	    /**
	     * Banni l'{@link Utilisateur} donné en paramètre (modifie son role)
	     * @param pUtilisateur
	     */
	    void banUtilisateur(Utilisateur pUtilisateur);
	    
	    /**
	     * Modifie le role de {@link Utilisateur} donné en paramètre
	     * @param pUtilisateur
	     */
	    void modifierRoleUtilisateur(Utilisateur pUtilisateur);
}
