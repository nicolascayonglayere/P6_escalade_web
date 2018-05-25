package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.SiteDaoImpl;
import oc.P6.escalade.model.bean.topo.Site;

@Named
public class SiteManagerImpl extends AbstractDAOManager implements SiteManager{

	@Inject
	private Site site;
	
	@Inject
	private SiteDaoImpl siteDAO;// = (SiteDaoImpl) getDAOFactory().getSiteManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Site> getListSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerSite(String pNom) {
		// TODO Auto-generated method stub
		
	}

}
