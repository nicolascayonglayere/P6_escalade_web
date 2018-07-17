package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

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
		String vResult = ActionSupport.INPUT;
		Utilisateur vUser = (Utilisateur)session.get("utilisateur"); 
		try {
			vUser.setCoordonnee(managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(vUser.getId()));
		} catch (CoordonneeUtilisateurException | UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("pseudo : "+utilisateur.getPseudo()+" - adresse "+coordonneeUtilisateur.getAdresse()+" - email "+coordonneeUtilisateur.getEmail());
		System.out.println("email "+vUser.getCoordonnee().getEmail());
		System.out.println("adresse "+vUser.getCoordonnee().getAdresse());
		
		utilisateur.setId(vUser.getId());
		utilisateur.setCoordonnee(coordonneeUtilisateur);
		coordonneeUtilisateur.setIdUtilisateur(vUser.getId());
		coordonneeUtilisateur.setId(vUser.getCoordonnee().getId());
		
		if (!(utilisateur.getPseudo().equals(vUser.getPseudo()))) {
			utilisateur = managerFactory.getUtilisateurManager().modifierPseudoUtilisateur(utilisateur);
			if(!(utilisateur.getPseudo().equals(vUser.getPseudo()))) {
				addActionMessage("Votre pseudo a été modifié.");
				vResult = ActionSupport.SUCCESS;
			}
			else {
				addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
				vResult = ActionSupport.INPUT;
			}
			
		}
		
		if(!(utilisateur.getPassword().equals(vUser.getPassword()))) {
			utilisateur = managerFactory.getUtilisateurManager().modifierPassUtilisateur(utilisateur);
			if(!(utilisateur.getPassword().equals(vUser.getPassword()))) {
				addActionMessage("Votre mot de passe a été modifié.");
				vResult = ActionSupport.SUCCESS;
			}
			else {
				addFieldError("utilisateur.password", "Veuillez choisir un autre mot de passe.");
				vResult = ActionSupport.INPUT;
			}
		}
		
		if(!(utilisateur.getCoordonnee().getEmail().equals(vUser.getCoordonnee().getEmail()))) {
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().modifierEmail(coordonneeUtilisateur);
			if(!(coordonneeUtilisateur.getEmail().equals(vUser.getCoordonnee().getEmail()))) {
				utilisateur.setCoordonnee(coordonneeUtilisateur);
				addActionMessage("Votre email a bien été modifié.");
				vResult = ActionSupport.SUCCESS;
			}
			else {
				addFieldError("coordonneeUtilisateur.email", "Cet email existe déja.");
				vResult = ActionSupport.INPUT;
			}
			
		}
		
		if(!(utilisateur.getCoordonnee().getAdresse().equals(vUser.getCoordonnee().getAdresse()))) {
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().modifierAdresse(coordonneeUtilisateur);
			utilisateur.setCoordonnee(coordonneeUtilisateur);
			addActionMessage("Votre adresse a bien été modifiée.");
			vResult = ActionSupport.SUCCESS;
		}
		
		if(vResult.equals("INPUT"))
			addActionMessage("Aucunes modifications enregistrées");
		else {
			utilisateur.setNom(vUser.getNom());
			utilisateur.setPrenom(vUser.getPrenom());
			utilisateur.setRole(vUser.getRole());
			utilisateur.setId_Role(vUser.getId_Role());
			session.remove("utilisateur");
			session.put("utilisateur", utilisateur);
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
