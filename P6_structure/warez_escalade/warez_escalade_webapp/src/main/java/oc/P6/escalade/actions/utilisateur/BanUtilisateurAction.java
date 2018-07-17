package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

public class BanUtilisateurAction extends ActionSupport implements SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String checkMe;
	private Utilisateur utilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;

	
	public String getCheckMe() {
		return checkMe;
	}
	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String execute() {
		System.out.println(checkMe);
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(checkMe);
		} catch (UtilisateurException e1) {
			e1.printStackTrace();
			addActionMessage(e1.getMessage());
			return ActionSupport.INPUT;
		}

		try {
			managerFactory.getUtilisateurManager().banUtilisateur(utilisateur);
			addActionMessage("Vous avez banni l'utilisateur "+utilisateur.getPseudo());
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			e.printStackTrace();
			addActionMessage(e.getMessage());
			return ActionSupport.INPUT;
		}
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;		
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

}
