package oc.P6.escalade.model.contract.emprunt;

import java.util.Date;

import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface IntTopoEmprunt {

	int getId();
	void setId(int pId);
	
	Topo getTopo();
	void setTopo(Topo pTopo);
	
	Utilisateur getEmprunteur();
	void setEmprunteur(Utilisateur pUtilisateur);
	
	Date getDateEmprunt();
	void setDateEmprunt(Date pDate);
	
	Date getDateRetour();
	void setDateRetour(Date pDate);
	
	String getNom();
	void setNom(String pNom);
}
