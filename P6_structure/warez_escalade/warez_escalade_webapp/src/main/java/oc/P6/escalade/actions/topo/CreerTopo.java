package oc.P6.escalade.actions.topo;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CreerTopo extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Topo topo;
	private Utilisateur utilisateur;
	private Map<String, Object>session;
	private File fichierImage;
	
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
		System.out.println("trace");
		utilisateur=(Utilisateur)session.get("utilisateur");

		System.out.println(topo.getNomTopo());
		if (managerFactory.getTopoManager().getTopo(topo.getNomTopo()) != null) {
			addActionMessage("Le topo "+topo.getNomTopo()+" existe déja.");
			return ActionSupport.INPUT;
		}
		else {
			topo.setAuteur(utilisateur);
			managerFactory.getTopoManager().creerTopo(topo);
			addActionMessage("Le topo "+topo.getNomTopo()+" a bien été crée.");
			session.put("topo", topo);
			return ActionSupport.SUCCESS;		
		}

	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public File getFichierImage() {
		return fichierImage;
	}
	public void setFichierImage(File fichierImage) {
		this.fichierImage = fichierImage;
	}
}
