package oc.P6.escalade.business.contract.manager;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface TopoEmpruntManager {

	ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur);
	
	TopoEmprunt getTopoEmprunt(String pNom, Utilisateur pEmprunteur);
	
	void creerTopoEmprunt(Topo topo, Utilisateur pEmprunteur);
	
	void ajoutTopoEmprunt(Topo topo);
	
	void retourTopoEmprunt(TopoEmprunt pTopoEmprunt, Utilisateur pEmprunteur);
	
	int getNbExemplaire(Topo pTopo);
}
