package oc.P6.escalade.business.contract.manager;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;

public interface TopoEmpruntManager {

	ArrayList<TopoEmprunt> getListTopoEmprunt();
	
	TopoEmprunt getTopoEmprunt();
	
	void creerTopoEmprunt(Topo topo);
	
	void ajoutTopoEmprunt(Topo topo);
	
	void retourTopoEmprunt(TopoEmprunt topoEmprunt);
}
