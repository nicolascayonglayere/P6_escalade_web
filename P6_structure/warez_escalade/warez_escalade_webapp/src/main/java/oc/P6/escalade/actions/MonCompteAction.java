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
	private String role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String execute() {
		String username1 = ((Utilisateur) session.get("utilisateur")).getPseudo();
		System.out.println("Compte de "+username1);
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(username1);
		listTopoEmprunt = WebappHelper.getManagerFactory().getTopoEmpruntManager().getListTopoEmprunt(utilisateur.getId());
		coordonneeUtilisateur = WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		role = utilisateur.getRole();
		System.out.println("Compte : "+username1+" - "+utilisateur.getRole()+" - "+utilisateur.getNom()+" - "+listTopoEmprunt.size()+" - "+coordonneeUtilisateur.getEmail());
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
