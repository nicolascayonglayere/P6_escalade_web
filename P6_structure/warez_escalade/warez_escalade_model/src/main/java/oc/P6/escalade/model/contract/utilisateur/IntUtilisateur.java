package oc.P6.escalade.model.contract.utilisateur;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;

public interface IntUtilisateur {

	int getId();
	void setId(int pId);
	
	String getNom();
	void setNom(String pNom);
	
	String getPrenom();
	void setPrenom(String pPrenom);
	
	String getPseudo();
	void setPseudo(String pPseudo);
	
	String getPassword();
	void setPassword(String pPassword);
	
	int getId_Role();
	void setId_Role(int pId_Role);
	
	String getRole();
	void setRole(String pRole);
	
	CoordonneeUtilisateur getCoordonnee();
	void setCoordonnee(CoordonneeUtilisateur coordonnee);
	
	ArrayList<TopoEmprunt> getListTopoEmprunt();
	void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt);
	
	
}
