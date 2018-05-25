package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.TopoDaoImpl;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
public class TopoManagerImpl extends AbstractDAOManager implements TopoManager {

	@Inject
	private Topo topo;
	
	@Inject
	private TopoDaoImpl topoDAO;// = (TopoDaoImpl) getDAOFactory().getTopoManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Topo> getListTopo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topo getTopo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerTopo(Topo pTopo) {
		// TODO Auto-generated method stub
		
	}

}
