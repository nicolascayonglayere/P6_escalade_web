package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface CommentaireSecteurDao {

	boolean create(CommentaireSecteur pCommentaireSecteur);
	
	boolean delete (CommentaireSecteur pCommentaireSecteur);
	
	boolean update (CommentaireSecteur pCommentaireSecteur);
	
	CommentaireSecteur find(Utilisateur pAuteur);
}
