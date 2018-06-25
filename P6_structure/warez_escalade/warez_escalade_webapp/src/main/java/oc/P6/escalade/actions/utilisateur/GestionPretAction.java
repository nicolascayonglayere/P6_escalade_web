package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class GestionPretAction extends ActionSupport implements SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nom;
	private String pseudo;
	private Utilisateur utilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	
	public String retourner() {
		pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
		HttpServletRequest request = ServletActionContext.getRequest();
		nom = request.getParameter("nom");
		System.out.println("nom topo retourner "+nom+" nom emprunteur "+pseudo);
		TopoEmprunt vTopoEmp = managerFactory.getTopoEmpruntManager().getTopoEmprunt(nom, utilisateur);//ajouter un param utilisateur.pseudo ds la methode getTopoEmprunt
		if (vTopoEmp != null)
			managerFactory.getTopoEmpruntManager().retourTopoEmprunt(vTopoEmp, utilisateur);
		
		addActionMessage("Vous avez rendu le topo "+vTopoEmp.getNom());
		return SUCCESS;
	}
	
	public String emprunter() {
		pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
		HttpServletRequest request = ServletActionContext.getRequest();
		nom = request.getParameter("nom");
		Topo vTopo = managerFactory.getTopoManager().getTopo(nom);
		//ajouter un ctrl pour pas emprunter +sieurs fois le meme topo + ctrl du nb d'ex dispo
		if (managerFactory.getTopoEmpruntManager().getTopoEmprunt(nom, utilisateur) == null) {
			if (managerFactory.getTopoEmpruntManager().getNbExemplaire(vTopo)>0) {
				managerFactory.getTopoEmpruntManager().creerTopoEmprunt(vTopo, utilisateur);
				addActionMessage("Votre emprunt est bien enregistré");		
				return ActionSupport.SUCCESS;
			}
			else {
				addActionMessage("Il n'y a plus d'exemplaires disponibles");
				return ActionSupport.INPUT;
			}
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

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	
	
}
