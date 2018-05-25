package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface CoordonneeUtilisateurDao {

	boolean create(CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	boolean delete (CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	boolean update (CoordonneeUtilisateur pCoordonneeUtilisateur);
	
	CoordonneeUtilisateur find(Utilisateur pAuteur);
}
