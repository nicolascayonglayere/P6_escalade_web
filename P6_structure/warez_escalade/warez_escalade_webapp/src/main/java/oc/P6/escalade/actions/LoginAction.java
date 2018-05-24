package oc.P6.escalade.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;

	

	public String home() {
		return SUCCESS;
	}

	// ---------------------------- Log Out register user

	
	public String logOut() {
		session.remove("utilisateur");
		addActionMessage("You Have Been Successfully Logged Out");
		return SUCCESS;
	}

	// ---------------------------- Login register user

	public String loginRegisterUser() {
		System.out.println(utilisateur.getPseudo()+" - "+utilisateur.getPassword());
		Utilisateur vUser = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		
		if ((utilisateur.getPseudo().equals(vUser.getPseudo()))&&(utilisateur.getPassword().equals(vUser.getPassword()))) {
			session.put("utilisateur", utilisateur);
			return SUCCESS;
		}
		else {
			addActionError("Please Enter Valid emailId or Password");
			return LOGIN;
		}
		
		
		//if (utilisateur.getPseudo().equals("yogj") && utilisateur.getPassword().equals("admin")) {
		//	session.put("utilisateur", utilisateur);
		//	return SUCCESS;
		//} else {
		//	addActionError("Please Enter Valid emailId or Password");
		//	return LOGIN;
		//}
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

	@Override
	public void setServletRequest(HttpServletRequest pRequest) {
		// TODO Auto-generated method stub
		this.servletRequest = pRequest;
	}


}
