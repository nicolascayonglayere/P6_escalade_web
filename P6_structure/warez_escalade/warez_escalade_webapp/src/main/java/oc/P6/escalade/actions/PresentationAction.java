package oc.P6.escalade.actions;

import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class PresentationAction extends ActionSupport{

	/**
	 * 
	 */
	@Inject
	private ManagerFactory managerFactory;
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Utilisateur> listAdmin;
	private ArrayList<Utilisateur> listModo;
	public ArrayList<Utilisateur> getListAdmin() {
		return listAdmin;
	}
	public void setListAdmin(ArrayList<Utilisateur> listAdmin) {
		this.listAdmin = listAdmin;
	}
	public ArrayList<Utilisateur> getListModo() {
		return listModo;
	}
	public void setListModo(ArrayList<Utilisateur> listModo) {
		this.listModo = listModo;
	}
	
	public String execute() {
		
		listModo = managerFactory.getUtilisateurManager().getListModo();
		listAdmin = managerFactory.getUtilisateurManager().getListAdmin();
		
		return SUCCESS;
	}

}
