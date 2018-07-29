package oc.P6.escalade.actions.ajax;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action asynchrone qui renvoie la liste des {@link TopoEmprunt} de le {@link Utilisateur} en session
 * @author nicolas
 *
 */
public class BilanEmpruntAjax extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TopoEmprunt> listTopoEmprunt = new ArrayList <TopoEmprunt>();
	@Inject
	ManagerFactory managerFactory;
	private Map<String, Object> session;
	
	public String execute() {
		
		try {
			listTopoEmprunt = managerFactory.getTopoEmpruntManager().getListTopoEmprunt(((Utilisateur)session.get("utilisateur")).getId());
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return ActionSupport.SUCCESS;
	}

	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}

	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
}
