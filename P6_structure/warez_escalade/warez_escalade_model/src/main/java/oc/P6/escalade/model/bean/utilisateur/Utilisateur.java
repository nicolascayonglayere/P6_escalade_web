package oc.P6.escalade.model.bean.utilisateur;

import java.util.ArrayList;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;



/**
 * Classe représentant un Utilsateur
 */
@Named("utilisateur")
public class Utilisateur {
 
    @NotNull
    @Size(min = 1)
    private String pseudo;
    private String nom;
    private String prenom;
    private String password;
    private CoordonneeUtilisateur coordonnee;
    private int id;
    //private RoleUtilisateur statut;
    private int id_role;
    private String role;
    private ArrayList<TopoEmprunt> listTopoEmprunt;

    /**
     * Constructeur.
     */
    public Utilisateur() {
    }


    /**
     * Constructeur.
     *
     * @param pPseudo -
     */
    public Utilisateur(String pPseudo) {
        pseudo = pPseudo;
    }


	/**
	 * Getter et Setter
	 * @return
	 */
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pPseudo) {
        pseudo = pPseudo;
    }


    public int getId_role() {
		return id_role;
	}


	public void setId_role(int id_role) {
		this.id_role = id_role;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public CoordonneeUtilisateur getCoordonnee() {
		return coordonnee;
	}


	public void setCoordonnee(CoordonneeUtilisateur coordonnee) {
		this.coordonnee = coordonnee;
	}
	

	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}


	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	// ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        vStB.append(" {").append("pseudo=").append(pseudo).append("}");
        return vStB.toString();
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || (obj instanceof Utilisateur && StringUtils.equals(((Utilisateur) obj).getPseudo(), pseudo));
    }


    @Override
    public int hashCode() {
        return this.pseudo != null ? this.pseudo.hashCode() : super.hashCode();
    }
}

