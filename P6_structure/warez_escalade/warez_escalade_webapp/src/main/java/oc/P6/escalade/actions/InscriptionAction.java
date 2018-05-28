package oc.P6.escalade.actions;



import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class InscriptionAction extends ActionSupport {

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
		//--controle de la nullite du pseudo
		if (utilisateur.getPseudo().length() == 0) {
			addFieldError("utilisateur.pseudo", "Un pseudo est requis.");
			vResult = ActionSupport.INPUT;
		}
		
		if (utilisateur.getPassword().length() == 0) {
			addFieldError("utilisateur.password", "Un mot de passe est requis.");
			vResult = ActionSupport.INPUT;			
		}
		
		if(coordonnee.getEmail().length() == 0) {
			addFieldError ("coordonnee.email", "Un email est requis.");
			vResult = ActionSupport.INPUT;	
		}
		
		if(coordonnee.getAdresse().length() == 0) {
			addFieldError("coordonnee.adresse", "Une adresse est requise.");
			vResult = ActionSupport.INPUT;	
		}
		
		if (vResult != ActionSupport.INPUT) {
			WebappHelper.getManagerFactory().getUtilisateurManager().creerUtilisateur(utilisateur);

			//System.out.println("pseudo : "+utilisateur.getPseudo());
			//session.put("utilisateur", WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()));
			vResult = ActionSupport.SUCCESS;
		}
		coordonnee.setUtilisateur(WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()));
		System.out.println(utilisateur.getPseudo()+" - "+ coordonnee.getIdUtilisateur());
		WebappHelper.getManagerFactory().getCoordonneeUtilisateurManager().creerCoordonnee(coordonnee);
		
		System.out.println(vResult);
		return vResult;
	}
}
