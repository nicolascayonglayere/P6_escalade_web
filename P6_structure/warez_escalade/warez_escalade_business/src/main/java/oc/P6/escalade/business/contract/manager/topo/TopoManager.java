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
	
	
}
