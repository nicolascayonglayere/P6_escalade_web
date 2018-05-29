package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SecteurManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.SecteurDaoImpl;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

@Named
public class SecteurManagerImpl extends AbstractDAOManager implements SecteurManager{

	@Inject
	private Secteur secteur;
	
	@Inject
	private SecteurDaoImpl secteurDAO;// = (SecteurDaoImpl) getDAOFactory().getSecteurManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Secteur> getListSecteur(Site pSite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Secteur getSecteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerSecteur(String pNom) {
		// TODO Auto-generated method stub
		
	}

}
