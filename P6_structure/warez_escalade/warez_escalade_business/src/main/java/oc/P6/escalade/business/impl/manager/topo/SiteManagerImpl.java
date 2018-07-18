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
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;
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
	 * @throws SiteException 
	 */
	@Override
	public Site getSite(String pNom, Topo pTopo) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();

		try {
			site = siteDAO.find(pNom, pTopo.getId());
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site n'existe pas. "+pNom);
			} 			
    	}

		return (Site) site;
	}

	/**
	 * Méthode pour créer le {@link Site} donné en paramètre
	 * @throws SiteException 
	 */
	@Override
	public void creerSite(Site pSite) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		System.out.println("CTRL "+pSite.getNomSite());

		try {
			siteDAO.create(pSite);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site existe deja. "+pSite.getNomSite());
			} 			
	   	}		
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} du {@link Topo} donné en paramètre
	 * @throws TopoException 
	 */
	@Override
	public ArrayList<Site> getSite(Topo pTopo) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		ArrayList<Site> vSite = new ArrayList<Site>();

		try {
			vSite = siteDAO.find(pTopo.getId());
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new TopoException ("Le topo n'a aucun site. "+pTopo.getNomTopo());
			} 			
		}

		return vSite;
	}

	@Override
	public void modifierSite(Site pSite) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		System.out.println("CTRL "+pSite.getNomSite());

		try {
			siteDAO.update(pSite);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site n'existe pas. "+pSite.getNomSite());
			} 			
	   	}		
		
	}

	@Override
	public Site getSite(int pId) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();

		try {
			site = siteDAO.get(pId);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site d'id "+pId+" n'existe pas.");
			} 			
	   	}

		return (Site) site;
	}

	@Override
	public void supprimmerSite(Site pSite) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		System.out.println("CTRL "+pSite.getNomSite());

		try {
			pSite.setId(siteDAO.find(pSite.getNomSite(), pSite.getTopo().getId()).getId());
			for (Secteur s : daoFactory.getSecteurManagerDao().getListeSecteur(pSite)) {
				for (Voie v : daoFactory.getVoieManagerDao().getlistVoie(s)) {
					daoFactory.getVoieManagerDao().delete(v);
				}
				daoFactory.getSecteurManagerDao().delete(s);
			}
			siteDAO.delete(pSite);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site n'existe pas. "+pSite.getNomSite());
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
