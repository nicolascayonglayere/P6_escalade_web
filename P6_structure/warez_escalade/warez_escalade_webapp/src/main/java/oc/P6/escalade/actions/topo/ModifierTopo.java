package oc.P6.escalade.actions.topo;


import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class ModifierTopo extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	ManagerFactory managerFactory;
	private Topo topo;
	private String nom;
	private String nomTopo;
	private Map<String, Object> session;

	public String execute() {
		System.out.println(topo.getNomTopo());
		topo.setId(((Topo)session.get("topoModif")).getId());
		if (topo.equals((Topo)session.get("topoModif"))) {
			addActionMessage("Aucune modification enregistrée ! ");
			return ActionSupport.INPUT;			
		}
		else {
			managerFactory.getTopoManager().modifTopo(topo);
			this.session.remove("topoModif");
			addActionMessage("le topo "+topo.getNomTopo()+" a bien été modifié.");
			return ActionSupport.SUCCESS;			
		}


	}
	
	public String input() {
		topo = managerFactory.getTopoManager().getTopo(nomTopo);
		this.session.put("topoModif", topo);
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

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
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
