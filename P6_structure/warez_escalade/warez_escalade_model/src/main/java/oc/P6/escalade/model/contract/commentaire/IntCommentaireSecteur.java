package oc.P6.escalade.model.contract.commentaire;

import java.util.Date;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface IntCommentaireSecteur {

	int getId();
	void setId(int pId);
	
	Utilisateur getAuteur();
	void setAuteur(Utilisateur pUtilisateur);
	
	Date getDate();
	void setDate(Date pDate);
	
	String getMessage();
	void setMessage(String pMessage);
	
	Secteur getSecteur();
	void setSecteur(Secteur pSecteur);
}
