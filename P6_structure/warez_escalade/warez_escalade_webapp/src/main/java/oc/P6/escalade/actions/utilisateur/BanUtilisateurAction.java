package oc.P6.escalade.actions.utilisateur;


import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
@Scope("Protoype")
public class BanUtilisateurAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String checkMe;
	private Utilisateur utilisateur;
	
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
	
	public String execute() {
		System.out.println(checkMe);
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(checkMe);
			managerFactory.getUtilisateurManager().banUtilisateur(utilisateur);
			addActionMessage("Vous avez banni l'utilisateur "+utilisateur.getPseudo());
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			e.printStackTrace();
			addActionMessage(e.getMessage());
			return ActionSupport.INPUT;
		}
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

}
