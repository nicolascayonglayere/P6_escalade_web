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
		pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(pseudo);
		HttpServletRequest request = ServletActionContext.getRequest();
		nom = request.getParameter("nom");
		System.out.println("nom topo retourner "+nom+" nom emprunteur "+pseudo);
		TopoEmprunt vTopoEmp = WebappHelper.getManagerFactory().getTopoEmpruntManager().getTopoEmprunt(nom, utilisateur);//ajouter un param utilisateur.pseudo ds la methode getTopoEmprunt
		if (vTopoEmp != null)
			WebappHelper.getManagerFactory().getTopoEmpruntManager().retourTopoEmprunt(vTopoEmp, utilisateur);
		
		addActionMessage("Vous avez rendu le topo "+vTopoEmp.getNom());
		return SUCCESS;
	}
	
	public String emprunter() {
		pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(pseudo);
		HttpServletRequest request = ServletActionContext.getRequest();
		nom = request.getParameter("nom");
		Topo vTopo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nom);
		//ajouter un ctrl pour pas emprunter +sieurs fois le meme topo + ctrl du nb d'ex dispo
		if (WebappHelper.getManagerFactory().getTopoEmpruntManager().getTopoEmprunt(nom, utilisateur) == null) {
			WebappHelper.getManagerFactory().getTopoEmpruntManager().creerTopoEmprunt(vTopo, utilisateur);
			addActionMessage("Votre emprunt est bien enregistré");		
			return SUCCESS;
		}
		else
			addActionMessage("Vous avez deja emprunté ce topo.");
			return ActionSupport.INPUT;
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
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
		
	}
	
	
}
