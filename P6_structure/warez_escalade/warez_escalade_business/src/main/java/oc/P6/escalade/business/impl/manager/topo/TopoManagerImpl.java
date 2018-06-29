package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.contract.topo.IntTopo;

/**
 * Implémentation de {@link TopoManager}
 * @author nicolas
 *
 */
@Named
public class TopoManagerImpl extends AbstractDAOManager implements TopoManager {

	@Inject
	private IntTopo topo;
	
	@Inject
	private TopoManagerDao topoDAO;// = (TopoDaoImpl) getDAOFactory().getTopoManagerDao();
	
	//private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo}
	 */
	@Override
	public ArrayList<Topo> getListTopo() {
		ArrayList<Topo> listeTopo = topoDAO.listerTopo();
		return listeTopo;
	}

	/**
	 * Méthode pour obtenir le {@link Topo} dont le nom est donné en paramètre
	 */
	@Override
	public Topo getTopo(String pNom) {
		Topo vTopo = null;
		if (topoDAO.find(pNom) != null) {
			vTopo = topoDAO.find(pNom);
		}
		else
			try {
				throw new Exception ("Le topo n'existe pas.");
			}catch (Exception e) {
				e.printStackTrace();
			}
		return vTopo;
	}

	/**
	 * Méthode pour créer le {@link Topo} donné en paramètre
	 */
	@Override
	public void creerTopo(Topo pTopo) {
		System.out.println("CTRL "+pTopo.getNomTopo());
		if (topoDAO.find(pTopo.getNomTopo()) != null) {
			try {
				throw new Exception("Le topo existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			topo.setNomTopo(pTopo.getNomTopo());
			topo.setAuteur(pTopo.getAuteur());
			topo.setNbreEx(pTopo.getNbreEx());
			topo.setDescription(pTopo.getDescription());
			topo.setLatitude(pTopo.getLatitude());
			topo.setLongitude(pTopo.getLongitude());
			topo.setImage(pTopo.getNomTopo().replaceAll("\\p{Space}", ""));
			topoDAO.create((Topo)topo);
		}
		
	}

	/**
	 * Méthode pour modifier le {@link Topo} donné en paramètre
	 */
	@Override
	public void modifTopo(Topo pTopo) {
		System.out.println("CTRL "+pTopo.getNomTopo());
		if (topoDAO.find(pTopo.getNomTopo()) == null) {
			try {
				throw new Exception("Le topo n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			topo.setId(topoDAO.find(pTopo.getNomTopo()).getId());
			topo.setNomTopo(pTopo.getNomTopo());
			topo.setAuteur(pTopo.getAuteur());
			topo.setNbreEx(pTopo.getNbreEx());
			topo.setDescription(pTopo.getDescription());
			topo.setLatitude(pTopo.getLatitude());
			topo.setLongitude(pTopo.getLongitude());
			topo.setImage(pTopo.getNomTopo().replaceAll("\\p{Space}", ""));
			topoDAO.update((Topo)topo);
		}
		
	}

	/**
	 * Méthode pour obtenir une liste de {@link Topo} à partir d'un nom donné en paramètre
	 */
	@Override
	public ArrayList<Topo> rechercheTopo(String pNom) {
		ArrayList<Topo>listTopo = topoDAO.rechercherTopo(pNom) ;
		System.out.println("business recherche "+pNom+" - "+listTopo.size());
		return listTopo;
	}

	@Override
	public void supprimerTopo(Topo pTopo) {
		System.out.println("CTRL business "+pTopo.getNomTopo());
		if (topoDAO.find(pTopo.getNomTopo()) == null) {
			try {
				throw new Exception("Le topo n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			topo.setId(topoDAO.find(pTopo.getNomTopo()).getId());
			topo.setNomTopo(pTopo.getNomTopo());
			topo.setAuteur(pTopo.getAuteur());
			topo.setNbreEx(pTopo.getNbreEx());
			topo.setDescription(pTopo.getDescription());
			topo.setLatitude(pTopo.getLatitude());
			topo.setLongitude(pTopo.getLongitude());
			topo.setImage(pTopo.getNomTopo().replaceAll("\\p{Space}", ""));
			topoDAO.delete((Topo) topo);
		}
		
	}

}
