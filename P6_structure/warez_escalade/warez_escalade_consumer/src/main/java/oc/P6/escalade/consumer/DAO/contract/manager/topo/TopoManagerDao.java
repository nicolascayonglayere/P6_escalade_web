package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import oc.P6.escalade.model.bean.topo.Topo;


public interface TopoManagerDao {

	boolean create(Topo pTopo);
	
	boolean delete (Topo pTopo);
	
	boolean update (Topo pTopo);
	
	Topo find(String pNom);
}
