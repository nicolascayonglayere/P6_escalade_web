package oc.P6.escalade.actions.topo;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class ModifierTopo extends ActionSupport implements SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	ManagerFactory managerFactory;
	private Topo topo;
	private String nom;
	private Map<String, Object> session;
	private HttpServletRequest request;

	public String execute() {
		//HttpServletRequest request = ServletActionContext.getRequest();
		nom = request.getParameter("nom");
		System.out.println(nom);
		topo = managerFactory.getTopoManager().getTopo(nom);
		managerFactory.getTopoManager().modifTopo(topo);
		addActionMessage("le topo "+topo.getNomTopo()+" a bien été modifié.");
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
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

}
