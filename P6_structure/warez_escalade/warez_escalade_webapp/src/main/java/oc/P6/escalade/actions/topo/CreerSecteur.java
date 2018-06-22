package oc.P6.escalade.actions.topo;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;


public class CreerSecteur extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Secteur secteur;
	private String nomTopo, nomSite;
	
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	
	public String execute() {
		Topo topo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nomTopo);
		Site site = WebappHelper.getManagerFactory().getSiteManager().getSite(nomSite, topo);
		secteur.setSite(site);
		WebappHelper.getManagerFactory().getSecteurManager().creerSecteur(secteur);
		return ActionSupport.SUCCESS;
	}
	
}
