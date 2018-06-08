package oc.P6.escalade.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
		utilisateur = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(username1);
		session.remove("utilisateur");
		this.servletRequest.getSession().invalidate();
		addActionMessage("You Have Been Successfully Logged Out");
		return SUCCESS;
	}

	// ---------------------------- Login register user

	public String loginRegisterUser() {
		String vResult="";
		System.out.println(utilisateur.getPseudo()+" - "+utilisateur.getPassword());
		Utilisateur vUser = WebappHelper.getManagerFactory().getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		if(vUser.getNom() != null) {
			if ((utilisateur.getPseudo().equals(vUser.getPseudo()))&&(utilisateur.getPassword().equals(vUser.getPassword()))) {
				session.put("utilisateur", utilisateur);
				vResult = ActionSupport.SUCCESS;
			}		
		}
		

		else {
			addActionError("Please Enter Valid emailId or Password");
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


}
