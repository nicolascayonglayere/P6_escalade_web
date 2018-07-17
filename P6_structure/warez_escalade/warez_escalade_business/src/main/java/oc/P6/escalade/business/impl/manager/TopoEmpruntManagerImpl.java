package oc.P6.escalade.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;
import oc.P6.escalade.model.contract.emprunt.IntTopoEmprunt;

/**
 * Implémentation de {@link TopoEmpruntManager}
 * @author nicolas
 *
 */
@Named
public class TopoEmpruntManagerImpl extends AbstractDAOManager implements TopoEmpruntManager{
 	
	@Inject
	private IntTopoEmprunt topoEmprunt;
	@Inject
	private DAOFactory daoFactory;

	private TopoEmpruntDao topoEmpruntDao;
	private TopoManagerDao topoDAO;
	private UtilisateurManagerDAO userDAO;

	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link TopoEmprunt} de {@link Utilisateur} dont l'id est donné en paramètre
	 */
	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur) throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		topoEmpruntDao = daoFactory.getTopoEmpruntDao();
		ArrayList<TopoEmprunt> listTopoEmprunt = new ArrayList<TopoEmprunt>();
		try {
			 listTopoEmprunt = topoEmpruntDao.getListTopoEmprunt(pId_utilisateur);
			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("Utilisateur inconnu");
			} 			
		}
		return listTopoEmprunt;
	}

	/**
	 * Méthode pour obtenir le {@link TopoEmprunt} nommé pNom de {@link Utilisateur} donné en paramètre
	 */
	@Override
	public TopoEmprunt getTopoEmprunt(String pNom, Utilisateur pEmprunteur) throws UtilisateurException{
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		topoEmpruntDao = daoFactory.getTopoEmpruntDao();
		topoDAO = daoFactory.getTopoManagerDao();
		System.out.println("topo : "+topoDAO.find(pNom).getNomTopo()+" - "+topoDAO.find(pNom).getId());
		Topo vTopo = topoDAO.find(pNom);
	   	try {
	   		System.out.println("topoEmp id : "+topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()).getId());
	   		topoEmprunt = topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId());
	   		System.out.println("CTRL "+topoEmprunt.getNom()+" - "+topoEmprunt.getEmprunteur().getPseudo()+" - "+topoEmprunt.getTopo().getNomTopo());
	   		TransactionStatus vTScommit = vTransactionStatus;
	   		vTransactionStatus = null;
	   		platformTransactionManager.commit(vTScommit);
	   	}finally {
	   		if (vTransactionStatus != null) { 
	   			platformTransactionManager.rollback(vTransactionStatus);
	   			topoEmprunt = null ;
	   			//throw new Exception("Cet emprunt n'existe pas : NOM=" + pNom);
	   			throw new UtilisateurException("Utilisateur inconnu : pseudo "+pEmprunteur.getPseudo());	
	   		} 			
	   	}

    	
    	return (TopoEmprunt) topoEmprunt;
	}

	/**
	 * Méthode pour créer un {@link TopoEmprunt} avec un {@link Topo} donné en paramètre pour {@link Utilisateur} donné en paramètre
	 */
	@Override
	public TopoEmprunt creerTopoEmprunt(Topo topo, Utilisateur pEmprunteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		topoEmpruntDao = daoFactory.getTopoEmpruntDao();
		topoDAO = daoFactory.getTopoManagerDao();
		userDAO = daoFactory.getUtilisateurManagerDAO();
		System.out.println(topo.getNomTopo()+" - "+pEmprunteur.getPseudo());
		

		//if (topoDAO.find(topo.getNomTopo()) != null && userDAO.find(pEmprunteur.getPseudo())!= null) {
		if(this.getNbExemplaire(topo) > 0) {
			Calendar cal = Calendar.getInstance();
			System.out.println(cal.getTime());
			topoEmprunt.setDateEmprunt(cal.getTime());
			cal.add(Calendar.DATE, 20);
			topoEmprunt.setEmprunteur(pEmprunteur);
			topoEmprunt.setTopo(topo);
			topoEmprunt.setDateRetour(cal.getTime());
			topoEmprunt.setNom(topo.getNomTopo());
			try {
				
				//ApplicationContext context = new ClassPathXmlApplicationContext("bootstrapContext.xml");
				//topoEmprunt = (IntTopoEmprunt) context.getBean("topoEmprunt");

				topoEmprunt = topoEmpruntDao.create((TopoEmprunt) topoEmprunt);
    			TransactionStatus vTScommit = vTransactionStatus;
    			vTransactionStatus = null;
    			platformTransactionManager.commit(vTScommit);
    			//((AbstractApplicationContext)context).close();
    		}finally {
    			if (vTransactionStatus != null) { 
    				platformTransactionManager.rollback(vTransactionStatus);
    				try {
    					throw new Exception("L'emprunt existe deja.");
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			} 			
    		}
		}
		else {
			topoEmprunt = null;
			try {
				throw new Exception("Il n'y a plus d'exemplaire disponible");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (TopoEmprunt) topoEmprunt;
	}

	@Override
	public void ajoutTopoEmprunt(Topo topo) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode pour rendre un {@link TopoEmprunt} donné en param pour {@link Utilisateur} donné en paramètre
	 */
	@Override
	public void retourTopoEmprunt(TopoEmprunt pTopoEmprunt, Utilisateur pEmprunteur) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		topoEmpruntDao = daoFactory.getTopoEmpruntDao();
		topoDAO = daoFactory.getTopoManagerDao();
		userDAO = daoFactory.getUtilisateurManagerDAO();
		int idTopo = topoDAO.find(pTopoEmprunt.getNom()).getId();
		int idEmprunteur = userDAO.find(pEmprunteur.getPseudo()).getId();
		Calendar cal = Calendar.getInstance();
		System.out.println(pTopoEmprunt.getNom()+" - "+pEmprunteur.getPseudo()+" - "+topoEmpruntDao.find(idTopo, idEmprunteur).getId());
		try {
			pTopoEmprunt.setId(topoEmpruntDao.find(idTopo, idEmprunteur).getId());
			pTopoEmprunt.setDateRetour(cal.getTime());
			pTopoEmprunt.setEmprunteur(pEmprunteur);
			pTopoEmprunt.setTopo(pTopoEmprunt.getTopo());
			pTopoEmprunt.setNom(pTopoEmprunt.getNom());
			topoEmpruntDao.delete(pTopoEmprunt);
	   		TransactionStatus vTScommit = vTransactionStatus;
	   		vTransactionStatus = null;
	   		platformTransactionManager.commit(vTScommit);
	   	}finally {
	   		if (vTransactionStatus != null) {
	   			platformTransactionManager.rollback(vTransactionStatus);
	      			try {
	      				throw new Exception("Cet emprunt n'existe pas : NOM=" + pTopoEmprunt.getNom());
	      				
	      			} catch (Exception e) {
	      				// TODO Auto-generated catch block
	      				e.printStackTrace();
	      			}	
	   		} 			
	   	}		
	}

	/**
	 * Méthode pour obtenir le nombre d'exemplaire restant d'un  {@link Topo}
	 */
	@Override
	public int getNbExemplaire(Topo pTopo) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		topoEmpruntDao = daoFactory.getTopoEmpruntDao();
		topoDAO = daoFactory.getTopoManagerDao();
		int nbEx;
		try {
			nbEx = topoDAO.find(pTopo.getNomTopo()).getNbreEx() - topoEmpruntDao.getListTopoEmprunt(pTopo).size();
			System.out.println("nbre ex : "+topoDAO.find(pTopo.getNomTopo()).getNbreEx()+" - "+topoEmpruntDao.getListTopoEmprunt(pTopo).size());
   			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return nbEx;
	}

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

	public IntTopoEmprunt getTopoEmprunt() {
		return topoEmprunt;
	}

	public void setTopoEmprunt(IntTopoEmprunt topoEmprunt) {
		this.topoEmprunt = topoEmprunt;
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
