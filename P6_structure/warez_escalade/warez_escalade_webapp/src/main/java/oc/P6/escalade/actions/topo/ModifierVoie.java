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

public class ModifierVoie extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomVoie, nomTopo, nomSite, nomSecteur;
	private Voie voie;
	private Secteur secteur;
	private Site site;
	private Topo topo;
	private Map<String, Object> session;
	
	public String execute() {
		System.out.println(secteur.getNomSecteur()+" - "+voie.getNomVoie());
		voie.setId(((Voie)session.get("modifVoie")).getId());
		voie.setSecteur(((Voie)session.get("modifVoie")).getSecteur());
		if (voie.equals((Voie)session.get("modifVoie"))) {
			addActionMessage("Aucune modification enregistrée ! ");
			return ActionSupport.INPUT;
		}
		else {
			managerFactory.getVoieManager().majVoie(voie);
			this.session.remove("modifVoie");
			addActionMessage("La voie "+voie.getNomVoie()+"a bien été modifiée.");
			return ActionSupport.SUCCESS;			
		}
	
	}
	
	public String input() {
		topo = managerFactory.getTopoManager().getTopo(nomTopo);
		site = managerFactory.getSiteManager().getSite(nomSite, topo);
		secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
		voie = managerFactory.getVoieManager().getVoie(nomVoie, secteur);
		this.session.put("modifVoie", voie);
		return ActionSupport.SUCCESS;
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
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

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	


}
