package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public interface CommentaireVoieDao {

	boolean create(CommentaireVoie pCommentaireVoie);
	
	boolean delete (CommentaireVoie pCommentaireVoie);
	
	boolean update (CommentaireVoie pCommentaireVoie);
	
	CommentaireVoie find(Utilisateur pAuteur);
}
