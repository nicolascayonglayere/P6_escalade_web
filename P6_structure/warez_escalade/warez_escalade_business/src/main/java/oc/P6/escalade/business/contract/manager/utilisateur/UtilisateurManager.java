package oc.P6.escalade.business.contract.manager.utilisateur;

import java.util.ArrayList;
import java.util.List;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;




	/**
	 * Manager du package « utilisateur »
	 */
	public interface UtilisateurManager {

	    /**
	     * Renvoie la liste des {@link Utilisateur}
	     *
	     * @return List
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
	    
	    Utilisateur getUtilisateurPass(String pPassword);

	    /**
	     * Ajoute un nouvel {@link Utilisateur}
	     *
	     * @param pUtilisateur -
	     * @throws FunctionalException Si le pseudo est déjà utilisé
	     */
	    void creerUtilisateur(Utilisateur pUtilisateur); // throws FunctionalException;

	    /**
	     * Supprime un {@link Utilisateur}
	     *
	     * @param pUtilisateur -
	     */
	    void deleteUtilisateur(Utilisateur pUtilisateur);
	    
	    ArrayList<Utilisateur> getListAdmin();
	    
	    ArrayList<Utilisateur> getListModo();
	    
	    void modifierUtilisateur(Utilisateur pUtilisateur);
}
