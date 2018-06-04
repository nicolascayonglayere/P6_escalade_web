package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

public interface SecteurManager {

	ArrayList<Secteur> getListSecteur(Site pSite);
	
	Secteur getSecteur();
	
	void creerSecteur(String pNom);
}