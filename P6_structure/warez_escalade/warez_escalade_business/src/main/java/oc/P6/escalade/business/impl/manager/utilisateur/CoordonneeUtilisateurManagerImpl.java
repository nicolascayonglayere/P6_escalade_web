package oc.P6.escalade.business.impl.manager.utilisateur;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.utilisateur.CoordonneeUtilisateurManager;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntUtilisateur;
/**
 * Implémentation de {@link CoordonneeUtilisateurManager}
 * @author nicolas
 *
 */
@Named
public class CoordonneeUtilisateurManagerImpl extends AbstractDAOManager implements CoordonneeUtilisateurManager{

	@Inject
	private IntCoordonneeUtilisateur coordonnee;
	@Inject
	private IntUtilisateur utilisateur;
    @Inject
    private DAOFactoryImpl daoFactory;
	
	private CoordonneeUtilisateurDaoImpl coordonneeDao;
	
	private UtilisateurDaoImpl userDAO;
    @Inject
    @Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir les {@link CoordonneeUtilisateur} de {@link Utilisateur} dont l'id est donné en paramètre
	 * @throws UtilisateurException 
	 */
	@Override
	public CoordonneeUtilisateur getCoordonnee(int pId) throws CoordonneeUtilisateurException, UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);

        userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		if(userDAO.find(pId) != null) {
			try {
				coordonnee = coordonneeDao.find(userDAO.find(pId));
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			}finally {
				if (vTransactionStatus != null) { 
					platformTransactionManager.rollback(vTransactionStatus);
					throw new CoordonneeUtilisateurException("Coordonnee non trouvé : id=" + coordonneeDao.find(userDAO.find(pId)).getId());
				} 			
    		}
		}
		
    	else {
			throw new UtilisateurException("Utilisateur inconnu");
    	}
     	System.out.println("CTRL "+coordonnee.getId()+" - "+coordonnee.getEmail());
    	return (CoordonneeUtilisateur) coordonnee;
	}

	/**
	 * Méthode pour créer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) throws CoordonneeUtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
 
		System.out.println("CTRL coord "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		try {
			coordonneeDao.create(pCoordinneeUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new CoordonneeUtilisateurException("L'email existe deja.");
			}
		}
		
	}
	
	/**
	 * Méthode pour modifier {@link CoordonneeUtilisateur} donnée en paramètre
	 * @throws CoordonneeUtilisateurException 
	 */
	@Override
	public CoordonneeUtilisateur modifier (CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
   
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
        System.out.println("CTRL coord "+pCoordonneeUtilisateur.getEmail()+" - "+pCoordonneeUtilisateur.getId());
		try {
			coordonnee = coordonneeDao.update(pCoordonneeUtilisateur);
			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			platformTransactionManager.commit(vTScommit);
		}catch (CoordonneeUtilisateurException e) {
			e.printStackTrace();
			throw new CoordonneeUtilisateurException(e.getMessage());
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
			}
		}
		
		return (CoordonneeUtilisateur) coordonnee;
	}
	
	/**
	 * Méthode pour supprimer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
  
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		System.out.println("CTRL coord "+pCoordonneeUtilisateur.getEmail()+" - "+pCoordonneeUtilisateur.getUtilisateur().getId());
		try {
			pCoordonneeUtilisateur.setId(coordonneeDao.find(pCoordonneeUtilisateur.getUtilisateur()).getId());
			coordonneeDao.delete(pCoordonneeUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new CoordonneeUtilisateurException("Coordonnee non trouvé : id=" + pCoordonneeUtilisateur.getId());
			}
		}
		
	}

	public IntUtilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(IntUtilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public DAOFactoryImpl getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactoryImpl daoFactory) {
		this.daoFactory = daoFactory;
	}

	public IntCoordonneeUtilisateur getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(IntCoordonneeUtilisateur coordonnee) {
		this.coordonnee = coordonnee;
	}

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}



}
