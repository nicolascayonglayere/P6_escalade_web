package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {
	/**
	 * 
	 */
	@Inject
	private ManagerFactory managerFactory;
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private String pseudo;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;

	

	public String home() {
		return SUCCESS;
	}

	// ---------------------------- Log Out register user

	
	public String logOut() {
		String username1 = ((Utilisateur) this.session.get("utilisateur")).getPseudo();
		System.out.println("deco - "+username1);
		utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(username1);
		session.remove("utilisateur");
		this.servletRequest.getSession().invalidate();
		addActionMessage("You Have Been Successfully Logged Out");
		return SUCCESS;
	}

	// ---------------------------- Login register user

	public String loginRegisterUser() {
		String vResult="";
		System.out.println(utilisateur.getPseudo()+" - "+utilisateur.getPassword());
		Utilisateur vUser = managerFactory.getUtilisateurManager().getUtilisateurPass(utilisateur.getPassword(), utilisateur.getPseudo());

		if(vUser.getNom() != null && !(vUser.getRole()).equals("banni")) {
			if ((utilisateur.getPseudo().equals(vUser.getPseudo()))&&(utilisateur.getPassword().equals(vUser.getPassword()))) {
				session.put("utilisateur", utilisateur);
				vResult = ActionSupport.SUCCESS;
			}
			else {
				addActionError("Entrer un pseudo et un mot de passe valide !");
				vResult = ActionSupport.LOGIN;
			}
		}
		

		else {
			addActionError("Vous avez été banni de ce site.");
			vResult = ActionSupport.LOGIN;
		}
		return vResult;
	}

	public String input() {
		return ActionSupport.INPUT;
	}
	
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
		// TODO Auto-generated method stub
		this.servletRequest = pRequest;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}


}
