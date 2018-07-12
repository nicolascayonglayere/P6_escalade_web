package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SecteurManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.topo.IntSecteur;

/**
 * Implémentation de {@link SecteurManager}
 * @author nicolas
 *
 */
@Named
public class SecteurManagerImpl extends AbstractDAOManager implements SecteurManager{

	@Inject
	private IntSecteur secteur;
	
	@Inject
	private DAOFactory daoFactory;
	
	private SecteurManagerDao secteurDAO;
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Secteur} du {@link Site } donné en paramètre
	 */
	@Override
	public ArrayList<Secteur> getListSecteur(Site pSite) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
		try {
			listSecteur = secteurDAO.getListeSecteur(pSite);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listSecteur;
	}

	/**
	 * Méthode pour obtenir le {@link Secteur} nommé pNom du {@link Site} donné en paramètre
	 */
	@Override
	public Secteur getSecteur(String pNom, Site pSite) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		if (secteurDAO.find(pNom, pSite.getId()) != null) {
			try {
				secteur = secteurDAO.find(pNom, pSite.getId());
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
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Secteur) secteur;
	}
	
	@Override
	public Secteur getSecteur(int pId) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		if (secteurDAO.find(pId) != null) {
			try {
				secteur = secteurDAO.find(pId);
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
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Secteur) secteur;
	}

	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre
	 */
	@Override
	public void creerSecteur(Secteur pSecteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		System.out.println("CTRL "+pSecteur.getNomSecteur()+" - "+pSecteur.getSite().getId());
		if (secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()) != null) {
			try {
				throw new Exception("Le secteur existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				secteurDAO.create(pSecteur);
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
	public void modifierSecteur(Secteur pSecteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		System.out.println("CTRL "+pSecteur.getNomSecteur());
		if (secteurDAO.find(pSecteur.getId()) == null) {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				secteurDAO.update(pSecteur);
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
	 * Méthode pour supprimer le {@link Secteur} donné en paramètre
	 */
	@Override
	public void supprimerSecteur(Secteur pSecteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		secteurDAO = daoFactory.getSecteurManagerDao();
		System.out.println("CTRL "+pSecteur.getNomSecteur());
		if (secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()) == null) {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				pSecteur.setId(secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()).getId());
				for (Voie v : daoFactory.getVoieManagerDao().getlistVoie(pSecteur)) {
					daoFactory.getVoieManagerDao().delete(v);
				}
				secteurDAO.delete(pSecteur);
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

	public IntSecteur getSecteur() {
		return secteur;
	}

	public void setSecteur(IntSecteur secteur) {
		this.secteur = secteur;
	}

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}



}
