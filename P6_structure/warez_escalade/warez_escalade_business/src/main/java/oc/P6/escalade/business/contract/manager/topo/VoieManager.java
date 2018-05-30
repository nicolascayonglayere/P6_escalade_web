package oc.P6.escalade.business.contract.manager.topo;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

public interface VoieManager {

	ArrayList<Voie> getListVoie(Secteur pSecteur);
	
	Voie getVoie();
	
	void creerVoie(Voie pVoie);
	
	void majVoie(Voie pVoie);
}
