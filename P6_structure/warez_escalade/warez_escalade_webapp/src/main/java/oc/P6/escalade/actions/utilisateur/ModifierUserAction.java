package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
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

		try {
			utilisateur = managerFactory.getUtilisateurManager().modifierUtilisateur(utilisateur);
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().modifier(coordonneeUtilisateur);
			utilisateur.setNom(vUser.getNom());
			utilisateur.setPrenom(vUser.getPrenom());
			utilisateur.setRole(vUser.getRole());
			utilisateur.setId_Role(vUser.getId_Role());
			session.remove("utilisateur");
			session.put("utilisateur", utilisateur);
			addActionMessage("Vos modifications ont bien été enregistrées");
			vResult = ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			addFieldError("utilisateur.pseudo", e.getMessage());
			e.printStackTrace();
			vResult = ActionSupport.INPUT;
		} catch (CoordonneeUtilisateurException ex) {
			addActionMessage(ex.getMessage());
			addFieldError("coordonneeUtilisateur.email", ex.getMessage());
			ex.printStackTrace();
			vResult = ActionSupport.INPUT;
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
