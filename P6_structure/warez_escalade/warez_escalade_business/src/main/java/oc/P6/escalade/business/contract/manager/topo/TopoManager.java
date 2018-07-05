package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

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
	 */
	ArrayList<Topo> getListTopoConstr(String pNom);
	
	/**
	 * Retourne le {@link Topo} dont le nom est donné en parmètre
	 * @param pNom
	 * @return {@link Topo}
	 */
	Topo getTopo(String pNom);
	
	/**
	 * Crée le {@link Topo} donné en paramètre
	 * @param pTopo
	 */
	void creerTopo(Topo pTopo);
	
	/**
	 * Modifie le {@link Topo} donné en paramètre
	 * @param pTopo
	 */
	void modifTopo(Topo pTopo);
	
	/**
	 * Retourne la liste des {@link Topo} à partir d'un nom donné  en paramètre
	 * @param pNom
	 * @return
	 */
	ArrayList<Topo> rechercheTopo(String pNom);
	
	/**
	 * Supprime le {@link Topo} donné en paramètre
	 * @param pTopo
	 */
	void supprimerTopo(Topo pTopo);
}
