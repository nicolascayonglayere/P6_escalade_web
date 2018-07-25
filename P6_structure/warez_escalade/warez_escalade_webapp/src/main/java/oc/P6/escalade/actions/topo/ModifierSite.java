package oc.P6.escalade.actions.topo;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
@Scope("Protoype")
public class ModifierSite extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomSite, nomTopo;
	private Site site;
	private Topo topo;
	private Map<String, Object> session;
	
	public String execute() {
		System.out.println(site.getNomSite()+" - "+topo.getNomTopo());
		site.setId(((Site)session.get("siteModif")).getId());
		site.setTopo(((Site)session.get("siteModif")).getTopo());
		if(site.equals((Site)session.get("siteModif"))) {
			addActionMessage("Aucune modification enregistrée ! ");
			return ActionSupport.INPUT;
		}
		else {
			try {
				managerFactory.getSiteManager().modifierSite(site);
				this.session.remove("siteModif");
				addActionMessage("Le site "+site.getNomSite()+"a bien été modifié.");
				return ActionSupport.SUCCESS;
			} catch (SiteException e) {
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
			this.session.put("siteModif", site);
			return ActionSupport.SUCCESS;
		} catch (TopoException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		}

	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
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
