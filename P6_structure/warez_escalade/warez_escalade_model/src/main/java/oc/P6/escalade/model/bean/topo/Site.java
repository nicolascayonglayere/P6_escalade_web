package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

@Named("site")
public class Site {

	private int id;
	private String nom;
	private Topo topo;
	
	public Site () {}
	
	public Site(String pNom) {
		this.nom = pNom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	
	
}
