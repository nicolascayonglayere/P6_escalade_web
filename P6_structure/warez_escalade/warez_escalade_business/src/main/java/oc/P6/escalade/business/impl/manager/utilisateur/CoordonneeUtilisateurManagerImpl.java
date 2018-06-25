package oc.P6.escalade.business.impl.manager.utilisateur;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.utilisateur.CoordonneeUtilisateurManager;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
/**
 * Implémentation de {@link CoordonneeUtilisateurManager}
 * @author nicolas
 *
 */
@Named
public class CoordonneeUtilisateurManagerImpl extends AbstractDAOManager implements CoordonneeUtilisateurManager{

	@Inject
	private CoordonneeUtilisateur coordonnee;
	@Inject
	private Utilisateur utilisateur;
	
	@Inject
	private CoordonneeUtilisateurDaoImpl coordonneeDao;
	
	@Inject
	private UtilisateurDaoImpl userDAO;
	
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir les {@link CoordonneeUtilisateur} de {@link Utilisateur} dont l'id est donné en paramètre
	 */
	@Override
	public CoordonneeUtilisateur getCoordonnee(int pId) {
		if(userDAO.find(pId) != null) {
			coordonnee.setUtilisateur(userDAO.find(pId));
			coordonnee.setAdresse(coordonneeDao.find(userDAO.find(pId)).getAdresse());
			coordonnee.setEmail(coordonneeDao.find(userDAO.find(pId)).getEmail());
			coordonnee.setIdUtilisateur(pId);
			coordonnee.setId(coordonneeDao.find(userDAO.find(pId)).getId());
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
    	return coordonnee;
	}

	/**
	 * Méthode pour créer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		
		System.out.println("CTRL coord "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setEmail(pCoordinneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordinneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordinneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.create(pCoordinneeUtilisateur);
		}finally {
			
		}
		
	}
	
	/**
	 * Méthode pour modifier {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void modifierCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		System.out.println("CTRL coord "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setId(coordonneeDao.find(pCoordinneeUtilisateur.getUtilisateur()).getId());
			coordonnee.setEmail(pCoordinneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordinneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordinneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.update(pCoordinneeUtilisateur);
		}finally {
			
		}
		
	}

	/**
	 * Méthode pour supprimer {@link CoordonneeUtilisateur} donnée en paramètre
	 */
	@Override
	public void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		System.out.println("CTRL coord "+pCoordonneeUtilisateur.getEmail()+" - "+pCoordonneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setId(coordonneeDao.find(pCoordonneeUtilisateur.getUtilisateur()).getId());
			coordonnee.setEmail(pCoordonneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordonneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordonneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.delete(pCoordonneeUtilisateur);
		}finally {
			
		}
		
	}

}
