package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.topo.Site;

/**
 * Interface du POJO {@link Secteur}
 * @author nicolas
 *
 */
public interface IntSecteur {
	
	int getId();
	void setId(int pId);
	
	String getNomSecteur();
	void setNomSecteur(String pNom);
	
	String getDescription();
	void setDescription(String pDescription);
	
	Site getSite();
	void setSite(Site pSite);
	
	String getImage();
	void setImage(String pImage);

}
