package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.topo.Secteur;

public interface IntVoie {

	int getId();
	void setId(int pId);
	
	String getNom();
	void setNom(String pNom);
	
	String getDescription();
	void setDescription(String pDescription);

	String getCotation();
	void setCotation(String pCotation);
	
	int getHauteur();
	void setHauteur(int pHauteur);
	
	int getNbPoint();
	void setNbPoint(int pNbPoint);
	
	int getNbLgueur();
	void setNbLgueur(int pNbLgueur);
	
	Secteur getSecteur();
	void setSecteur(Secteur pSecteur);
}
