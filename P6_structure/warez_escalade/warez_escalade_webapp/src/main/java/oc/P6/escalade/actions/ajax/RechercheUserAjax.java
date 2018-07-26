package oc.P6.escalade.actions.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Role;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class RechercheUserAjax extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Map<String, String[]> listUtilisateur = new HashMap<String, String[]>();
	private Utilisateur utilisateur;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	private Map<String, String> listRole = new HashMap<String, String>();
	private String pseudo;
	private HttpServletRequest request;
	
	/**
	 * Méthode qui retourne le résultat de la recherche d'un {@link Utilisateur}
	 */
	public String execute() {
		System.out.println(request.getParameter("pseudo"));
		try {
			for (Utilisateur u : managerFactory.getUtilisateurManager().getListUtilisateur(request.getParameter("pseudo"))) {
				String[] userArray = {u.getNom(), u.getPrenom()};
				System.out.println(u.toString());
				listUtilisateur.put(u.getPseudo(), userArray); //= managerFactory.getUtilisateurManager().getListUtilisateur(request.getParameter("pseudo"));
			}
					
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		for(Role r : managerFactory.getRoleManager().getListRole()) {
			listRole.put(String.valueOf(r.getId_role()), r.getRole());
		}
		return ActionSupport.SUCCESS;
	}

	//--Getter et Setter--//
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public Map<String, String[]> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(Map<String, String[]> listUtilisateur) {
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Map<String, String> getListRole() {
		return listRole;
	}

	public void setListRole(Map<String, String> listRole) {
		this.listRole = listRole;
	}
	
}
