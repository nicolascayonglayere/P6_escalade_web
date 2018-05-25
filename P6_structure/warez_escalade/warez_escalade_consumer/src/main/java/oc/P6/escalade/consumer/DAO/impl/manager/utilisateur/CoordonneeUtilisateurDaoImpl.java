package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("coordonneeUtilisateurDao")
public class CoordonneeUtilisateurDaoImpl extends AbstractDAO implements CoordonneeUtilisateurDao{

	@Override
	public boolean create(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CoordonneeUtilisateur find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
