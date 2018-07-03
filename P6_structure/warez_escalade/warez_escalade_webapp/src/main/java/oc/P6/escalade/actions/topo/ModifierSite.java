package oc.P6.escalade.actions.topo;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Site;

public class ModifierSite extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomSite, nomTopo;
	private Site site;
	
	public String execute() {
		System.out.println(nomSite+" - "+nomTopo);
		site.setTopo(managerFactory.getTopoManager().getTopo(nomTopo));
		managerFactory.getSiteManager().modifierSite(site);
		addActionMessage("Le site "+site.getNomSite()+"a bien été modifié.");
		return ActionSupport.SUCCESS;	
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

}
