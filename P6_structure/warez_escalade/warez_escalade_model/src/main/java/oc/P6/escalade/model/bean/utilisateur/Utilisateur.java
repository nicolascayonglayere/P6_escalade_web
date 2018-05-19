package oc.P6.escalade.model.bean.utilisateur;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;



/**
 * Classe représentant un Utilsateur
 */
@Named("refUtilisateur")
public class Utilisateur {

    // ==================== Attributs ====================
    @NotNull
    @Size(min = 1)
    private String pseudo;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private int id;
    private String statut;


    // ==================== Constructeurs ====================
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


    // ==================== Getters/Setters ====================
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pPseudo) {
        pseudo = pPseudo;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	// ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        vStB.append(" {")
            .append("pseudo=").append(pseudo)
            .append("}");
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

