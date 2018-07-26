package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.VoieException;
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
	private TopoEmpruntManager topoEmpruntManagerImpl;
	
	@Inject
	private DAOFactory daoFactory;
	
	private TopoManagerDao topoDAO;
	private SiteManagerDAO siteDAO;
	private SecteurManagerDao secteurDAO;
	private VoieManagerDao voieDAO;
	
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
	public Site creerSite(Site pSite) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		siteDAO = daoFactory.getSiteManagerDao();
		System.out.println("CTRL "+pSite.getNomSite());

		try {
			site = siteDAO.create(pSite);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SiteException("Le site existe deja. "+pSite.getNomSite());
			} 			
	   	}
		return (Site) site;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} du {@link Topo} donné en paramètre
	 * @throws TopoException 
	 */
	@Override
	public ArrayList<Site> getSite(Topo pTopo) throws SiteException {
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
		    	throw new SiteException ("Aucun site pour le topo : "+pTopo.getNomTopo());
				
			} 			
		}

		return vSite;
	}
	/**
	 * Méthode pour obtenir la liste des {@link Site} de nom pNom contenant des {@link Voie} d'un intervalle de difficulté donné en paramètre
	 * @throws SiteException 
	 */
	@Override
	public ArrayList<Site> rechercheMultiSite(String pNom, String pDiffMin, String pDiffMax) throws SiteException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        siteDAO = daoFactory.getSiteManagerDao();
        secteurDAO = daoFactory.getSecteurManagerDao();
        voieDAO = daoFactory.getVoieManagerDao();
		ArrayList<Site>listSite = new ArrayList<Site>(); 
		
		try {
			listSite = siteDAO.rechercheMultiSite(pNom, pDiffMin, pDiffMax);
			System.out.println("ctrl business multi 1 "+listSite.size());
			if(listSite.size() == 0)
				throw new SiteException("Aucun résultat pour le site de nom commençant par "+pNom);
			else {
				for (Site si : listSite) {
					System.out.println("ctrl business multi "+si.getId()+" - "+si.getListVoie().size());
					if(topoEmpruntManagerImpl.getNbExemplaire(si.getTopo()) == 0) {
						throw new SiteException("Il n'y a plus d'exemplaire disponible pour le topo : "+si.getTopo().getNomTopo());
					}
				}				
			}

		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listSite;
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
			try {
				for (Secteur s : daoFactory.getSecteurManagerDao().getListeSecteur(pSite)) {
					for (Voie v : daoFactory.getVoieManagerDao().getlistVoie(s)) {
						
							daoFactory.getVoieManagerDao().delete(v);
	
					}
					daoFactory.getSecteurManagerDao().delete(s);
				}
			} catch (VoieException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecteurException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
