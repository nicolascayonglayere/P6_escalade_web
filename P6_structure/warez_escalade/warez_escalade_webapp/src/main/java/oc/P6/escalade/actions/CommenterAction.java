package oc.P6.escalade.actions;


import java.util.Calendar;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CommenterAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Utilisateur utilisateur;
	@Inject
	private ManagerFactory managerFactory;
	private String nom;
	private String message;
	private CommentaireTopo commentaireTopo;
	private Topo topo;
	private Map<String, Object> session; 
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
	
	
	public String execute() {
		System.out.println(((Utilisateur) session.get("utilisateur")).getPseudo());
		utilisateur = (Utilisateur) (session.get("utilisateur"));
		System.out.println("comentaire du topo "+topo.getNomTopo()+" - "+utilisateur.getPseudo()+" - "+commentaireTopo.getMessage());
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
		commentaireTopo.setMessage(commentaireTopo.getMessage());
		commentaireTopo.setAuteur(utilisateur);
		commentaireTopo.setTopo(topo);
		commentaireTopo.setDate(Calendar.getInstance().getTime());
		commentaireTopo.setValidation(false);
		managerFactory.getCommentaireTopoManager().creerCommentaireTopo(commentaireTopo);

		addActionMessage("Votre commentaire a bien été envoyé");
		return ActionSupport.SUCCESS;
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String input() {
		System.out.println("trace input commentaire");
		return ActionSupport.INPUT;
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
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

}
