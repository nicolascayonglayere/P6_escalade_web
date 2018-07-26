package oc.P6.escalade.actions.topo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui permet la création d'un {@link Topo}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
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
	

	/**
	 * Méthode qui crée le {@link Topo}
	 */
	public String execute() {
		GestionFichierProperties gfp = new GestionFichierProperties();
		System.out.println("trace");
		utilisateur=(Utilisateur)session.get("utilisateur");

		System.out.println(topo.getNomTopo());

		try {
			topo.setAuteur(utilisateur);
			topo = managerFactory.getTopoManager().creerTopo(topo);
			Path p = Paths.get(gfp.lireProp().getProperty("chemin.upload"), topo.getImage());
			gfp.creerDossierImg(p);
			addActionMessage("Le topo "+topo.getNomTopo()+" a bien été crée.");
			session.put("topo", topo);
			return ActionSupport.SUCCESS;
		} catch (TopoException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	//--Getter et Setter--//
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
}
