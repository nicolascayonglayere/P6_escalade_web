package oc.P6.escalade.actions.utilisateur;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe action qui retourne les données nécessaires à la jsp affichant le compte d'un {@link Utilisateur}
 * @author nicolas
 *
 */
@Named
public class MonCompteAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Utilisateur utilisateur;
	private String pseudo;
	private ArrayList<TopoEmprunt> listTopoEmprunt;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private ArrayList<Topo> listTopoConstr;
	private ArrayList<CommentaireTopo> listCommentaire;
	private Map<String, Object> session;

	/**
	 * Méthode retournant les données nécessaires à la jsp affichant le compte d'un {@link Utilisateur}
	 */
	public String execute() {
		int idRole = 0;
		String username1 = ((Utilisateur) session.get("utilisateur")).getPseudo();
		logger.debug("Compte de "+username1);
		utilisateur = (Utilisateur) session.get("utilisateur");
		try {
			listTopoEmprunt = managerFactory.getTopoEmpruntManager().getListTopoEmprunt(utilisateur.getId());
			coordonneeUtilisateur = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(utilisateur.getId());
		} catch (CoordonneeUtilisateurException e ) {
			addActionMessage(e.getMessage());
			//e.printStackTrace();
			return ActionSupport.INPUT;
		} catch (UtilisateurException e1) {
			addActionMessage(e1.getMessage());
			//e1.printStackTrace();
			return ActionSupport.INPUT;
		}
		idRole = utilisateur.getId_Role();
		if(idRole == 1) {
			try {
				listTopoConstr = managerFactory.getTopoManager().getListTopoConstr(utilisateur.getPseudo());
			} catch (UtilisateurException e2) {
				addActionMessage(e2.getMessage());
				//e2.printStackTrace();
				return ActionSupport.INPUT;
			}
		}
		if(idRole == 2)
			listCommentaire = managerFactory.getCommentaireTopoManager().getListCommentaireTopo();
		logger.debug("Compte : "+username1+" - "+utilisateur.getRole()+" - "+utilisateur.getNom()+" - "+listTopoEmprunt.size()+" - "+coordonneeUtilisateur.getEmail());
		return SUCCESS;
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

	public ArrayList<Topo> getListTopoConstr() {
		return listTopoConstr;
	}

	public void setListTopoConstr(ArrayList<Topo> listTopoConstr) {
		this.listTopoConstr = listTopoConstr;
	}

	public ArrayList<CommentaireTopo> getListCommentaire() {
		return listCommentaire;
	}

	public void setListCommentaire(ArrayList<CommentaireTopo> listCommentaire) {
		this.listCommentaire = listCommentaire;
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
	
	public ArrayList<TopoEmprunt> getListTopoEmprunt() {
		return listTopoEmprunt;
	}

	public void setListTopoEmprunt(ArrayList<TopoEmprunt> listTopoEmprunt) {
		this.listTopoEmprunt = listTopoEmprunt;
	}
	
	
	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}
}
