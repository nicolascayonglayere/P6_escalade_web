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
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
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

    private UtilisateurDaoImpl userDAO; 
    
    @Inject
    @Named("platformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    /**
     * Méthode retournant {@link Utilisateur} dont le pseudo est donné en paramètre
     */
    @Override
    public Utilisateur getUtilisateur(String pPseudo) {//throws NotFoundException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
    	//--chercher l'utilisateur ds la bdd
    	userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    	TopoEmpruntDao topoEmpDAO = (TopoEmpruntDaoImpl)daoFactory.getTopoEmpruntDao();
    	if(userDAO.find(pPseudo) != null) {
    		try {
            	LOGGER.debug(userDAO.find(pPseudo).getPseudo());
            	System.out.println(userDAO.find(pPseudo).getPseudo());
    	    	
    	    	utilisateur = userDAO.find(pPseudo);
    	    	utilisateur.setListTopoEmprunt(topoEmpDAO.getListTopoEmprunt(utilisateur.getId()));
    	    	
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
    		}finally {
				if (vTransactionStatus != null) 
					platformTransactionManager.rollback(vTransactionStatus); 			
    		}
    	}
    	else {
    		utilisateur.setNom(null); 
    		//utilisateur.setPseudo(pPseudo);
			try {
				throw new Exception("Utilisateur non trouvé : PSEUDO=" + pPseudo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//System.out.println("CTRL "+utilisateur.getPseudo()+" - "+utilisateur.getPassword()+" - "+utilisateur.getId());
    	return (Utilisateur)utilisateur;
        
        
    }
	/**
	 * Méthode pour créer un {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void creerUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
  		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		if (userDAO.find(pUtilisateur.getNom())!= null) {
			System.out.println("trace-");
			try {
				throw new Exception("Le pseudo est deja utilisé. Choisissez un autre pseudo.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("trace+");
			try {

				pUtilisateur.setId_Role(3);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.create(pUtilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
			    }
			}

		}
			
	}

	/**
	 * Méthode pour obtenir la liste des administrateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListAdmin() {
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
		    }
		}

	}
	/**
	 * Méthode pour obtenir la liste des modérateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListModo() {
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
		    }
		}
	}
	
	/**
	 * Méthode pour modifier {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void modifierUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		if (userDAO.find(pUtilisateur.getPseudo())!= null && userDAO.find(pUtilisateur.getPseudo()).getPassword() != pUtilisateur.getPassword()) {
			System.out.println("trace-");
			try {
				throw new Exception("Le pseudo est deja utilisé. Choisissez un autre pseudo.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("trace+");
			try {

				utilisateur.setId_Role(pUtilisateur.getId_Role());//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update(pUtilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
			    }
			}

		}
	}
	/**
	 * Méthode pour supprimmer {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void deleteUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		if (userDAO.find(pUtilisateur.getPseudo())== null) {
			System.out.println("trace-");
			try {
				throw new Exception("L'utilisateur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("trace+");
			try {
				userDAO.delete(pUtilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
			    }
			}

		}
		
	}
	
	/**
	 * Méthode pour obtenir la liste des utilisateurs à partir du pseudo (une partie pour une recherche)
	 */
	@Override
	public ArrayList<Utilisateur> getListUtilisateur(String pPseudo) {
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
        	//utilisateur.setNom(userDAO.findPass(pPassword, pPseudo).getNom());
        	//utilisateur.setPrenom(userDAO.findPass(pPassword, pPseudo).getPrenom());
    	    //utilisateur.setPseudo(pPseudo);
    		//utilisateur.setPassword(pPassword);
    		//utilisateur.setId_Role(userDAO.findPass(pPassword, pPseudo).getId_Role());
    		//utilisateur.setRole(userDAO.findPass(pPassword, pPseudo).getRole());
    		//utilisateur.setId(userDAO.findPass(pPassword, pPseudo).getId());
    			
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
	 */
	@Override
	public void banUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
         
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
			try {
				pUtilisateur.setId_Role(4);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update(pUtilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
			    }
			}

	}
	
	/**
	 * Méthode pour modifier le mot de passe de {@link Utilisateur} donné en paramètre
	 * Cryptage
	 */
	@Override
	public void modifierPassUtilisateur(Utilisateur pUtilisateur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
         
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		System.out.println("CTRL "+pUtilisateur.getPseudo()+" - "+userDAO.find(pUtilisateur.getPseudo()).getId()+" - "+userDAO.find(pUtilisateur.getPseudo()).getId_Role());
		if (userDAO.find(pUtilisateur.getPseudo()) == null ) {
			System.out.println("trace-");
			try {
				throw new Exception("Le pseudo n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("trace+");
			try {
				utilisateur.setId(userDAO.find(pUtilisateur.getPseudo()).getId());
				utilisateur.setNom(pUtilisateur.getNom());
				utilisateur.setPrenom(pUtilisateur.getPrenom());
				utilisateur.setPseudo(pUtilisateur.getPseudo());
				utilisateur.setPassword(pUtilisateur.getPassword());
				//utilisateur.setCoordonnee(pUtilisateur.getCoordonnee());
				utilisateur.setId_Role(userDAO.find(pUtilisateur.getPseudo()).getId_Role());//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update((Utilisateur)utilisateur);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
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

}
