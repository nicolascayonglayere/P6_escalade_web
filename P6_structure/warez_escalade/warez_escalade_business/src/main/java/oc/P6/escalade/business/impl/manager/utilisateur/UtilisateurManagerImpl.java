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
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.RoleUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;


@Named
public class UtilisateurManagerImpl extends AbstractDAOManager implements UtilisateurManager   {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(UtilisateurManagerImpl.class);

 
    
    //@Inject
   // @Named("refListUtilisateur")
   //private List<Utilisateur> listUtilisateur;
    @Inject
    //@Named("refUtilisateur")
    private Utilisateur utilisateur;

   // private Optional<Utilisateur> searchUtilisateur(String pPseudo) {
     //   return this.listUtilisateur.stream().filter(u -> StringUtils.equals(u.getPseudo(), pPseudo)).findFirst();
    //}
    @Inject
    //@Named("utilisateurDAO")
    private UtilisateurDaoImpl userDAO;// = (UtilisateurDaoImpl) getDAOFactory().getUtilisateurManagerDAO();
    
    //@Inject
    //@Named("platformTransactionManager")
 // private PlatformTransactionManager platformTransactionManager;
 // 
 // public void setPlatformTransactionManager(PlatformTransactionManager transactionManager) {
 //     this.platformTransactionManager = transactionManager;
 //  }
    
    @Override
    public Utilisateur getUtilisateur(String pPseudo) {//throws NotFoundException {
    	//--chercher l'utilisateur ds la bdd

    	if(userDAO.find(pPseudo) != null) { 
        	LOGGER.debug(userDAO.find(pPseudo).getPseudo());
        	System.out.println(userDAO.find(pPseudo).getPseudo());
        		utilisateur.setNom(userDAO.find(pPseudo).getNom());
        		utilisateur.setPrenom(userDAO.find(pPseudo).getPrenom());
    	        utilisateur.setPseudo(pPseudo);
    			utilisateur.setPassword(userDAO.find(pPseudo).getPassword());
    			utilisateur.setId_role(userDAO.find(pPseudo).getId_role());
    			utilisateur.setRole(userDAO.find(pPseudo).getRole());
    			utilisateur.setId(userDAO.find(pPseudo).getId());
        
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

	@Override
	public void creerUtilisateur(Utilisateur pUtilisateur) {
		//TransactionDefinition vDefinition = new DefaultTransactionDefinition();
		//vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		//vDefinition.setTimeout(30); // 30 secondes
        //TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		//TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		System.out.println("CTRL "+pUtilisateur.getPseudo());
		if (userDAO.find(pUtilisateur.getPseudo())!= null) {
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
				utilisateur.setId_role(3);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.create(pUtilisateur);
				
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

	@Override
	public ArrayList<Utilisateur> getListAdmin() {
		ArrayList<Utilisateur> listAdmin = userDAO.getList(1);
		return listAdmin;
	}

	@Override
	public ArrayList<Utilisateur> getListModo() {
		ArrayList<Utilisateur> listModo = userDAO.getList(2);
		return listModo;
	}

	@Override
	public void modifierUtilisateur(Utilisateur pUtilisateur) {
		System.out.println("CTRL "+pUtilisateur.getPseudo());
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
				utilisateur.setId_role(userDAO.find(pUtilisateur.getId()).getId_role());//(RoleUtilisateur.Utilisateur.getId());
				userDAO.update(pUtilisateur);
				
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

	@Override
	public void deleteUtilisateur(Utilisateur pUtilisateur) {
		System.out.println("CTRL "+pUtilisateur.getPseudo());
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
				utilisateur.setId_role(3);//(RoleUtilisateur.Utilisateur.getId());
				userDAO.delete(pUtilisateur);
				
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

	@Override
	public ArrayList<Utilisateur> getListUtilisateur(String pPseudo) {
		ArrayList<Utilisateur> listUtilisateur = userDAO.getList(pPseudo);
		return listUtilisateur;
	}

	@Override
	public Utilisateur getUtilisateurPass(String pPassword) {
    	//--chercher l'utilisateur ds la bdd

    	if(userDAO.findPass(pPassword) != null) { 
        	System.out.println(userDAO.findPass(pPassword).getPseudo());
        		utilisateur.setNom(userDAO.findPass(pPassword).getNom());
        		utilisateur.setPrenom(userDAO.findPass(pPassword).getPrenom());
    	        utilisateur.setPseudo(userDAO.findPass(pPassword).getPseudo());
    			utilisateur.setPassword(pPassword);
    			utilisateur.setId_role(userDAO.findPass(pPassword).getId_role());
    			utilisateur.setRole(userDAO.findPass(pPassword).getRole());
    			utilisateur.setId(userDAO.findPass(pPassword).getId());
        
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
