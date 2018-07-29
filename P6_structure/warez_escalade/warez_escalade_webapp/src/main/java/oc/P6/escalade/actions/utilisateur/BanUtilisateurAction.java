package oc.P6.escalade.actions.utilisateur;


import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action pour bannir un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class BanUtilisateurAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String checkMe;
	private Utilisateur utilisateur;
	
	/**
	 * Méthode pour bannir un {@link Utilisateur}
	 */
	public String execute() {
		logger.debug(checkMe);
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(checkMe);
			managerFactory.getUtilisateurManager().banUtilisateur(utilisateur);
			addActionMessage("Vous avez banni l'utilisateur "+utilisateur.getPseudo());
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			//e.printStackTrace();
			addActionMessage(e.getMessage());
			return ActionSupport.INPUT;
		}
	}

	//--Getter et Setter--//
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public String getCheckMe() {
		return checkMe;
	}
	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
