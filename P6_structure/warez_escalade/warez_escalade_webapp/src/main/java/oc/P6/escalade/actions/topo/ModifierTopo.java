package oc.P6.escalade.actions.topo;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class ModifierTopo extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	ManagerFactory managerFactory;
	private Topo topo;
	private String nom;
	private HttpServletRequest request;
	private String nomTopo;

	public String execute() {
		//HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(request.getParameter("nomTopo")+" - "+nomTopo);
		nom = request.getParameter("nomTopo");
		System.out.println(nom);
		topo = managerFactory.getTopoManager().getTopo(nom);
		managerFactory.getTopoManager().modifTopo(topo);
		addActionMessage("le topo "+topo.getNomTopo()+" a bien été modifié.");
		return ActionSupport.SUCCESS;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

}
