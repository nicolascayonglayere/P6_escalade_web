package oc.P6.escalade.actions.topo;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public class ModifierSecteur extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomSecteur, nomTopo, nomSite;
	private Secteur secteur;
	private Site site;
	private Topo topo;
	private int id;
	private Map<String, Object> session;
	
	public String execute() {
		System.out.println(secteur.getNomSecteur()+" - "+topo.getNomTopo());
		secteur.setId(((Secteur)session.get("secteurModif")).getId());
		secteur.setSite(((Secteur)session.get("secteurModif")).getSite());
		if (secteur.equals(((Secteur)session.get("secteurModif")))) {
			addActionMessage("Aucune modification enregistrée ! ");
			return ActionSupport.INPUT;
		}
		else {
			try {
				managerFactory.getSecteurManager().modifierSecteur(secteur);
				this.session.remove("secteurModif");
				addActionMessage("Le secteur "+secteur.getNomSecteur()+"a bien été modifié.");
				return ActionSupport.SUCCESS;
			} catch (SecteurException e) {
				addActionMessage(e.getMessage());
				e.printStackTrace();
				return ActionSupport.INPUT;
			}
			
		}
	
	}
	
	public String input() {
		try {
			topo = managerFactory.getTopoManager().getTopo(nomTopo);
			site = managerFactory.getSiteManager().getSite(nomSite, topo);
			secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
			this.session.put("secteurModif", secteur);
			return ActionSupport.SUCCESS;
		} catch (TopoException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		}

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
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
