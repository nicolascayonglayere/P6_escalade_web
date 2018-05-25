package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;


public interface CommentaireSecteurManager {

	ArrayList<CommentaireSecteur> getListCommentaireSecteur();
	
	CommentaireSecteur getCommentaireSecteur();
	
	void creerCommentaireTopo(CommentaireSecteur pCommSecteur);
}
