package oc.P6.escalade.model.contract.commentaire;

import java.util.Date;

import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface IntCommentaireTopo {

	int getId();
	void setId(int pId);
	
	Utilisateur getAuteur();
	void setAuteur(Utilisateur pUtilisateur);
	
	Date getDate();
	void setDate(Date pDate);
	
	String getMessage();
	void setMessage(String pMessage);
	
	Topo getTopo();
	void setTopo(Topo pTopo);
	
	boolean getValidation();
	void setValidation(boolean pValidation);
}
