package oc.P6.escalade.consumer.DAO.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

public interface VoieManagerDao {

	boolean create(Voie pVoie);
	
	boolean delete (Voie pVoie);
	
	boolean update (Voie pVoie);
	
	Voie find(String pNom);
	
	ArrayList<Voie> getlistVoie(Secteur pSecteur);
}
