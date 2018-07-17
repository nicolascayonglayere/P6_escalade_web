package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

public class SupprimerUserAction extends ActionSupport implements SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private Map<String, Object> session;
	private HttpServletRequest request;

	
	
	public String execute() {
		utilisateur = (Utilisateur) session.get("utilisateur");
		//String pseudo = ((Utilisateur) session.get("utilisateur")).getPseudo();
		System.out.println("pseudo : "+utilisateur.getPseudo());
		//utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(pseudo);
		try {
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		} catch (CoordonneeUtilisateurException | UtilisateurException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.INPUT;
		}

		try {
			managerFactory.getCoordonneeUtilisateurManager().supprimerCoordonnee(coordonneeUtilisateur);
			managerFactory.getUtilisateurManager().deleteUtilisateur(utilisateur);
			session.remove("utilisateur");
			this.request.getSession().invalidate();
			this.addActionMessage("Votre compte a bien été supprimé.");
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}catch (CoordonneeUtilisateurException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		}

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


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}


}
