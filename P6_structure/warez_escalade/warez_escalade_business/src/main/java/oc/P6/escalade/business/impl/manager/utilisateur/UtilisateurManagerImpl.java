package oc.P6.escalade.business.impl.manager.utilisateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.utilisateur.UtilisateurManager;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.TopoEmpruntDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.RoleUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

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
    //@Inject
   // @Named("refListUtilisateur")
   //private List<Utilisateur> listUtilisateur;
    @Inject
    //@Named("refUtilisateur")
    private Utilisateur utilisateur;

   // private Optional<Utilisateur> searchUtilisateur(String pPseudo) {
     //   return this.listUtilisateur.stream().filter(u -> StringUtils.equals(u.getPseudo(), pPseudo)).findFirst();
    //}
    //@Inject
    //@Named("utilisateurDAO")
    private UtilisateurDaoImpl userDAO; 
    
    @Inject
    private TopoEmpruntDaoImpl topoEmpDAO;
    //@Inject
    //@Named("platformTransactionManager")
 // private PlatformTransactionManager platformTransactionManager;
 // 
 // public void setPlatformTransactionManager(PlatformTransactionManager transactionManager) {
 //     this.platformTransactionManager = transactionManager;
 //  }
    /**
     * Méthode retournant {@link Utilisateur} dont le pseudo est donné en paramètre
     */
    @Override
    public Utilisateur getUtilisateur(String pPseudo) {//throws NotFoundException {
    	//--chercher l'utilisateur ds la bdd
    	userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    	if(userDAO.find(pPseudo) != null) { 
        	LOGGER.debug(userDAO.find(pPseudo).getPseudo());
        	System.out.println(userDAO.find(pPseudo).getPseudo());
	       	utilisateur.setNom(userDAO.find(pPseudo).getNom());
	       	utilisateur.setPrenom(userDAO.find(pPseudo).getPrenom());
	    	utilisateur.setPseudo(pPseudo);
	    	utilisateur.setPassword(userDAO.find(pPseudo).getPassword());
	    	utilisateur.setId_Role(userDAO.find(pPseudo).getId_Role());
	    	utilisateur.setRole(userDAO.find(pPseudo).getRole());
	    	utilisateur.setId(userDAO.find(pPseudo).getId());
	    	
	    	utilisateur.setListTopoEmprunt(topoEmpDAO.getListTopoEmprunt(userDAO.find(pPseudo).getId()));
        
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
    	return utilisateur;
        
        
    }
	/**
	 * Méthode pour créer un {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void creerUtilisateur(Utilisateur pUtilisateur) {
		//TransactionDefinition vDefinition = new DefaultTransactionDefinition();
		//vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		//vDefinition.setTimeout(30); // 30 secondes
        //TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		//TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
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
				//utilisateur.setId(id);
				utilisateur.setNom(pUtilisateur.getNom());
				utilisateur.setPrenom(pUtilisateur.getPrenom());
				utilisateur.setPseudo(pUtilisateur.getPseudo());
				utilisateur.setPassword(pUtilisateur.getPassword());
				//utilisateur.setCoordonnee(pUtilisateur.getCoordonnee());
				utilisateur.setId_Role(3);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.create(utilisateur);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}

		}
			
	}

	/**
	 * Méthode pour obtenir la liste des administrateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListAdmin() {
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		ArrayList<Utilisateur> listAdmin = userDAO.getList(1);
		return listAdmin;
	}
	/**
	 * Méthode pour obtenir la liste des modérateurs
	 */
	@Override
	public ArrayList<Utilisateur> getListModo() {
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		ArrayList<Utilisateur> listModo = userDAO.getList(2);
		return listModo;
	}
	
	/**
	 * Méthode pour modifier {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void modifierUtilisateur(Utilisateur pUtilisateur) {
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
				utilisateur.setId(pUtilisateur.getId());
				utilisateur.setNom(pUtilisateur.getNom());
				utilisateur.setPrenom(pUtilisateur.getPrenom());
				utilisateur.setPseudo(pUtilisateur.getPseudo());
				utilisateur.setPassword(pUtilisateur.getPassword());
				//utilisateur.setCoordonnee(pUtilisateur.getCoordonnee());
				utilisateur.setId_Role(pUtilisateur.getId_Role());//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update(utilisateur);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}

		}
	}
	/**
	 * Méthode pour supprimmer {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void deleteUtilisateur(Utilisateur pUtilisateur) {
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
				utilisateur.setId(pUtilisateur.getId());
				utilisateur.setNom(pUtilisateur.getNom());
				utilisateur.setPrenom(pUtilisateur.getPrenom());
				utilisateur.setPseudo(pUtilisateur.getPseudo());
				utilisateur.setPassword(pUtilisateur.getPassword());
				//utilisateur.setCoordonnee(pUtilisateur.getCoordonnee());
				utilisateur.setId_Role(3);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.delete(utilisateur);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}

		}
		
	}
	
	/**
	 * Méthode pour obtenir la liste des utilisateurs à partir du pseudo (une partie pour une recherche)
	 */
	@Override
	public ArrayList<Utilisateur> getListUtilisateur(String pPseudo) {
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
		ArrayList<Utilisateur> listUtilisateur = userDAO.getList(pPseudo);
		for (Utilisateur u : listUtilisateur) {
			System.out.println(userDAO.find(u.getPseudo()).getId());
			u.setListTopoEmprunt(topoEmpDAO.getListTopoEmprunt(userDAO.find(u.getPseudo()).getId()));
		}
		return listUtilisateur;
	}

	
	/**
	 * Méthode pour obtenir {@link Utilisateur} à partir de son pseudo et son mot de passe
	 */
	@Override
	public Utilisateur getUtilisateurPass(String pPassword, String pPseudo) {
    	//--chercher l'utilisateur ds la bdd
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
    	if(userDAO.findPass(pPassword, pPseudo) != null) { 
        	System.out.println(userDAO.findPass(pPassword, pPseudo).getPseudo());
        		utilisateur.setNom(userDAO.findPass(pPassword, pPseudo).getNom());
        		utilisateur.setPrenom(userDAO.findPass(pPassword, pPseudo).getPrenom());
    	        utilisateur.setPseudo(pPseudo);
    			utilisateur.setPassword(pPassword);
    			utilisateur.setId_Role(userDAO.findPass(pPassword, pPseudo).getId_Role());
    			utilisateur.setRole(userDAO.findPass(pPassword, pPseudo).getRole());
    			utilisateur.setId(userDAO.findPass(pPassword, pPseudo).getId());
        
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
    	return utilisateur;
	}

	/**
	 * Méthode pour ban {@link Utilisateur} donné en paramètre	
	 */
	@Override
	public void banUtilisateur(Utilisateur pUtilisateur) {
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		userDAO = (UtilisateurDaoImpl) daoFactory.getUtilisateurManagerDAO();
			try {
				utilisateur.setId(pUtilisateur.getId());
				utilisateur.setNom(pUtilisateur.getNom());
				utilisateur.setPrenom(pUtilisateur.getPrenom());
				utilisateur.setPseudo(pUtilisateur.getPseudo());
				utilisateur.setPassword(pUtilisateur.getPassword());
				//utilisateur.setCoordonnee(pUtilisateur.getCoordonnee());
				utilisateur.setId_Role(4);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update(utilisateur);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}

		
		
	}
	
	/**
	 * Méthode pour modifier le mot de passe de {@link Utilisateur} donné en paramètre
	 * Cryptage
	 */
	@Override
	public void modifierPassUtilisateur(Utilisateur pUtilisateur) {
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
				userDAO.update(utilisateur);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}

		}
		
	}
	public DAOFactoryImpl getDaoFactory() {
		return daoFactory;
	}
	public void setDaoFactory(DAOFactoryImpl daoFactory) {
		this.daoFactory = daoFactory;
	}



  // @Override
  // public void addUtilisateur(Utilisateur pUtilisateur) throws FunctionalException {
  //     if (pUtilisateur == null) {
  //         throw new FunctionalException("L'objet Utilisateur ne doit pas être null !");
  //     }
  //
  //     // Validation du bean pChannel
  //     Set<ConstraintViolation<Utilisateur>> vViolations = getConstraintValidator().validate(pUtilisateur);
  //     if (!vViolations.isEmpty()) {
  //         throw new FunctionalException("L'objet Utilisateur est invalide",
  //                                       new ConstraintViolationException(vViolations));
  //     }
  //
  //     // Vérification qu'un Utilisateur de même pseudo n'existe pas déjà
  //     if (searchUtilisateur(pUtilisateur.getPseudo()).isPresent()) {
  //         throw new FunctionalException("Le pseudo est déjà utilisé !");
  //     }
  //
  //     this.listUtilisateur.add(pUtilisateur);
  // }


    //@Override
   // public void deleteUtilisateur(Utilisateur pUtilisateur) {
     //   this.listUtilisateur.remove(pUtilisateur);
    //}


    //@Override
    //public List<Utilisateur> getListUtilisateur() {
      //  return Collections.unmodifiableList(listUtilisateur);
    //}
}
