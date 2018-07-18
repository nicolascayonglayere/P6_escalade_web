package oc.P6.escalade.actions;

import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;


import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

//@Named("presentation")
public class PresentationAction extends ActionSupport{

	/**
	 * 
	 */
	//@Inject
	//private WebappHelper webappHelper;
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Utilisateur> listAdmin;
	private ArrayList<Utilisateur> listModo;
	
	public ManagerFactory getManagerFactory() {
		System.out.println("trace getmanagerFacto");
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		System.out.println("trace setManagerFacto");
		this.managerFactory = managerFactory;
	}
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
	
		try {
			listModo = managerFactory.getUtilisateurManager().getListModo();
		} catch (UtilisateurException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.INPUT;
		}
		try {
			listAdmin = managerFactory.getUtilisateurManager().getListAdmin();
		} catch (UtilisateurException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		}

		return ActionSupport.SUCCESS;
	}

}
