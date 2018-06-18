package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public interface SiteManagerDAO {

	boolean create(Site pSite);
	
	boolean delete (Site pSite);
	
	boolean update (Site pSite);
	
	ArrayList<Site> find(String pNom);
	
	ArrayList<Site> find(int pId);
	
	Site find(String pNom, int pIdTopo);
	
	Site get(int pId);
}
