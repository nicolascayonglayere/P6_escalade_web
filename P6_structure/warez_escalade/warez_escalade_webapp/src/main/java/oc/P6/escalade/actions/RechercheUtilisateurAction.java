package oc.P6.escalade.actions;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class RechercheUtilisateurAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Utilisateur> listUtilisateur;
	private Utilisateur utilisateur;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	
	public ArrayList<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(ArrayList<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}

	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}

	public String execute() {
		System.out.println(utilisateur.getPseudo());
		listUtilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getListUtilisateur(utilisateur.getPseudo());
		System.out.println("ctrl topo emp "+listUtilisateur.get(1).getListTopoEmprunt());
		return ActionSupport.SUCCESS;
	}

}
