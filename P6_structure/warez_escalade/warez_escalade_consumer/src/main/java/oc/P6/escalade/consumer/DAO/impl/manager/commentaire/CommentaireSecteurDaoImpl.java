package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSecteurDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CommentaireSecteurDaoImpl extends AbstractDAO implements CommentaireSecteurDao{

	@Override
	public boolean create(CommentaireSecteur pCommentaireSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CommentaireSecteur pCommentaireSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommentaireSecteur pCommentaireSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentaireSecteur find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
