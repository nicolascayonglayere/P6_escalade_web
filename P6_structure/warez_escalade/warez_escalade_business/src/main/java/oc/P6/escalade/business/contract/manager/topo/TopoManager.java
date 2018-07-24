package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Interface TopoManager et ses méthodes
 * @author nicolas
 *
 */
public interface TopoManager {

	/**
	 * Retourne la liste des {@link Topo}
	 * @return
	 */
	ArrayList<Topo> getListTopo();
	
	/**
	 * Retourne la liste des {@link Topo} en construction d'après le nom de l'auteur donné en paramètre
	 * @param pNom
	 * @return
	 * @throws UtilisateurException 
	 */
	ArrayList<Topo> getListTopoConstr(String pNom) throws UtilisateurException;
	
	/**
	 * Retourne le {@link Topo} dont le nom est donné en parmètre
	 * @param pNom
	 * @return {@link Topo}
	 * @throws TopoException 
	 */
	Topo getTopo(String pNom) throws TopoException;
	
	/**
	 * Crée le {@link Topo} donné en paramètre
	 * @param pTopo
	 * @throws TopoException 
	 */
	Topo creerTopo(Topo pTopo) throws TopoException;
	
	/**
	 * Modifie le {@link Topo} donné en paramètre
	 * @param pTopo
	 * @throws TopoException 
	 */
	void modifTopo(Topo pTopo) throws TopoException;
	
	/**
	 * Retourne la liste des {@link Topo} à partir d'un nom donné  en paramètre
	 * @param pNom
	 * @return liste des {@link Topo}
	 */
	ArrayList<Topo> rechercheTopo(String pNom);
	
	/**
	 * Retourne la liste des {@link Topo} à partir d'un nom et d'un intervalle de difficulté donnés en paramètre
	 * @param pNom
	 * @param pDiffMin
	 * @param pDiffMax
	 * @throws TopoException 
	 * @returnla liste des {@link Topo}
	 */
	ArrayList<Topo> rechercheMultiTopo(String pNom, String pDiffMin, String pDiffMax) throws TopoException;
	
	/**
	 * Supprime le {@link Topo} donné en paramètre
	 * @param pTopo
	 * @throws TopoException 
	 * @throws VoieException 
	 * @throws SecteurException 
	 * @throws SiteException 
	 */
	void supprimerTopo(Topo pTopo) throws TopoException, VoieException, SecteurException, SiteException;
}
