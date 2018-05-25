package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireSite;

public interface CommentaireSiteManager {

	ArrayList<CommentaireSite> getListCommentaireSite();
	
	CommentaireSite getCommentaireSite();
	
	void creerCommentaireSite(CommentaireSite pCommSite);
}
