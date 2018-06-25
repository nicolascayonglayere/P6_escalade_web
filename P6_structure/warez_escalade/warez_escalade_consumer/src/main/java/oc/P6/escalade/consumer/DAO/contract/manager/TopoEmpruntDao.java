package oc.P6.escalade.consumer.DAO.contract.manager;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
/**
 * Interface TopoEmprintDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface TopoEmpruntDao {

	/**
	 * Méthode pour créer un {@link TopoEmprunt} donné en paramètre dans la base de donnée
	 * @param pTopoEmprunt
	 * @return
	 */
	boolean create(TopoEmprunt pTopoEmprunt);
	
	/**
	 * Méthode pour supprimer un {@link TopoEmprunt} donné en paramètre dans la base de donnée 
	 * @param pTopoEmprunt
	 * @return
	 */
	boolean delete (TopoEmprunt pTopoEmprunt);
	
	/**
	 * Méthode pour modifier un {@link TopoEmprunt} donné en paramètre dans la base de donnée
	 * @param pTopoEmprunt
	 * @return
	 */
	boolean update (TopoEmprunt pTopoEmprunt);
	
	/**
	 * Méthode pour obtenir un {@link TopoEmprunt} d'un {@link Topo} pour un {@link Utilisateur} 
	 * @param pIdTopo
	 * @param pIdEmprunteur
	 * @return {@link TopoEmprunt}
	 */
	TopoEmprunt find(int pIdTopo, int pIdEmprunteur);
	
	/**
	 * Méthode pour obtenir la liste des {@link TopoEmprunt} d'un {@link Utilisateur}
	 * @param pId_utilisateur
	 * @return liste des {@link TopoEmprunt}
	 */
	ArrayList<TopoEmprunt> getListTopoEmprunt (int pId_utilisateur);
	
	/**
	 * Méthode pour obtenir la liste des {@link TopoEmprunt} d'un {@link Topo}
	 * @param pTopo
	 * @return liste des {@link TopoEmprunt}
	 */
	ArrayList<TopoEmprunt> getListTopoEmprunt (Topo pTopo);
}
