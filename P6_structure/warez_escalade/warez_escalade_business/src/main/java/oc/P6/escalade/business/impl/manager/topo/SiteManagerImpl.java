package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.SiteDaoImpl;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Implémentation de {@link SiteManager}
 * @author nicolas
 *
 */
@Named
public class SiteManagerImpl extends AbstractDAOManager implements SiteManager{

	@Inject
	private Site site;
	
	@Inject
	private SiteDaoImpl siteDAO;// = (SiteDaoImpl) getDAOFactory().getSiteManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Site> getListSite() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Méthode pour obtenir le {@link Site} nommé pNom du {@link Topo} donné en paramètre
	 */
	@Override
	public Site getSite(String pNom, Topo pTopo) {
		Site site = null;
		if (siteDAO.find(pNom, pTopo.getId()) != null) {
			site = siteDAO.find(pNom, pTopo.getId());
		}
		else {
			try {
				throw new Exception("Le site n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return site;
	}

	/**
	 * Méthode pour créer le {@link Site} donné en paramètre
	 */
	@Override
	public void creerSite(Site pSite) {
		System.out.println("CTRL "+pSite.getNom());
		if (siteDAO.find(pSite.getNom(), pSite.getTopo().getId()) != null) {
			try {
				throw new Exception("Le site existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			site.setNom(pSite.getNom());
			site.setDescription(pSite.getDescription());
			site.setTopo(pSite.getTopo());
			siteDAO.create(pSite);
		}
		
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} du {@link Topo} donné en paramètre
	 */
	@Override
	public ArrayList<Site> getSite(Topo pTopo) {
		ArrayList<Site> vSite = new ArrayList<Site>();
		
		if (siteDAO.find(pTopo.getId()) != null) {
			vSite = siteDAO.find(pTopo.getId());
		}
		else
			try {
				throw new Exception ("Le topo n'a aucun site.");
			}catch (Exception e) {
				e.printStackTrace();
			}
		return vSite;
	}

}
