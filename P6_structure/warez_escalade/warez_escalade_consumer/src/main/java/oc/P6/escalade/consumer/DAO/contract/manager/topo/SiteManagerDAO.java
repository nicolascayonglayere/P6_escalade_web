package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public interface SiteManagerDAO {

	boolean create(Site pSite);
	
	boolean delete (Site pSite);
	
	boolean update (Site pSite);
	
	Site find(String pNom);
}
