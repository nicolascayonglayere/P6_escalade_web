package oc.P6.escalade.business.impl.manager.topo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.topo.IntTopo;

/**
 * Implémentation de {@link TopoManager}
 * @author nicolas
 *
 */
@Named
public class TopoManagerImpl extends AbstractDAOManager implements TopoManager {

	@Inject
	private IntTopo topo;
	
	@Inject
	private TopoEmpruntManager topoEmpruntManagerImpl;
	
	@Inject
	private DAOFactory daoFactory;
	
	private TopoManagerDao topoDAO;
	private SiteManagerDAO siteDAO;
	private SecteurManagerDao secteurDAO;
	private VoieManagerDao voieDAO;
	
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Topo}
	 */
	@Override
	public ArrayList<Topo> getListTopo() {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
        ArrayList<Topo> listeTopo = new ArrayList<Topo>();
        try {
        	listeTopo= topoDAO.listerTopo();
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listeTopo;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Topo} en construction dont l'auteur est donné en paramètre 
	 */
	@Override
	public ArrayList<Topo> getListTopoConstr(String pNom) throws UtilisateurException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
        ArrayList<Topo> listTopoConstr = new ArrayList<Topo>();
        try {
        	listTopoConstr= topoDAO.listerTopo(pNom);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new UtilisateurException("L'utilisateur n'existe pas. "+pNom);
			} 			
		}
		return listTopoConstr;
	}
	
	/**
	 * Méthode pour obtenir le {@link Topo} dont le nom est donné en paramètre
	 * @throws TopoException 
	 */
	@Override
	public Topo getTopo(String pNom) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
        System.out.println(pNom);
		try {
			topo = topoDAO.find(pNom);
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				topo = null;
				throw new TopoException ("Le topo n'existe pas.");
			} 			
		}			

		return (Topo) topo;
	}

	/**
	 * Méthode pour créer le {@link Topo} donné en paramètre
	 * @throws TopoException 
	 */
	@Override
	public Topo creerTopo(Topo pTopo) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
		System.out.println("CTRL "+pTopo.getNomTopo());

		try {
			pTopo.setImage(pTopo.getNomTopo().replaceAll("\\p{Space}", ""));
			Path chemin = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"+pTopo.getImage());
			try {
				Files.createDirectories(chemin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pTopo.setConstruction(true);
			topo = topoDAO.create(pTopo);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) { 
				platformTransactionManager.rollback(vTransactionStatus);
				throw new TopoException("Le topo existe deja.");
			} 			
		}
		return (Topo) topo;
	}

	/**
	 * Méthode pour modifier le {@link Topo} donné en paramètre
	 * @throws TopoException 
	 */
	@Override
	public void modifTopo(Topo pTopo) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
		System.out.println("CTRL "+pTopo.getNomTopo());

		try {
			pTopo.setConstruction(false);
			topoDAO.update(pTopo);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new TopoException("Le topo n'existe pas.");
			} 			
		}		
	}

	/**
	 * Méthode pour obtenir une liste de {@link Topo} à partir d'un nom donné en paramètre
	 */
	@Override
	public ArrayList<Topo> rechercheTopo(String pNom) {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
		ArrayList<Topo>listTopo = new ArrayList<Topo>(); 
		try {
			listTopo = topoDAO.rechercherTopo(pNom) ;
			System.out.println("business recherche "+pNom+" - "+listTopo.size());
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listTopo;
	}

	@Override
	public ArrayList<Topo> rechercheMultiTopo(String pNom, String pDiffMin, String pDiffMax) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
		ArrayList<Topo>listTopo = new ArrayList<Topo>(); 
		
		try {
			listTopo = topoDAO.rechercheMultiTopo(pNom, pDiffMin, pDiffMax);
			System.out.println("ctrl business multi 1 "+listTopo.size());
			if(listTopo.size() == 0)
				throw new TopoException("Aucun résultat pour le topo de nom commençant par "+pNom);
			else {
				for (Topo t : listTopo) {
					System.out.println("ctrl business multi "+t.getId()+" - "+t.getListVoie().size());
					if(topoEmpruntManagerImpl.getNbExemplaire(t) == 0) {
						throw new TopoException("Il n'y a plus d'exemplaire disponible pour le topo : "+t.getNomTopo());
					}			
				}	
			}
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);

		}finally {
			if (vTransactionStatus != null) 
				platformTransactionManager.rollback(vTransactionStatus); 			
		}
		return listTopo;
	}

	@Override
	public void supprimerTopo(Topo pTopo) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        topoDAO = daoFactory.getTopoManagerDao();
		System.out.println("CTRL business "+pTopo.getNomTopo());

		try {
			pTopo.setId(topoDAO.find(pTopo.getNomTopo()).getId());
			try {
			for(Site si : daoFactory.getSiteManagerDao().find(pTopo.getId())) {
				for (Secteur se : daoFactory.getSecteurManagerDao().getListeSecteur(si)) {
					for (Voie v : daoFactory.getVoieManagerDao().getlistVoie(se)) {
						daoFactory.getVoieManagerDao().delete(v);
					}
					daoFactory.getSecteurManagerDao().delete(se);
				}
				daoFactory.getSiteManagerDao().delete(si);
			}			
			}catch (VoieException e1){
				e1.printStackTrace();
			} catch (SecteurException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SiteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			topoDAO.delete(pTopo);
			
			Path chemin = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"+pTopo.getImage());
			try {
				Files.delete(chemin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		}finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new TopoException("Le topo n'existe pas.");
			} 			
		}
		
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public IntTopo getTopo() {
		return topo;
	}

	public void setTopo(IntTopo topo) {
		this.topo = topo;
	}

	public SiteManagerDAO getSiteDAO() {
		return siteDAO;
	}

	public void setSiteDAO(SiteManagerDAO siteDAO) {
		this.siteDAO = siteDAO;
	}

	public SecteurManagerDao getSecteurDAO() {
		return secteurDAO;
	}

	public void setSecteurDAO(SecteurManagerDao secteurDAO) {
		this.secteurDAO = secteurDAO;
	}

	public VoieManagerDao getVoieDAO() {
		return voieDAO;
	}

	public void setVoieDAO(VoieManagerDao voieDAO) {
		this.voieDAO = voieDAO;
	}




}
