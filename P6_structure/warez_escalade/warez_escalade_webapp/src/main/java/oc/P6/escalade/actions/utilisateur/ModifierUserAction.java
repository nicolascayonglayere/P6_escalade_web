package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui modifie le {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class ModifierUserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Map<String, Object> session;
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	

	/**
	 * Méthode qui modifie le {@link Utilisateur}
	 */
	public String execute() {
		String vResult = ActionSupport.INPUT;
		Utilisateur vUser = (Utilisateur)session.get("utilisateur"); 
		logger.debug(vUser.getId()+" - "+vUser.getId_Role());
		try {
			vUser.setCoordonnee(managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(vUser.getId()));

			logger.debug("pseudo : "+utilisateur.getPseudo()+" - adresse "+coordonneeUtilisateur.getAdresse()+" - email "+coordonneeUtilisateur.getEmail());
			logger.debug("email "+vUser.getCoordonnee().getEmail());
			logger.debug("adresse "+vUser.getCoordonnee().getAdresse());
			
			utilisateur.setId(vUser.getId());
			utilisateur.setCoordonnee(coordonneeUtilisateur);
			utilisateur.setId_Role(vUser.getId_Role());
			coordonneeUtilisateur.setIdUtilisateur(vUser.getId());
			coordonneeUtilisateur.setId(vUser.getCoordonnee().getId());

			utilisateur = managerFactory.getUtilisateurManager().modifierUtilisateur(utilisateur);
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().modifier(coordonneeUtilisateur);
			utilisateur.setNom(vUser.getNom());
			utilisateur.setPrenom(vUser.getPrenom());
			utilisateur.setRole(vUser.getRole());
			session.remove("utilisateur");
			session.put("utilisateur", utilisateur);
			addActionMessage("Vos modifications ont bien été enregistrées");
			vResult = ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			addFieldError("utilisateur.pseudo", e.getMessage());
			logger.debug(e.getMessage());
			//e.printStackTrace();
			vResult = ActionSupport.INPUT;
		} catch (CoordonneeUtilisateurException ex) {
			addActionMessage(ex.getMessage());
			addFieldError("coordonneeUtilisateur.email", ex.getMessage());
			logger.debug(ex.getMessage());
			//ex.printStackTrace();
			vResult = ActionSupport.INPUT;
		}
		
		logger.debug(vResult);
		return vResult;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
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
}
