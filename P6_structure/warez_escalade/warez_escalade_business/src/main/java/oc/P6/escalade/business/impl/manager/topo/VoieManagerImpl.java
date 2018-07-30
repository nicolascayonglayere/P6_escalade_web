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
import oc.P6.escalade.business.impl.manager.TopoEmpruntManagerImpl;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.topo.IntVoie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implémentation de {@link VoieManager}
 * @author nicolas
 *
 */
@Named
public class VoieManagerImpl extends AbstractDAOManager implements VoieManager{

	static final Logger logger = LogManager.getLogger();
	@Inject
	private IntVoie voie;
	@Inject
	private DAOFactory daoFactory;
	
	private VoieManagerDao voieDao;
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	@Inject
	private TopoEmpruntManagerImpl topoEmpruntManagerImpl;
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donné en paramètre
	 * @throws VoieException 
	 */
	@Override
	public ArrayList<Voie> getListVoie(Secteur pSecteur) throws VoieException {
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
		}catch(VoieException e){
			logger.debug(e.getMessage());
			throw new VoieException("Aucune voie pour le secteur : "+pSecteur.getNomSecteur());
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
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
		logger.debug("CTRL "+pVoie.getNomVoie());
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
		logger.debug("CTRL "+pVoie.getNomVoie());

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
		logger.debug("CTRL "+pVoie.getNomVoie());

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

	@Override
	public ArrayList<Voie> rechercheMultiVoie(String pNom, String pDiffMin, String pDiffMax) throws VoieException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        voieDao = daoFactory.getVoieManagerDao();
		ArrayList<Voie>listVoie = new ArrayList<Voie>(); 
		ArrayList<Voie> listVoieTopo = new ArrayList<Voie>();
		try {
			listVoie = voieDao.rechercheMultiVoie(pNom, pDiffMin, pDiffMax);
			logger.debug("ctrl business multi 1 "+listVoie.size());
			if(listVoie.size() == 0)
				throw new VoieException("Aucun résultat pour la voie de nom commençant par "+pNom);
			else {
				for (Voie v : listVoie) {
					logger.debug("ctrl business multi "+v.getId());
					if(topoEmpruntManagerImpl.getNbExemplaire(v.getSecteur().getSite().getTopo()) == 0) {
						throw new VoieException("Il n'y a plus d'exemplaire disponible pour le topo : "+v.getSecteur().getSite().getTopo().getNomTopo());
					}
					listVoieTopo.add(v);
					v.getSecteur().getSite().getTopo().setListVoie(listVoieTopo);
				}				
			}

		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listVoie;
	}
	
	//--Getter et Setter--//
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}




}
