package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Topo;

public interface TopoManager {

	ArrayList<Topo> getListTopo();
	
	Topo getTopo();
	
	void creerTopo(Topo pTopo);
	
	
}
