package oc.P6.escalade.actions.utilisateur;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class MonCompteAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Utilisateur utilisateur;
	private String pseudo;
	private String role;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private ArrayList<Topo> listTopoConstr;
	private Map<String, Object> session;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}

	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}
	
	
	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String execute() {
		String username1 = ((Utilisateur) session.get("utilisateur")).getPseudo();
		System.out.println("Compte de "+username1);
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(username1);
		listTopoEmprunt = managerFactory.getTopoEmpruntManager().getListTopoEmprunt(utilisateur.getId());
		coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		role = utilisateur.getRole();
		if(role.equals("administrateur"))
			listTopoConstr = managerFactory.getTopoManager().getListTopoConstr(utilisateur.getPseudo());
		System.out.println("Compte : "+username1+" - "+utilisateur.getRole()+" - "+utilisateur.getNom()+" - "+listTopoEmprunt.size()+" - "+coordonneeUtilisateur.getEmail());
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public ArrayList<Topo> getListTopoConstr() {
		return listTopoConstr;
	}

	public void setListTopoConstr(ArrayList<Topo> listTopoConstr) {
		this.listTopoConstr = listTopoConstr;
	}
}
