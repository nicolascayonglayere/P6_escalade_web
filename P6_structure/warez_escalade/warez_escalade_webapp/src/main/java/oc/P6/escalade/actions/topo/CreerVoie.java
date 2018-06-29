package oc.P6.escalade.actions.topo;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class CreerVoie extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Voie voie;
	private String nomTopo, nomSite, nomSecteur;
	private Topo topo;
	private Secteur secteur;
	private Site site;
	private Map<String, Object> session;
	
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
		System.out.println("trace creation voie");
		nomTopo = ((Topo)session.get("topo")).getNomTopo();
		topo = managerFactory.getTopoManager().getTopo(nomTopo);
		nomSite=((Site)session.get("site")).getNomSite();
		site = managerFactory.getSiteManager().getSite(nomSite, topo);
		nomSecteur = ((Secteur)session.get("secteur")).getNomSecteur();
		secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
		voie.setSecteur(secteur);
		System.out.println(nomTopo+" - "+nomSite+" - "+nomSecteur+" - "+voie.getCotation());
		managerFactory.getVoieManager().creerVoie(voie);
		addActionMessage("La voie "+voie.getNomVoie()+" a bien été créee.");
		return ActionSupport.SUCCESS;
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	

}
