package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui assure la connexion et la deconnexion d'un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {
	/**
	 * 
	 */
	
	static final Logger logger = LogManager.getLogger();
	@Inject
	private ManagerFactory managerFactory;
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private String pseudo;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;

	
	/**
	 * Méthode qui assure un retour à la page d'accueil
	 * @return
	 */
	public String home() {
		return SUCCESS;
	}

	/**
	 * Méthode pour déconnecter un {@link Utilisateur}
	 * @return
	 */
	public String logOut() {
		String username1 = ((Utilisateur) this.session.get("utilisateur")).getPseudo();
		logger.debug("deco - "+username1);
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(username1);
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			//e.printStackTrace();
			return ActionSupport.INPUT;
		}
		session.remove("utilisateur");
		this.servletRequest.getSession().invalidate();
		addActionMessage("You Have Been Successfully Logged Out");
		return ActionSupport.SUCCESS;
	}

	/**
	 * Méthode pour connecter un {@link Utilisateur}
	 * @return
	 */
	public String loginRegisterUser() {
		String vResult="";
		logger.debug(utilisateur.getPseudo()+" - "+utilisateur.getPassword());
		Utilisateur vUser;
		try {
			vUser = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
			logger.debug("mdp : "+vUser.getPassword());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if(!(vUser.getRole().equals("banni"))) {
				if ((utilisateur.getPseudo().equals(vUser.getPseudo()))&&(passwordEncoder.matches(utilisateur.getPassword(), vUser.getPassword()))) {
					session.put("utilisateur", vUser);
					vResult = ActionSupport.SUCCESS;
				}
				else {
					addActionError("Entrer un mot de passe valide !");
					vResult = ActionSupport.LOGIN;
				}
			}
			else {
				addActionError("Vous avez été banni de ce site.");
				vResult = ActionSupport.LOGIN;
			}
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			//e.printStackTrace();
			vResult = ActionSupport.LOGIN;
		}

		return vResult;
	}

	public String input() {
		return ActionSupport.INPUT;
	}
	
	//--Getter et Setter--//
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

	@Override
	public void setServletRequest(HttpServletRequest pRequest) {
		this.servletRequest = pRequest;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}


}
