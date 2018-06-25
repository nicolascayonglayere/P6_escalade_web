package oc.P6.escalade.model.contract.commentaire;

import java.util.Date;

import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface IntCommentaireVoie {

	int getId();
	void setId(int pId);
	
	Utilisateur getAuteur();
	void setAuteur(Utilisateur pUtilisateur);
	
	Date getDate();
	void setDate(Date pDate);
	
	String getMessage();
	void setMessage(String pMessage);
	
	Voie getVoie();
	void setVoie(Voie pVoie);
}
