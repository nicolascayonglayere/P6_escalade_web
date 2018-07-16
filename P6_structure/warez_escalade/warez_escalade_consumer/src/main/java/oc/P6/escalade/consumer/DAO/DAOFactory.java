package oc.P6.escalade.consumer.DAO;

import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSecteurDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSiteDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireVoieDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.RoleDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;

/**
 * Interface DAOFactory et ses méthodes d'accès aux différents DAOManager
 * @author nicolas
 *
 */
public interface DAOFactory {
	
	UtilisateurManagerDAO getUtilisateurManagerDAO();
	CoordonneeUtilisateurDao getCoordonneeUtilisateurDao();
	
	TopoManagerDao getTopoManagerDao();
	SiteManagerDAO getSiteManagerDao();
	SecteurManagerDao getSecteurManagerDao();
	VoieManagerDao getVoieManagerDao();
	
	CommentaireTopoDao getCommentaireTopoDao();
	CommentaireSiteDao getCommentaireSiteDao();
	CommentaireSecteurDao getCommentaireSecteurDao();
	CommentaireVoieDao getCommentaireVoieDao();
	
	TopoEmpruntDao getTopoEmpruntDao();
	
	RoleDao getRoleDao();
}
