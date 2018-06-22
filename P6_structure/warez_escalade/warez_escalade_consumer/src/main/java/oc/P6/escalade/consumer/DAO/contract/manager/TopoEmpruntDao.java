package oc.P6.escalade.consumer.DAO.contract.manager;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;

public interface TopoEmpruntDao {

	boolean create(TopoEmprunt pTopoEmprunt);
	
	boolean delete (TopoEmprunt pTopoEmprunt);
	
	boolean update (TopoEmprunt pTopoEmprunt);
	
	TopoEmprunt find(int pIdTopo, int pIdEmprunteur);
	
	ArrayList<TopoEmprunt> getListTopoEmprunt (int pId_utilisateur);
	
	ArrayList<TopoEmprunt> getListTopoEmprunt (Topo pTopo);
}
