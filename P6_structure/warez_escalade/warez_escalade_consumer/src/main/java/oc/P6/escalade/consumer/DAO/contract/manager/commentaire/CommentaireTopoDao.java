package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface CommentaireTopoDao {

	boolean create(CommentaireTopo pCommentaireTopo);
	
	boolean delete (CommentaireTopo pCommentaireTopo);
	
	boolean update (CommentaireTopo pCommentaireTopo);
	
	CommentaireTopo find(Utilisateur pAuteur);
}
