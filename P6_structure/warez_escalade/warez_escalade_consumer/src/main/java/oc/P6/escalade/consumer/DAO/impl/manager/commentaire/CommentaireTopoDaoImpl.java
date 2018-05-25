package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CommentaireTopoDaoImpl  extends AbstractDAO implements CommentaireTopoDao{

	@Override
	public boolean create(CommentaireTopo pCommentaireTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CommentaireTopo pCommentaireTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommentaireTopo pCommentaireTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentaireTopo find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
