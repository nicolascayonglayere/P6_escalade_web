package oc.P6.escalade.model.contract.topo;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Interface du POJO Topo
 * @author nicolas
 *
 */
public interface IntTopo {

	int getId();
	void setId(int pId);
	
	String getNomTopo();
	void setNomTopo(String pNom);
	
	Utilisateur getAuteur();
	void setAuteur(Utilisateur pUtilisateur);
	
	String getDescription();
	void setDescription(String pDescription);
	
	String getImage();
	void setImage(String pImage);
	
	double getLatitude();
	void setLatitude(double pLatitude);
	
	double getLongitude();
	void setLongitude(double pLongitude);
	
	int getNbreEx();
	void setNbreEx(int pNbreEx);
	
	boolean getConstruction();
	void setConstruction(boolean pConstruction);
}
