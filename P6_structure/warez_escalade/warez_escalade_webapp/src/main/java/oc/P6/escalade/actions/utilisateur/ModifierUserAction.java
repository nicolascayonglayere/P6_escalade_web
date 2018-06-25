package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class ModifierUserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
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
		if (managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getPseudo().equals(utilisateur.getPseudo())) {// et si le pseudo actuel != 
			addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
			vResult = ActionSupport.INPUT;
		}
		else {
			utilisateur.setId(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId());
			managerFactory.getUtilisateurManager().modifierUtilisateur(utilisateur);
			coordonneeUtilisateur.setUtilisateur(utilisateur);
			System.out.println(utilisateur.getPseudo()+" - "+ coordonneeUtilisateur.getIdUtilisateur());
			managerFactory.getCoordonneeUtilisateurManager().modifierCoordonnee(coordonneeUtilisateur);
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
			session.put("utilisateur", utilisateur);
			addActionMessage("Les modifications ont été correctement enregistrées.");
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
