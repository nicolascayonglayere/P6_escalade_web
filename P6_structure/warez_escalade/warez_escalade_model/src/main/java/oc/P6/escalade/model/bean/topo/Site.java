package oc.P6.escalade.model.bean.topo;

import javax.inject.Named;

import oc.P6.escalade.model.contract.topo.IntSite;

/**
 * Objet métier représentant un site d'un topo
 * @author nicolas
 *
 */
@Named("site")
public class Site implements IntSite{

	private int id;
	private String nomSite;
	private Topo topo;
	private String description;
	private String image;
	
	/**
	 * Constructeurs
	 */
	public Site () {}
	
	public Site(String pNom) {
		this.nomSite = pNom;
	}

	/**
	 * Getter et Setter
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String getNomSite() {
		return this.nomSite;
	}

	@Override
	public void setNomSite(String pNom) {
		this.nomSite = pNom;		
	}
	
	
}
