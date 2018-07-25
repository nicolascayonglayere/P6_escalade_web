package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui permet la suppression du compte d'un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
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

	
	/**
	 * Méthode supprimant le compte de l'{@link Utilisateur}
	 */
	public String execute() {
		utilisateur = (Utilisateur) session.get("utilisateur");
		System.out.println("pseudo : "+utilisateur.getPseudo());

		try {
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
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
	
	//--Getter et Setter--//
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
