package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class BanUtilisateurAction extends ActionSupport implements SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(checkMe);
		WebappHelper.getManagerFactory().getUtilisateurManager().banUtilisateur(utilisateur);
		this.session.remove("utilisateur");
		addActionMessage("Vous avez banni l'utilisateur "+utilisateur.getPseudo());
		this.servletRequest.getSession().invalidate();
		return ActionSupport.SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;		
	}

}
