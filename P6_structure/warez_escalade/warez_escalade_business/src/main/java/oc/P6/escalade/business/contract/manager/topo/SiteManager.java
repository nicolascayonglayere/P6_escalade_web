package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Site;

public interface SiteManager {

	ArrayList<Site> getListSite();
	
	Site getSite();
	
	void creerSite(String pNom);
}
