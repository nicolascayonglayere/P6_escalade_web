package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;

public interface SecteurManager {

	ArrayList<Secteur> getListSecteur();
	
	Secteur getSecteur();
	
	void creerSecteur(String pNom);
}
