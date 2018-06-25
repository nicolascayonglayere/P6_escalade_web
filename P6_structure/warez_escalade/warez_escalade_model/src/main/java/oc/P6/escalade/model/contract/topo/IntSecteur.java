package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.topo.Site;

public interface IntSecteur {
	
	int getId();
	void setId(int pId);
	
	String getNom();
	void setNom(String pNom);
	
	String getDescription();
	void setDescription(String pDescription);
	
	Site getSite();
	void setSite(Site pSite);
	
	String getImage();
	void setImage(String pImage);

}
