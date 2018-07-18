package oc.P6.escalade.business.contract.manager;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface TopoEmpruntManager et ses méthodes
 * @author nicolas
 *
 */
public interface TopoEmpruntManager {
	
	/**
	 * Retourne la liste des {@link TopoEmprunt} de l'{@link Utilisateur} dont l'id est donné en paramètre
	 * @param pId_utilisateur
	 * @return la liste des {@link TopoEmprunt}
	 * @throws UtilisateurException 
	 */
	ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur) throws UtilisateurException;
	
	/**
	 * Retourne le {@link TopoEmprunt} nommé pNom de {@link Utilisateur} donné en paramètre
	 * @param pNom
	 * @param pEmprunteur
	 * @return {@link TopoEmprunt}
	 * @throws UtilisateurException 
	 */
	TopoEmprunt getTopoEmprunt(String pNom, Utilisateur pEmprunteur) throws UtilisateurException;
	
	/**
	 * Crée le {@link TopoEmprunt} du {@link Topo} donné en paramètre pour {@link Utilisateur} donné en paramètre
	 * @param topo
	 * @param pEmprunteur
	 * @return le {@link TopoEmprunt} crée
	 */
	TopoEmprunt creerTopoEmprunt(Topo topo, Utilisateur pEmprunteur);
	
	/**
	 * 
	 * @param topo
	 */
	void ajoutTopoEmprunt(Topo topo);
	
	/**
	 * Supprime le {@link TopoEmprunt} donné en paramètre pour {@link Utilisateur} donné en paramètre
	 * @param pTopoEmprunt
	 * @param pEmprunteur
	 */
	void retourTopoEmprunt(TopoEmprunt pTopoEmprunt, Utilisateur pEmprunteur);
	
	/**
	 * Renvoie le nombre d'exemplaire du {@link Topo} donné en paramètre
	 * @param pTopo
	 * @return
	 */
	int getNbExemplaire(Topo pTopo);
}
