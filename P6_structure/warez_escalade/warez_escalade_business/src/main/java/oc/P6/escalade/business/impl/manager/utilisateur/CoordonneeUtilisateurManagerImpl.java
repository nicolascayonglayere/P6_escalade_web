package oc.P6.escalade.business.impl.manager.utilisateur;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.utilisateur.CoordonneeUtilisateurManager;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.CoordonneeUtilisateurDaoImpl;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CoordonneeUtilisateurManagerImpl extends AbstractDAOManager implements CoordonneeUtilisateurManager{

	@Inject
	private CoordonneeUtilisateur coordonnee;
	@Inject
	private Utilisateur utilisateur;
	
	@Inject
	private CoordonneeUtilisateurDaoImpl coordonneeDao;
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public CoordonneeUtilisateur getCoordonnee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		
		System.out.println("CTRL "+pCoordinneeUtilisateur.getEmail()+" - "+pCoordinneeUtilisateur.getUtilisateur().getId());
		try {
			coordonnee.setEmail(pCoordinneeUtilisateur.getEmail());
			coordonnee.setAdresse(pCoordinneeUtilisateur.getAdresse());
			coordonnee.setIdUtilisateur(pCoordinneeUtilisateur.getUtilisateur().getId());
			coordonneeDao.create(pCoordinneeUtilisateur);
		}finally {
			
		}
		
	}

	@Override
	public void majCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur) {
		// TODO Auto-generated method stub
		
	}

}
