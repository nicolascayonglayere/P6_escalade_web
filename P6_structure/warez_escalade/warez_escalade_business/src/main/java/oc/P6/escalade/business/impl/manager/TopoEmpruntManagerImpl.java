package oc.P6.escalade.business.impl.manager;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.consumer.DAO.impl.manager.TopoEmpruntDaoImpl;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
public class TopoEmpruntManagerImpl extends AbstractDAOManager implements TopoEmpruntManager{

	@Inject
	private TopoEmprunt topoEmprunt;
	
	@Inject
	private TopoEmpruntDaoImpl topoEmpruntDao;
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopoEmprunt getTopoEmprunt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerTopoEmprunt(Topo topo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajoutTopoEmprunt(Topo topo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retourTopoEmprunt(TopoEmprunt topoEmprunt) {
		// TODO Auto-generated method stub
		
	}

}
