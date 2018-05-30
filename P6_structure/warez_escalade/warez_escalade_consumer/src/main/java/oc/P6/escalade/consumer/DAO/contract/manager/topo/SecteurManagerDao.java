package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

public interface SecteurManagerDao {
	
	boolean create(Secteur pSecteur);
	
	boolean delete (Secteur pSecteur);
	
	boolean update (Secteur pSecteur);
	
	Secteur find(String pNom);
	
	Secteur find(int id);
	
	ArrayList<Secteur> getListeSecteur(Site pSite);
}
