package oc.P6.escalade.actions.utilisateur;

import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class RechercheUtilisateurAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Utilisateur> listUtilisateur;
	private Utilisateur utilisateur;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	private ArrayList<String> listRole = new ArrayList<String>();
	
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
		listUtilisateur = managerFactory.getUtilisateurManager().getListUtilisateur(utilisateur.getPseudo());
		listRole.add("administrateur");
		listRole.add("moderateur");
		listRole.add("utilisateur");
		System.out.println("ctrl topo emp "+listUtilisateur.get(0).getListTopoEmprunt());
		return ActionSupport.SUCCESS;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public ArrayList<String> getListRole() {
		return listRole;
	}

	public void setListRole(ArrayList<String> listRole) {
		this.listRole = listRole;
	}

}
