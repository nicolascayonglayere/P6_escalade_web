package oc.P6.escalade.model.bean.utilisateur;

import javax.inject.Named;

import oc.P6.escalade.model.contract.utilisateur.IntRole;

/**
 * Objet métier représentant le role des {@link Utilisateur} , implémente {@link IntRole}
 * @author nicolas
 *
 */
@Named
public class Role implements IntRole{


	private String role;
	private int id_role;
	
	public Role(){

	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	

}
