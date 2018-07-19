package oc.P6.escalade.business.impl.manager.utilisateur;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.utilisateur.UtilisateurManager;
import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.TopoEmpruntDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntUtilisateur;

/**
 * Classe UtilisateurManger implémentation de {@link UtilisateurManager}
 * @author nicolas
 *
 */
@Named("utilisateurManager")
public class UtilisateurManagerImpl extends AbstractDAOManager implements UtilisateurManager   {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(UtilisateurManagerImpl.class);

    @Inject
    private DAOFactoryImpl daoFactory;

    @Inject
    private IntUtilisateur utilisateur;
    @Inject
    private IntCoordonneeUtilisateur coordonneeUtilisateur;
    
    private UtilisateurDaoImpl userDAO; 
    private CoordonneeUtilisateurDaoImpl coordonneeDAO;
    @Inject
    @Named("platformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    /**
     * Méthode retournant {@link Utilisateur} dont le pseudo est donné en paramètre
     */
    @Override
    public Utilisateur getUtilisateur(String pPseudo) throws UtilisateurException{//throws NotFoundException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
    	//--chercher l'utilisateur ds la bdd
    	userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    	TopoEmpruntDao topoEmpDAO = (TopoEmpruntDaoImpl)daoFactory.getTopoEmpruntDao();

	   	try {
	        LOGGER.debug(userDAO.find(pPseudo).getPseudo());
	        System.out.println(userDAO.find(pPseudo).getPseudo());
	       	
	       	utilisateur = userDAO.find(pPseudo);
	       	utilisateur.setListTopoEmprunt(topoEmpDAO.getListTopoEmprunt(utilisateur.getId()));
	       	
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
	   	}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("Utilisateur non trouvé : PSEUDO=" + pPseudo);
			}
	   	}

    	//System.out.println("CTRL "+utilisateur.getPseudo()+" - "+utilisateur.getPassword()+" - "+utilisateur.getId());
    	return (Utilisateur)utilisateur;
        
        
    }
	/**
	 * Méthode pour créer un {@link Utilisateur} donné en paramètre
	 */
	@Override
	public Utilisateur creerUtilisateur(Utilisateur pUtilisateur) throws UtilisateurException, CoordonneeUtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
  		System.out.println("CTRL "+pUtilisateur.getPseudo());
  		
		try {
			userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
			coordonneeDAO = (CoordonneeUtilisateurDaoImpl) daoFactory.getCoordonneeUtilisateurDao();
			pUtilisateur.setId_Role(3);
			utilisateur = userDAO.create(pUtilisateur); 
			if(utilisateur.getId() != 0) {
				coordonneeUtilisateur = pUtilisateur.getCoordonnee();
				coordonneeUtilisateur.setUtilisateur((Utilisateur) utilisateur);
				if ((utilisateur.setCoordonnee(coordonneeDAO.create((CoordonneeUtilisateur) coordonneeUtilisateur)))) != null) {
				    TransactionStatus vTScommit = vTransactionStatus;
				    vTransactionStatus = null;
				    platformTransactionManager.commit(vTScommit);				
				}
				else {	
					throw new CoordonneeUtilisateurException("L'email existe deja "+pUtilisateur.getCoordonnee().getEmail()+".");
				}
			}
			else
				throw new UtilisateurException("L'utilisateur existe deja "+pUtilisateur.getPseudo()+".");
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				
		    }
		}
		return (Utilisateur) utilisateur;			
	}

	/**
	 * Méthode pour obtenir la liste des administrateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListAdmin() throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
        try {
    		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    		ArrayList<Utilisateur> listAdmin = userDAO.getList(1);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		    
			return listAdmin;
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("Aucun administrateur répertorié.");
		    }
		}

	}
	/**
	 * Méthode pour obtenir la liste des modérateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListModo() throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
        try {
    		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    		ArrayList<Utilisateur> listModo = userDAO.getList(2);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		    
			return listModo;
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("Aucun modérateur répertorié");
		    }
		}
	}
	
	/**
	 * Méthode pour modifier {@link Utilisateur} donné en paramètre
	 */
	@Override
	public Utilisateur modifierPseudoUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();

		try {
			
			utilisateur = userDAO.updatePseudo(pUtilisateur);

		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				try {
					throw new Exception("Erreur lors de la modification : mot de passe ou email déjà existant.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}

		return (Utilisateur) utilisateur;
	}
	/**
	 * Méthode pour supprimmer {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void deleteUtilisateur(Utilisateur pUtilisateur) throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
			try {
				userDAO.delete(pUtilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
					throw new UtilisateurException("L'utilisateur n'existe pas : "+pUtilisateur.getPseudo()+".");
			    }
			}
	}
	
	/**
	 * Méthode pour obtenir la liste des utilisateurs à partir du pseudo (une partie pour une recherche)
	 */
	@Override
	public ArrayList<Utilisateur> getListUtilisateur(String pPseudo) throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
        try {
    		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    		TopoEmpruntDao topoEmpDAO = (TopoEmpruntDaoImpl)daoFactory.getTopoEmpruntDao();
    		ArrayList<Utilisateur> listUtilisateur = userDAO.getList(pPseudo);
    		for (Utilisateur u : listUtilisateur) {
    			System.out.println(userDAO.find(u.getPseudo()).getId());
    			u.setListTopoEmprunt(topoEmpDAO.getListTopoEmprunt(userDAO.find(u.getPseudo()).getId()));
    		}			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		    
			return listUtilisateur;
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("Aucun utilisateur trouvé : "+pPseudo+".");
		    }
		}
	}

	
	/**
	 * Méthode pour obtenir {@link Utilisateur} à partir de son pseudo et son mot de passe
	 */
	@Override
	public Utilisateur getUtilisateurPass(String pPassword, String pPseudo) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
     	//--chercher l'utilisateur ds la bdd
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    	if(userDAO.findPass(pPassword, pPseudo) != null) { 
        	System.out.println(userDAO.findPass(pPassword, pPseudo).getPseudo());
        	try {
    			
    			utilisateur = userDAO.findPass(pPassword, pPseudo);
    			utilisateur.setPassword(pPassword);
    		    TransactionStatus vTScommit = vTransactionStatus;
    		    vTransactionStatus = null;
    		    platformTransactionManager.commit(vTScommit);
    		    
        	} finally {
    			if (vTransactionStatus != null) {
    				platformTransactionManager.rollback(vTransactionStatus);
    		    }
    		}
    	} 
    	else {
    		utilisateur.setNom(null); 
    		//utilisateur.setPseudo(pPseudo);
			try {
				throw new Exception("Utilisateur non trouvé : PSEUDO=" + utilisateur.getPseudo());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//System.out.println("CTRL "+utilisateur.getPseudo()+" - "+utilisateur.getPassword()+" - "+utilisateur.getId());
    	return (Utilisateur)utilisateur;
	}

	/**
	 * Méthode pour ban {@link Utilisateur} donné en paramètre	
	 * @throws UtilisateurException 
	 */
	@Override
	public void banUtilisateur(Utilisateur pUtilisateur) throws UtilisateurException  {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
         
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		try {
			pUtilisateur.setId_Role(4);//(RoleUtilisateur.Utilisateur.getId());
			userDAO.updateRole(pUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("L'utilisateur "+pUtilisateur.getPseudo()+" n'existe pas.");
			//try {
			//	throw new Exception("L'utilisateur n'existe pas.");
			//} catch (Exception e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		    }
		}

	}
	
	/**
	 * Méthode pour modifier le mot de passe de {@link Utilisateur} donné en paramètre
	 * Cryptage
	 */
	@Override
	public Utilisateur modifierPassUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
         
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		System.out.println("CTRL "+pUtilisateur.getPseudo()+" - "+userDAO.find(pUtilisateur.getPseudo()).getId()+" - "+userDAO.find(pUtilisateur.getPseudo()).getId_Role());

		try {
			utilisateur = userDAO.updatePass(pUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				try {
					throw new Exception("L'utilisateur n'existe pas.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
	
		return (Utilisateur) utilisateur;
		
	}
	/**
	 * Méthode qui modifie le role de {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void modifierRoleUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		try {
			userDAO.updateRole(pUtilisateur);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				try {
					throw new Exception("L'utilisateur n'existe pas.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
	}
	
	
	public DAOFactoryImpl getDaoFactory() {
		return daoFactory;
	}
	public void setDaoFactory(DAOFactoryImpl daoFactory) {
		this.daoFactory = daoFactory;
	}
	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}
	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	public IntCoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}
	public void setCoordonneeUtilisateur(IntCoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}


}
