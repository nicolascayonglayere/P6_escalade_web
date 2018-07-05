package oc.P6.escalade.actions.utilisateur;



import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class InscriptionAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
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
		if (managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getNom()!=null) {
			addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
			vResult = ActionSupport.INPUT;
		}
		//--ctrl du nom/prenom -> eviter les doubles comptes
		else if(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getNom()).getNom() != null &&
				managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPrenom()).getPrenom() != null) {
			addFieldError("utilisateur.nom", "Vous avez deja un compte.");
			vResult = ActionSupport.INPUT;
		}
		
		else {//if (vResult != ActionSupport.INPUT) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(utilisateur.getPassword());
			utilisateur.setPassword(hashedPassword);
			managerFactory.getUtilisateurManager().creerUtilisateur(utilisateur);
			//managerFactory.getUtilisateurManager().modifierPassUtilisateur(utilisateur);
			coordonnee.setUtilisateur(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()));
			System.out.println(utilisateur.getPseudo()+" - "+ coordonnee.getIdUtilisateur());
			managerFactory.getCoordonneeUtilisateurManager().creerCoordonnee(coordonnee);
			session.put("utilisateur", utilisateur);
			addActionMessage("Vous etes correctement inscrit et connecté.");
			vResult = ActionSupport.SUCCESS;
		}

		
		System.out.println(vResult);
		return vResult;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
