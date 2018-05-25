package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import oc.P6.escalade.model.bean.topo.Secteur;

public interface SecteurManagerDao {
	
	boolean create(Secteur pSecteur);
	
	boolean delete (Secteur pSecteur);
	
	boolean update (Secteur pSecteur);
	
	Secteur find(String pNom);
}
