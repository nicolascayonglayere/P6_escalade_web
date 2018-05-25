package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSiteDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.commentaire.CommentaireSite;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CommentaireSiteDaoImpl extends AbstractDAO implements CommentaireSiteDao {

	@Override
	public boolean create(CommentaireSite pCommentaireSite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CommentaireSite pCommentaireSite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommentaireSite pCommentaireSite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentaireSite find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
