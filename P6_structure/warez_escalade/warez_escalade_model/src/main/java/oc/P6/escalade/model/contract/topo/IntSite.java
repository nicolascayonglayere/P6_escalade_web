package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.topo.Topo;

public interface IntSite {

	int getId();
	void setId(int pId);
	
	String getNom();
	void setNom(String pNom);
	
	String getDescription();
	void setDescription(String pDescription);
	
	Topo getTopo();
	void setTopo(Topo pTopo);
	
	String getImage();
	void setImage(String pImage);
}
