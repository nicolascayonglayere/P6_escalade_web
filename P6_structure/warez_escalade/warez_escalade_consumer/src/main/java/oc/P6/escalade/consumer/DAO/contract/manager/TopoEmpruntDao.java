package oc.P6.escalade.consumer.DAO.contract.manager;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface TopoEmpruntDao {

	boolean create(TopoEmprunt pTopoEmprunt);
	
	boolean delete (TopoEmprunt pTopoEmprunt);
	
	boolean update (TopoEmprunt pTopoEmprunt);
	
	TopoEmprunt find(Utilisateur pEmprunteur);
}
