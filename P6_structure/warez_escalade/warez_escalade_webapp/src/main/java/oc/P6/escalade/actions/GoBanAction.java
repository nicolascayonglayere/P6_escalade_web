package oc.P6.escalade.actions;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
@Named
public class GoBanAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Utilisateur utilisateur;
	private Map<String, Object>session;
	
	public String execute() {
		try {
			utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(((Utilisateur)session.get("utilisateur")).getPseudo());
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	
}
