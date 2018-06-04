package oc.P6.escalade.actions;



import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class InscriptionAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonnee;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public CoordonneeUtilisateur getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(CoordonneeUtilisateur coordonnee) {
		this.coordonnee = coordonnee;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
	public String execute() throws Exception {
        return SUCCESS;
    }
	
	public String creerUser() {
		String vResult = "";
		System.out.println("pseudo : "+utilisateur.getPseudo());
		//--ctrl du pseudo
		if (WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getPseudo() != null) {
			addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
			vResult = ActionSupport.INPUT;
		}
		//--ctrl du nom/prenom -> eviter les doubles comptes
		else if(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getNom()).getNom() != null &&
				WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPrenom()).getPrenom() != null) {
			addFieldError("utilisateur.nom", "Vous avez deja un compte.");
			vResult = ActionSupport.INPUT;
		}
		
		else {//if (vResult != ActionSupport.INPUT) {
			WebappHelper.getManagerFactory().getUtilisateurManager().creerUtilisateur(utilisateur);
			coordonnee.setUtilisateur(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()));
			System.out.println(utilisateur.getPseudo()+" - "+ coordonnee.getIdUtilisateur());
			WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().creerCoordonnee(coordonnee);
			session.put("utilisateur", utilisateur);
			vResult = ActionSupport.SUCCESS;
		}

		
		System.out.println(vResult);
		return vResult;
	}
}
