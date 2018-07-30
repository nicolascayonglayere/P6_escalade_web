package oc.P6.escalade.model.contract.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface du POJO CoordonneeUtilisateur
 * @author nicolas
 *
 */
public interface IntCoordonneeUtilisateur {

	int getId();
	void setId(int pId);
	
	String getEmail();
	void setEmail(String pEmail);
	
	String getAdresse();
	void setAdresse(String pAdresse);
	
	Utilisateur getUtilisateur();
	void setUtilisateur(Utilisateur pUtilisateur);
	
	int getIdUtilisateur();
	void setIdUtilisateur(int idUtilisateur);
	
}
