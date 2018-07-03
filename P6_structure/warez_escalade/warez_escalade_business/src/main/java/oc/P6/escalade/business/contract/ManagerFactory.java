package oc.P6.escalade.business.contract;

import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSecteurManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSiteManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireTopoManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireVoieManager;
import oc.P6.escalade.business.contract.manager.topo.SecteurManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.business.contract.manager.topo.VoieManager;
import oc.P6.escalade.business.contract.manager.utilisateur.CoordonneeUtilisateurManager;
import oc.P6.escalade.business.contract.manager.utilisateur.UtilisateurManager;

/**
 * Interface ManagerFactory et ses méthodes d'accès aux différents manager
 * @author nicolas
 *
 */
public interface ManagerFactory {

	UtilisateurManager getUtilisateurManager();
	CoordonneeUtilisateurManager getCoordonneeUtilisateurManager();
	
	TopoManager getTopoManager();
	SiteManager getSiteManager();
	SecteurManager getSecteurManager();
	VoieManager getVoieManager();
	
	CommentaireTopoManager getCommentaireTopoManager();
	CommentaireSiteManager getCommentaireSiteManager();
	CommentaireSecteurManager getCommentaireSecteurManager();
	CommentaireVoieManager getCommentaireVoieManager();
	
	TopoEmpruntManager getTopoEmpruntManager();
}
