package oc.P6.escalade.actions.utilisateur;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class GestionPretAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nom;
	private String pseudo;
	private Utilisateur utilisateur;
	private TopoEmprunt topoEmprunt;
	private Map<String, Object> session;
	
	public String retourner() {
		utilisateur = (Utilisateur) session.get("utilisateur");
		System.out.println("nom topo retourner "+nom+" nom emprunteur "+pseudo);
		TopoEmprunt vTopoEmp;
		try {
			vTopoEmp = managerFactory.getTopoEmpruntManager().getTopoEmprunt(nom, utilisateur);
			managerFactory.getTopoEmpruntManager().retourTopoEmprunt(vTopoEmp, utilisateur);
			
			addActionMessage("Vous avez rendu le topo "+vTopoEmp.getNom());
			return ActionSupport.SUCCESS;
		} catch (UtilisateurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	public String emprunter() {
		System.out.println("emprunt : "+nom);
		utilisateur = (Utilisateur) session.get("utilisateur");
		Topo vTopo;
		try {
			vTopo = managerFactory.getTopoManager().getTopo(nom);
			topoEmprunt = managerFactory.getTopoEmpruntManager().creerTopoEmprunt(vTopo, utilisateur);
			if (topoEmprunt == null) {
				addActionMessage("Il n'y a plus d'exemplaires disponibles");
				return ActionSupport.INPUT;
			}
			else {
				if (topoEmprunt.getId() > 0) {
					addActionMessage("Votre emprunt est bien enregistré");		
					return ActionSupport.SUCCESS;
				}
				else {
					addActionMessage("Vous avez deja emprunté ce topo.");
					return ActionSupport.INPUT;
				}
			}
		} catch (TopoException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
