package oc.P6.escalade.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class SupprimerUserAction extends ActionSupport implements SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	
	
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("pseudo");
		System.out.println("pseudo : "+username);
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(username);
		coordonneeUtilisateur = WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().supprimerCoordonnee(coordonneeUtilisateur);
		WebappHelper.getManagerFactory().getUtilisateurManager().deleteUtilisateur(utilisateur);
		session.remove("utilisateur");
		this.servletRequest.getSession().invalidate();
		addActionMessage("Votre compte a bien été supprimé.");
		return SUCCESS;
	}
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}


	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
		
	}

}
