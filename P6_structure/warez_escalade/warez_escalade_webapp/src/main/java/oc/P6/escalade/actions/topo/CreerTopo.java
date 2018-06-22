package oc.P6.escalade.actions.topo;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CreerTopo extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Topo topo;
	private Utilisateur utilisateur;
	private Map<String, Object>session;
	
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String execute() {
		utilisateur=(Utilisateur)session.get("utilisateur");
		topo.setAuteur(utilisateur);
		System.out.println(topo.getNom()+" - "+topo.getAuteur().getPseudo());
		WebappHelper.getManagerFactory().getTopoManager().creerTopo(topo);
		addActionMessage("Le topo "+topo.getNom()+" a bien été crée.");
		return ActionSupport.SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
}
