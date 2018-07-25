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
public class CreerSite extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Topo topo;
	private Site site;
	private String nomTopo;
	private String nomSite;
	private Map<String, Object>session;
	
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
	
	public String execute() {
		try {
			if (((Topo)(session.get("topo"))).getNomTopo().length() > 0) {
				topo = (Topo)session.get("topo");
				//topo = managerFactory.getTopoManager().getTopo(nomTopo);
			}
			System.out.println("id_topo : "+topo.getId());
			site.setTopo(topo);
			site = managerFactory.getSiteManager().creerSite(site);
			//site = managerFactory.getSiteManager().getSite(site.getNomSite(), topo);
			addActionMessage("Le site "+site.getNomSite()+" a bien été crée.");
			session.put("site", site);
			return ActionSupport.SUCCESS;			
		}catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} 


	}
	
	public String input() {
		System.out.println(topo.getNomTopo());
		try {
			topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
		} catch (TopoException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return ActionSupport.SUCCESS;
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
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

}
