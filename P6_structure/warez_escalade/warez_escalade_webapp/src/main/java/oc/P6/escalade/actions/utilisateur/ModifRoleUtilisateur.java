package oc.P6.escalade.actions.utilisateur;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

public class ModifRoleUtilisateur extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String checkMe;
	private Utilisateur utilisateur;
	private int id_role;
	private int selectedRole;
	
	public String getCheckMe() {
		return checkMe;
	}
	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String execute() {
		System.out.println(checkMe+" - "+selectedRole);
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(checkMe);
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		utilisateur.setId_Role(selectedRole);
		
		managerFactory.getUtilisateurManager().modifierRoleUtilisateur(utilisateur);
		addActionMessage("Vous avez modifier le role de l'utilisateur "+utilisateur.getPseudo());
		return ActionSupport.SUCCESS;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public int getId_role() {
		return id_role;
	}
	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	public int getSelectedRole() {
		return selectedRole;
	}
	public void setSelectedRole(int selectedRole) {
		this.selectedRole = selectedRole;
	}

}
