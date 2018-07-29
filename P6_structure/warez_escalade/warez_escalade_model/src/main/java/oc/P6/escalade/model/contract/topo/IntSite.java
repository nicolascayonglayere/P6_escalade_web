package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Interface du POJO {@link Site}
 * @author nicolas
 *
 */
public interface IntSite {

	int getId();
	void setId(int pId);
	
	String getNomSite();
	void setNomSite(String pNom);
	
	String getDescription();
	void setDescription(String pDescription);
	
	Topo getTopo();
	void setTopo(Topo pTopo);

}
