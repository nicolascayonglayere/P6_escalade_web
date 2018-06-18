package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public interface SiteManager {

	ArrayList<Site> getListSite();
	
	Site getSite(String pNom, Topo pTopo);
	
	ArrayList<Site> getSite (Topo pTopo);
	
	void creerSite(Site pSite);
}
