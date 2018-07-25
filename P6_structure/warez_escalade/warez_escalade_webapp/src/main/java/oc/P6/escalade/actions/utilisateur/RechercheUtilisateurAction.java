package oc.P6.escalade.actions.utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Role;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action envoyant le résultat d'une recherche d'un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
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
	private Map<Integer, String> listRole = new HashMap<Integer, String>();
	
	/**
	 * Méthode qui retourne le résultat de la recherche d'un {@link Utilisateur}
	 */
	public String execute() {
		System.out.println(utilisateur.getPseudo());
		try {
			listUtilisateur = managerFactory.getUtilisateurManager().getListUtilisateur(utilisateur.getPseudo());
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		for(Role r : managerFactory.getRoleManager().getListRole()) {
			listRole.put(r.getId_role(), r.getRole());
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

	public Map<Integer, String> getListRole() {
		return listRole;
	}

	public void setListRole(Map<Integer, String> listRole) {
		this.listRole = listRole;
	}
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



}
