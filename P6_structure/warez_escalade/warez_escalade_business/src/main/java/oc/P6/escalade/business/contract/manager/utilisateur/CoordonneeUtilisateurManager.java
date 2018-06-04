package oc.P6.escalade.business.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;

public interface CoordonneeUtilisateurManager {

	CoordonneeUtilisateur getCoordonnee(int pId);
	
	void creerCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur);
	
	void modifierCoordonnee(CoordonneeUtilisateur pCoordinneeUtilisateur);
	
	void supprimerCoordonnee(CoordonneeUtilisateur pCoordonneeUtilisateur);
}
