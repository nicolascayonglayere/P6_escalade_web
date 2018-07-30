package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.exception.TopoException;
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
	 * @return le {@link Topo} crée
	 * @throws TopoException 
	 */
	Topo create(Topo pTopo) throws TopoException;

	/**
	 * Méthode pour supprimer le {@link Topo} donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return
	 * @throws TopoException 
	 */
	boolean delete (Topo pTopo) throws TopoException;
	
	/**
	 * Méthode pour modifier le {@link Topo} donné en paramètre dans la base de donnée
	 * @param pTopo
	 * @return
	 * @throws TopoException 
	 */
	boolean update (Topo pTopo) throws TopoException;
	
	/**
	 * Méthode pour trouver le {@link Topo} dont le nom est donné en paramètre dans la base de donnée
	 * @param pNom
	 * @return {@link Topo}
	 */
	Topo find(String pNom);
	
	/**
	 * Méthode pour trouver le {@link Topo} dont l'id est donné en paramètre dans la base de donnée
	 * @param pId
	 * @return {@link Topo}
	 */	
	Topo find(int pId);
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo} dans la base de donnée
	 * @return la liste des {@link Topo}
	 */
	ArrayList<Topo> listerTopo();
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo} dans la base de donnée à partir du nom de l'auteur donné en paramètre
	 * @param pNom
	 * @return la liste des {@link Topo} en construction
	 */
	ArrayList<Topo> listerTopo(String pNom);
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo} de la base de donnée à partir d'un nom donné en paramètre
	 * @param pNom
	 * @return la liste des {@link Topo}
	 */
	ArrayList<Topo> rechercherTopo(String pNom);
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo} de la base de donnée à partir d'un nom et contenant des {@link Voie} d'une difficulté définie dans un intervalle donné en paramètre
	 * @param pNom
	 * @param pDiffMin
	 * @param pDiffMax
	 * @return la liste des {@link Topo}
	 */
	ArrayList<Topo> rechercheMultiTopo(String pNom, String pDiffMin, String pDiffMax);
	
}
