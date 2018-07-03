package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class SupprimerUserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private Map<String, Object> session;

	
	
	public String execute() {
		String pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		System.out.println("pseudo : "+pseudo);
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
		coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		managerFactory.getCoordonneeUtilisateurManager().supprimerCoordonnee(coordonneeUtilisateur);
		managerFactory.getUtilisateurManager().deleteUtilisateur(utilisateur);
		session.remove("utilisateur");
		//this.servletRequest.getSession().invalidate();
		this.addActionMessage("Votre compte a bien été supprimé.");
		return SUCCESS;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
