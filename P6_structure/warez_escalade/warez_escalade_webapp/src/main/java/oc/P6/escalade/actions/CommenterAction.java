package oc.P6.escalade.actions;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.Commentaire;
import oc.P6.escalade.model.bean.commentaire.CommentaireSite;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CommenterAction extends ActionSupport implements ServletRequestAware, SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	@Inject
	private ManagerFactory managerFactory;
	private String nom;
	private CommentaireTopo commentaireTopo;
	private Topo topo;
	private HttpServletRequest servletRequest;
	private Map<String, Object> session; 
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public CommentaireTopo getCommentaireTopo() {
		return commentaireTopo;
	}
	public void setCommentaireTopo(CommentaireTopo commentaireTopo) {
		this.commentaireTopo = commentaireTopo;
	}
	
	
	public String commenter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String nameTopo = request.getParameter("nom");
		utilisateur = (Utilisateur) (session.get("utilisateur"));
		System.out.println(topo.getNom()+" - "+nameTopo+" - "+utilisateur.getPseudo()+" - "+commentaireTopo.getMessage());
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		topo = managerFactory.getTopoManager().getTopo(nameTopo);
	
		commentaireTopo.setAuteur(utilisateur);
		commentaireTopo.setTopo(topo);
		managerFactory.getCommentaireTopoManager().creerCommentaireTopo(commentaireTopo);
		addActionMessage("Votre commentaire a bien été envoyé");
		return ActionSupport.SUCCESS;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String input() {
		return ActionSupport.INPUT;
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
