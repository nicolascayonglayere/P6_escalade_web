package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface UtilisateurManagerDAO {
	
	boolean create(Utilisateur pUtilisateur);
	
	boolean delete (Utilisateur pUtilisateur);
	
	boolean update (Utilisateur pUtilisateur);
	
	Utilisateur find(String pPseudo);
	
	Utilisateur find(int pId);
	
	

}
