package oc.P6.escalade.actions.utilisateur;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;

/**
 * Classe action permettant la validation par le modérateur des {@link CommentaireTopo} envoyés.
 * @author nicolas
 *
 */
@Named
public class ValidationCommentaireAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String pseudo;
	private String nomTopo;
	private String message;
	private CommentaireTopo commentaireTopo;
	
	/**
	 * Méthode pour valider le {@link CommentaireTopo}
	 * @return 
	 */
	public String valider() {
		logger.debug(pseudo+" - "+nomTopo+" - "+message);
		try {
			commentaireTopo = managerFactory.getCommentaireTopoManager().getCommentaireTopo(nomTopo, pseudo, message);
			managerFactory.getCommentaireTopoManager().modifCommentaireTopo(commentaireTopo);
			addActionMessage("Le commentaire de "+commentaireTopo.getAuteur().getPseudo()+" sur le topo "+commentaireTopo.getTopo().getNomTopo()+" a été validé." );
			return ActionSupport.SUCCESS;
		} catch (CommentaireTopoException e) {
			addActionMessage(e.getMessage());
			//e.printStackTrace();
			return ActionSupport.INPUT;
		}

	}
	
	/**
	 * Méthode pour rejeter le commentaire
	 * @return
	 */
	public String rejeter() {
		logger.debug(pseudo+" - "+nomTopo+" - "+message);
		try {
			commentaireTopo = managerFactory.getCommentaireTopoManager().getCommentaireTopo(nomTopo, pseudo, message);
			managerFactory.getCommentaireTopoManager().deleteCommentaireTopo(commentaireTopo);
			addActionMessage("Le commentaire de "+commentaireTopo.getAuteur().getPseudo()+" sur le topo "+commentaireTopo.getTopo().getNomTopo()+" a été supprimé." );
			return ActionSupport.SUCCESS;
		} catch (CommentaireTopoException e) {
			addActionMessage(e.getMessage());
			//e.printStackTrace();
			return ActionSupport.INPUT;
		}

	}

	//--Getter et Setter--//
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public CommentaireTopo getCommentaireTopo() {
		return commentaireTopo;
	}

	public void setCommentaireTopo(CommentaireTopo commentaireTopo) {
		this.commentaireTopo = commentaireTopo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
