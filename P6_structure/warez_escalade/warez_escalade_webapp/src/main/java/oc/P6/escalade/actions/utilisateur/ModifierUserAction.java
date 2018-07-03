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
		System.out.println("pseudo : "+utilisateur.getPseudo()+" - adresse "+coordonneeUtilisateur.getAdresse()+" - email "+coordonneeUtilisateur.getEmail());
		System.out.println("email "+managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
							managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId()).getEmail());
		System.out.println("adresse "+managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
						managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId()).getAdresse());
		if (utilisateur.getPseudo().equals(((Utilisateur)session.get("utilisateur")).getPseudo())) {
			//--si pseudo inchangé, je cherche une modif dans les 3 autres champs : 
			if ((managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
							managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId()).getAdresse().equals(coordonneeUtilisateur.getAdresse()))&&
					(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getPassword().equals(utilisateur.getPassword()))&&
					(managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
							managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId()).getEmail().equals(coordonneeUtilisateur.getEmail()))) {
				addActionMessage("Aucunes modifications enregistrées");
				vResult = ActionSupport.INPUT;

			}
			else {
				utilisateur.setId(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId());
				utilisateur.setId_Role(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId_Role());
				managerFactory.getUtilisateurManager().modifierUtilisateur(utilisateur);
				coordonneeUtilisateur.setUtilisateur(utilisateur);
				System.out.println(utilisateur.getPseudo()+" - "+ coordonneeUtilisateur.getIdUtilisateur());
				managerFactory.getCoordonneeUtilisateurManager().modifierCoordonnee(coordonneeUtilisateur);
				utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
				session.put("utilisateur", utilisateur);
				addActionMessage("Les modifications ont été correctement enregistrées.");
				vResult = ActionSupport.SUCCESS;

			}
		}
		else {//--si le pseudo a change, on vérifie qu'il n'existe pas deja en base de donnée
			if(utilisateur.getPseudo().equals(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getPseudo())) {
				addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
				vResult = ActionSupport.INPUT;
			}
			else {
				utilisateur.setId(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId());
				utilisateur.setId_Role(managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo()).getId_Role());
				managerFactory.getUtilisateurManager().modifierUtilisateur(utilisateur);
				coordonneeUtilisateur.setUtilisateur(utilisateur);
				System.out.println(utilisateur.getPseudo()+" - "+ coordonneeUtilisateur.getIdUtilisateur());
				managerFactory.getCoordonneeUtilisateurManager().modifierCoordonnee(coordonneeUtilisateur);
				utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
				session.put("utilisateur", utilisateur);
				addActionMessage("Les modifications ont été correctement enregistrées.");
				vResult = ActionSupport.SUCCESS;
			}
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
