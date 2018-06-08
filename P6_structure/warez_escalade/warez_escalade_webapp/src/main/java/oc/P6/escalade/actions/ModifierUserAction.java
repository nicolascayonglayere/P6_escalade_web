package oc.P6.escalade.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class ModifierUserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	
	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session = pSession;		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}
	
	public String execute() {
		String vResult = "";
		System.out.println("pseudo : "+utilisateur.getPseudo());
		//--ctrl du pseudo
		if (WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getPseudo().equals(utilisateur.getPseudo())) {
			addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
			vResult = ActionSupport.INPUT;
		}
		else {
			utilisateur.setId(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId());
			WebappHelper.getManagerFactory().getUtilisateurManager().modifierUtilisateur(utilisateur);
			coordonneeUtilisateur.setUtilisateur(utilisateur);
			//coordonneeUtilisateur.setUtilisateur(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()));
			System.out.println(utilisateur.getPseudo()+" - "+ coordonneeUtilisateur.getIdUtilisateur());
			WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().modifierCoordonnee(coordonneeUtilisateur);
			utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
			session.put("utilisateur", utilisateur);
			addActionMessage("Les modifications ont été correctement enregistrées.");
			vResult = ActionSupport.SUCCESS;
		}

		
		System.out.println(vResult);
		return vResult;
	}
}
