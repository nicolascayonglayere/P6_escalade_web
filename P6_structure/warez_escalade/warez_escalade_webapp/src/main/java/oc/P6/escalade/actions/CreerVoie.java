package oc.P6.escalade.actions;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class CreerVoie extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Voie voie;
	private String nomTopo, nomSite, nomSecteur;
	
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
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
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	
	
	public String execute() {
		Topo topo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nomTopo);
		Site site = WebappHelper.getManagerFactory().getSiteManager().getSite(nomSite, topo);
		Secteur secteur = WebappHelper.getManagerFactory().getSecteurManager().getSecteur(nomSecteur, site);
		voie.setSecteur(secteur);
		WebappHelper.getManagerFactory().getVoieManager().creerVoie(voie);
		
		return ActionSupport.SUCCESS;
	}
	

}
