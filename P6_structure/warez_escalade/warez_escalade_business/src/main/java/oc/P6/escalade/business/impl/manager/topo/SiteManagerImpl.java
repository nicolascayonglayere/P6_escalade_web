package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
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
	private DAOFactory daoFactory;
	
	private SiteManagerDAO siteDAO;
	
	@Inject
	@Named("platformTransactionManager")
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
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		if (siteDAO.find(pNom, pTopo.getId()) != null) {
			try {
				site = siteDAO.find(pNom, pTopo.getId());
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
		}
		else {
			try {
				throw new Exception("Le site n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Site) site;
	}

	/**
	 * Méthode pour créer le {@link Site} donné en paramètre
	 */
	@Override
	public void creerSite(Site pSite) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
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
			try {
				site.setNomSite(pSite.getNomSite());
				site.setDescription(pSite.getDescription());
				site.setTopo(pSite.getTopo());
				siteDAO.create((Site)site);
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
		}
		
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} du {@link Topo} donné en paramètre
	 */
	@Override
	public ArrayList<Site> getSite(Topo pTopo) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		ArrayList<Site> vSite = new ArrayList<Site>();
		
		if (siteDAO.find(pTopo.getId()) != null) {
			try {
				vSite = siteDAO.find(pTopo.getId());
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
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
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
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
			try {
				site.setId(siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()).getId());
				site.setNomSite(pSite.getNomSite());
				site.setDescription(pSite.getDescription());
				site.setTopo(pSite.getTopo());
				siteDAO.update((Site)site);
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
		}		
		
	}

	@Override
	public Site getSite(int pId) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		if (siteDAO.get(pId) != null) {
			try {
				site = siteDAO.get(pId);
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
		}
		else {
			try {
				throw new Exception("Le site n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Site) site;
	}

	@Override
	public void supprimmerSite(Site pSite) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
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
			try {
				site.setId(siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()).getId());
				site.setNomSite(pSite.getNomSite());
				site.setDescription(pSite.getDescription());
				site.setTopo(pSite.getTopo());
				siteDAO.delete((Site)site);
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
		}
		
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public IntSite getSite() {
		return site;
	}

	public void setSite(IntSite site) {
		this.site = site;
	}

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

}
