package oc.P6.escalade.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class GestionPretAction extends ActionSupport implements SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String pseudo;
	private Utilisateur utilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	
	public String retourner() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("pseudo");
		
		System.out.println("nom topo retourner "+nom+" nom emprunteur "+pseudo);
		TopoEmprunt vTopoEmp = WebappHelper.getManagerFactory().getTopoEmpruntManager().getTopoEmprunt(nom);
		if (vTopoEmp != null)
			WebappHelper.getManagerFactory().getTopoEmpruntManager().retourTopoEmprunt(vTopoEmp, WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(pseudo));
		
		addActionMessage("Vous avez rendu le topo "+vTopoEmp.getNom());
		return SUCCESS;
	}
	
	public String emprunter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("pseudo");

		System.out.println("nom topo emprunter "+nom+" nom emprunteur "+username);
		Topo vTopo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nom);
		if (vTopo != null)
			WebappHelper.getManagerFactory().getTopoEmpruntManager().creerTopoEmprunt(vTopo, WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(username));
		addActionMessage("Votre emprunt est bien enregistré");		
		return SUCCESS;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
