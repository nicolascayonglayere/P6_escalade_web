package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.SiteDaoImpl;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.contract.topo.IntSite;

/**
 * Implémentation de {@link SiteManager}
 * @author nicolas
 *
 */
@Named
public class SiteManagerImpl extends AbstractDAOManager implements SiteManager{

	@Inject
	private IntSite site;
	
	@Inject
	private SiteManagerDAO siteDAO;// = (SiteDaoImpl) getDAOFactory().getSiteManagerDao();
	
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
		System.out.println("CTRL "+pSite.getNomSite());
		if (siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()) != null) {
			try {
				throw new Exception("Le site existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			site.setNomSite(pSite.getNomSite());
			site.setDescription(pSite.getDescription());
			site.setTopo(pSite.getTopo());
			siteDAO.create((Site)site);
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

	@Override
	public void modifierSite(Site pSite) {
		System.out.println("CTRL "+pSite.getNomSite());
		if (siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()) == null) {
			try {
				throw new Exception("Le site n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			site.setId(siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()).getId());
			site.setNomSite(pSite.getNomSite());
			site.setDescription(pSite.getDescription());
			site.setTopo(pSite.getTopo());
			siteDAO.update((Site)site);
		}		
		
	}

	@Override
	public Site getSite(int pId) {
		Site site = null;
		if (siteDAO.get(pId) != null) {
			site = siteDAO.get(pId);
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

	@Override
	public void supprimmerSite(Site pSite) {
		System.out.println("CTRL "+pSite.getNomSite());
		if (siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()) == null) {
			try {
				throw new Exception("Le site n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			site.setId(siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()).getId());
			site.setNomSite(pSite.getNomSite());
			site.setDescription(pSite.getDescription());
			site.setTopo(pSite.getTopo());
			siteDAO.delete((Site)site);
		}
		
	}

}
