package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;

public interface CommentaireTopoManager {

	ArrayList<CommentaireTopo> getListCommentaireTopo();
	
	CommentaireTopo getCommentaireTopo();
	
	void creerCommentaireTopo(CommentaireTopo pCommTopo);
}
