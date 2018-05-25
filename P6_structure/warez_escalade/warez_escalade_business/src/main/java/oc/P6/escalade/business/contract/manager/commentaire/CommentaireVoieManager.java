package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;

public interface CommentaireVoieManager {

	ArrayList<CommentaireVoie> getListCommentaireVoie();
	
	CommentaireVoie getCommentaireVoie();
	
	void creerCommentaireVoie(CommentaireVoie pCommVoie);
}
