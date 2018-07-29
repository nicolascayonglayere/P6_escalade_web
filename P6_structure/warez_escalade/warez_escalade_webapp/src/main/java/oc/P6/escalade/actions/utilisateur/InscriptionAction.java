package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui permet l'inscription d'un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Prototype")
public class InscriptionAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Map<String, Object> session;
	private Utilisateur utilisateur;
	private CoordonneeUtilisateur coordonnee;

	/**
	 * Méthode qui inscrit un {@link Utilisateur}
	 * @return
	 */
	public String creerUser() {
		logger.debug("pseudo : "+utilisateur.getPseudo());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(hashedPassword);
		utilisateur.setCoordonnee(coordonnee);
		try {
			utilisateur = managerFactory.getUtilisateurManager().creerUtilisateur(utilisateur);
		} catch (UtilisateurException e) {
			logger.debug(e.getMessage());
			//e.printStackTrace();
			addActionMessage(e.getMessage());
			addFieldError("utilisateur.pseudo", "Veuillez choisir un autre pseudo.");
			return ActionSupport.INPUT;
		} catch (CoordonneeUtilisateurException ex) {
			logger.debug(ex.getMessage());
			//ex.printStackTrace();
			addActionMessage(ex.getMessage());
			addFieldError("coordonnee.email", "Veuillez choisir un autre email.");
			return ActionSupport.INPUT;			
		}
		session.put("utilisateur", utilisateur);
		addActionMessage("Vous etes correctement inscrit et connecté.");
		return ActionSupport.SUCCESS;

	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
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
}
