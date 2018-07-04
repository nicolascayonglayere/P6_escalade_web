package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.VoieManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.topo.IntVoie;

/**
 * Implémentation de {@link VoieManager}
 * @author nicolas
 *
 */
@Named
public class VoieManagerImpl extends AbstractDAOManager implements VoieManager{

	@Inject
	private IntVoie voie;
	@Inject
	private DAOFactory daoFactory;
	
	private VoieManagerDao voieDao;
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donné en paramètre
	 */
	@Override
	public ArrayList<Voie> getListVoie(Secteur pSecteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		ArrayList<Voie> listVoie = new ArrayList<Voie>();
		try {
			listVoie = voieDao.getlistVoie(pSecteur);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}

		return listVoie;
	}

	/**
	 * Méthode pour créer la {@link Voie} donnée en paramètre
	 */
	@Override
	public void creerVoie(Voie pVoie) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) != null) {
			try {
				throw new Exception("La voie existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				voieDao.create(pVoie);
				
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
	 * Méthode pour modifier la {@link Voie} donnée en paramètre
	 */
	@Override
	public void majVoie(Voie pVoie) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) == null) {
			try {
				throw new Exception("La voie n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				voie.setId(voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()).getId());
				voie.setCotation(pVoie.getCotation());
				voie.setDescription(pVoie.getDescription());
				voie.setHauteur(pVoie.getHauteur());
				voie.setNbLgueur(pVoie.getNbLgueur());
				voie.setNbPoint(pVoie.getNbPoint());
				voie.setNomVoie(pVoie.getNomVoie());
				voie.setSecteur(pVoie.getSecteur());
				voieDao.update((Voie)voie);
				
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
	 * Méthode pour obtenir la {@link Voie} du {@link Secteur} avec son nom donné en paramètre
	 */
	@Override
	public Voie getVoie(String pNom, Secteur pSecteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		if (voieDao.find(pNom, pSecteur.getId()) != null) {
			try {
			voie = voieDao.find(pNom, pSecteur.getId());
			
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
		return (Voie) voie;
	}

	/**
	 * Méthode pour supprime la {@link Voie} donnée en paramètre
	 */
	@Override
	public void supprimerVoie(Voie pVoie) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) == null) {
			try {
				throw new Exception("La voie n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
			voie.setId(voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()).getId());
			voie.setCotation(pVoie.getCotation());
			voie.setDescription(pVoie.getDescription());
			voie.setHauteur(pVoie.getHauteur());
			voie.setNbLgueur(pVoie.getNbLgueur());
			voie.setNbPoint(pVoie.getNbPoint());
			voie.setNomVoie(pVoie.getNomVoie());
			voie.setSecteur(pVoie.getSecteur());
			voieDao.delete((Voie)voie);
			
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

}
