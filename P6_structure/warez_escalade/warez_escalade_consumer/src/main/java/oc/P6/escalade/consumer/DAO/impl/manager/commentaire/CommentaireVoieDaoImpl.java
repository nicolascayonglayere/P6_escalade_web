package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireVoieDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CommentaireVoieDaoImpl extends AbstractDAO implements CommentaireVoieDao{

	@Override
	public boolean create(CommentaireVoie pCommentaireVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CommentaireVoie pCommentaireVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommentaireVoie pCommentaireVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentaireVoie find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
