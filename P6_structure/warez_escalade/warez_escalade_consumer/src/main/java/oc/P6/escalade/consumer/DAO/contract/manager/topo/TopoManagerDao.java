package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Interface TopoManagerDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface TopoManagerDao {

	/**
	 * Méthode pour créer le {@link Topo} donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return
	 */
	boolean create(Topo pTopo);

	/**
	 * Méthode pour supprimer le {@link Topo} donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return
	 */
	boolean delete (Topo pTopo);
	
	/**
	 * Méthode pour modifier le {@link Topo} donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return
	 */
	boolean update (Topo pTopo);
	
	/**
	 * Méthode pour trouver le {@link Topo} dont le nom est donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return {@link Topo}
	 */
	Topo find(String pNom);
	
	/**
	 * Méthode pour trouver le {@link Topo} dont l'id est donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return {@link Topo}
	 */	
	Topo find(int pId);
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo} dans la base de donnée
	 * @return la liste des {@link Topo}
	 */
	ArrayList<Topo> listerTopo();
}
