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
	 */
	@Override
	public CoordonneeUtilisateur getCoordonnee(int pId) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);

        userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		if(userDAO.find(pId) != null) {
			try {
			//coordonnee.setUtilisateur(userDAO.find(pId));
			//coordonnee.setAdresse(coordonneeDao.find(userDAO.find(pId)).getAdresse());
			//coordonnee.setEmail(coordonneeDao.find(userDAO.find(pId)).getEmail());
			//coordonnee.setIdUtilisateur(pId);
			//coordonnee.setId(coordonneeDao.find(userDAO.find(pId)).getId());
				coordonnee = coordonneeDao.find(userDAO.find(pId));
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
				throw new Exception("Coordonnee non trouvé : id=" + coordonneeDao.find(userDAO.find(pId)).getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
     	System.out.println("CTRL "+coordonnee.getId()+" - "+coordonnee.getEmail());
    	return (CoordonneeUtilisateur) coordonnee;
	}

	/**
	 * Méthode pour créer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
 
		System.out.println("CTRL coord "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		try {
			coordonnee.setEmail(pCoordinneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordinneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordinneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.create(pCoordinneeUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus);
		}
		
	}
	
	/**
	 * Méthode pour modifier {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void modifierCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
   
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
        System.out.println("CTRL coord "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setId(coordonneeDao.find(pCoordinneeUtilisateur.getUtilisateur()).getId());
			coordonnee.setEmail(pCoordinneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordinneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordinneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.update(pCoordinneeUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus);			
		}
		
	}

	/**
	 * Méthode pour supprimer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
  
        coordonneeDao = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
		System.out.println("CTRL coord "+pCoordonneeUtilisateur.getEmail()+" - "+pCoordonneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setId(coordonneeDao.find(pCoordonneeUtilisateur.getUtilisateur()).getId());
			coordonnee.setEmail(pCoordonneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordonneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordonneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.delete(pCoordonneeUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus);
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
