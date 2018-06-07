package oc.P6.escalade.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.commentaire.Commentaire;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CommenterAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	private CommentaireTopo commentaire;
	private String message;
	private String nom;
	private Topo topo;
	private HttpServletRequest servletRequest;
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public CommentaireTopo getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(CommentaireTopo commentaire) {
		this.commentaire = commentaire;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	public String commenterTopo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String nameTopo = request.getParameter("nom");
		System.out.println(topo.getNom()+" - "+nameTopo+" - "+utilisateur.getPseudo()+" - "+message);
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		topo = WebappHelper.getManagerFactory().getTopoManager().getTopo(nameTopo);
		commentaire.setvCommentaire(message);
		commentaire.setAuteur(utilisateur);
		commentaire.setTopo(topo);
		WebappHelper.getManagerFactory().getCommentaireTopoManager().creerCommentaireTopo(commentaire);
		
		return ActionSupport.SUCCESS;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;		
	}

}
