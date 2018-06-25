package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.TopoDaoImpl;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Implémentation de {@link TopoManager}
 * @author nicolas
 *
 */
@Named
public class TopoManagerImpl extends AbstractDAOManager implements TopoManager {

	@Inject
	private Topo topo;
	
	@Inject
	private TopoDaoImpl topoDAO;// = (TopoDaoImpl) getDAOFactory().getTopoManagerDao();
	
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
		System.out.println("CTRL "+pTopo.getNom());
		if (topoDAO.find(pTopo.getNom()) != null) {
			try {
				throw new Exception("Le topo existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			topo.setNom(pTopo.getNom());
			topo.setAuteur(pTopo.getAuteur());
			topo.setNbreEx(pTopo.getNbreEx());
			topo.setDescription(pTopo.getDescription());
			topo.setLatitude(pTopo.getLatitude());
			topo.setLongitude(pTopo.getLongitude());
			topo.setImage(pTopo.getNom().replaceAll("\\p{Space}", ""));
			topoDAO.create(pTopo);
		}
		
	}

}
