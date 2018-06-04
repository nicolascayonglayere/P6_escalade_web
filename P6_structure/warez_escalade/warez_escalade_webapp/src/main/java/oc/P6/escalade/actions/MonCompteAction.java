package oc.P6.escalade.actions;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class MonCompteAction extends ActionSupport implements SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;
	private String pseudo;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}

	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}
	
	
	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("utilisateur.pseudo");
		System.out.println("Compte de "+pseudo+" - "+username);
		
		if (pseudo.length() > 0)
			utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(pseudo);
		else
			utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(username);
		listTopoEmprunt = WebappHelper.getManagerFactory().getTopoEmpruntManager().getListTopoEmprunt(utilisateur.getId());
		coordonneeUtilisateur = WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		System.out.println("Compte : "+pseudo+" - "+utilisateur.getRole()+" - "+utilisateur.getNom()+" - "+listTopoEmprunt.size()+" - "+coordonneeUtilisateur.getEmail());
		return SUCCESS;
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
