package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import oc.P6.escalade.model.bean.commentaire.CommentaireSite;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface CommentaireSiteDao {

	boolean create(CommentaireSite pCommentaireSite);
	
	boolean delete (CommentaireSite pCommentaireSite);
	
	boolean update (CommentaireSite pCommentaireSite);
	
	CommentaireSite find(Utilisateur pAuteur);
}
