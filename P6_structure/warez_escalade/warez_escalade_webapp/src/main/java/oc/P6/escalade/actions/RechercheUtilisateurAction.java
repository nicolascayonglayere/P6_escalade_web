package oc.P6.escalade.actions;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class RechercheUtilisateurAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Utilisateur> listUtilisateur;
	private String pseudo;
	private Utilisateur utilisateur;
	
	public ArrayList<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(ArrayList<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String execute() {
		System.out.println(pseudo+" - "+utilisateur.getPseudo());
		listUtilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getListUtilisateur(pseudo);
		return ActionSupport.SUCCESS;
	}

}
