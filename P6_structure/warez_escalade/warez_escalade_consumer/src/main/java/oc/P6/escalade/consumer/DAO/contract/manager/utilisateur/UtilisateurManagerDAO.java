package oc.P6.escalade.consumer.DAO.contract.manager.utilisateur;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface UtilisateurManagerDAO {
	
	boolean create(Utilisateur pUtilisateur);
	
	boolean delete (Utilisateur pUtilisateur);
	
	boolean update (Utilisateur pUtilisateur);
	
	Utilisateur find(String pPseudo);
	
	Utilisateur find(int pId);
	
	Utilisateur findPass(String pPassword);
	
	ArrayList<Utilisateur> getList(int pIdRole);
	
	ArrayList<Utilisateur> getList(String pPseudo);

}
