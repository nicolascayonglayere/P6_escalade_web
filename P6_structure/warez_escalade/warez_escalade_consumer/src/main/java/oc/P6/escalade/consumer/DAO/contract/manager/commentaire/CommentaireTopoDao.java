package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;

public interface CommentaireTopoDao {

	boolean create(CommentaireTopo pCommentaireTopo);
	
	boolean delete (CommentaireTopo pCommentaireTopo);
	
	boolean update (CommentaireTopo pCommentaireTopo);
	
	CommentaireTopo find(String pNomTopo, String pPseudo, String pMessage);
	
	ArrayList<CommentaireTopo> listCommentaire();
	
	ArrayList<CommentaireTopo> listCommentaireValid(int pIdTopo);
}
