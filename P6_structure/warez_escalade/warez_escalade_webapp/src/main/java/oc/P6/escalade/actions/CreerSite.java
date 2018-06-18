package oc.P6.escalade.actions;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public class CreerSite extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Topo topo;
	private Site site;
	private String nom;
	
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String execute() {
		topo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nom);
		site.setTopo(topo);
		WebappHelper.getManagerFactory().getSiteManager().creerSite(site);
		return ActionSupport.SUCCESS;
	}

}
