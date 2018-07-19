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
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.VoieException;
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
	 * @throws SecteurException 
	 */
	@Override
	public ArrayList<Voie> getListVoie(Secteur pSecteur) throws SecteurException {
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
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new SecteurException("Le secteur n'existe pas. "+pSecteur.getNomSecteur());
			} 			
		}

		return listVoie;
	}

	/**
	 * Méthode pour créer la {@link Voie} donnée en paramètre
	 * @throws VoieException 
	 */
	@Override
	public Voie creerVoie(Voie pVoie) throws VoieException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());
		try {
			voie = voieDao.create(pVoie);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new VoieException("La voie existe deja. "+pVoie.getNomVoie());
			} 			
	   	}
		return (Voie) voie;
	}

	/**
	 * Méthode pour modifier la {@link Voie} donnée en paramètre
	 * @throws VoieException 
	 */
	@Override
	public void majVoie(Voie pVoie) throws VoieException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());

		try {
			voieDao.update(pVoie);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);				
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new VoieException("La voie existe deja. "+pVoie.getNomVoie());
			} 			
	   	}
	}

	/**
	 * Méthode pour obtenir la {@link Voie} d'id donnée en paramètre
	 */
	@Override
	public Voie getVoie(int pId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Méthode pour obtenir la {@link Voie} du {@link Secteur} avec son nom donné en paramètre
	 * @throws VoieException 
	 */
	@Override
	public Voie getVoie(String pNom, Secteur pSecteur) throws VoieException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();

		try {
		voie = voieDao.find(pNom, pSecteur.getId());
		
			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new VoieException("La voie existe deja. "+pNom);
			} 			
	    }
		
		return (Voie) voie;
	}

	/**
	 * Méthode pour supprime la {@link Voie} donnée en paramètre
	 * @throws VoieException 
	 */
	@Override
	public void supprimerVoie(Voie pVoie) throws VoieException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		voieDao = daoFactory.getVoieManagerDao();
		System.out.println("CTRL "+pVoie.getNomVoie());

		try {
		pVoie.setId(voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()).getId());
		voieDao.delete(pVoie);
		
	    TransactionStatus vTScommit = vTransactionStatus;
	    vTransactionStatus = null;
	    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new VoieException("La voie n'existe pas.");
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
