package oc.P6.escalade.model.bean.utilisateur;

public enum RoleUtilisateur {

	Utilisateur(3),
	Moderateur(2),
	Administrateur(1),
	Banni(4);
	
	private int id_role;
	
	RoleUtilisateur(int pRole){
		this.id_role = pRole;
	}
	
	public int getId() {
	return this.id_role;
	}
	
}
