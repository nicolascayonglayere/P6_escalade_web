package oc.P6.escalade.business.impl.manager.commentaire;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSecteurManager;
import oc.P6.escalade.consumer.DAO.impl.manager.commentaire.CommentaireSecteurDaoImpl;
import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;

@Named
public class CommentaireSecteurImpl extends AbstractDAOManager implements CommentaireSecteurManager{

	@Inject
	private CommentaireSecteur commentaireSecteur;
	
	@Inject
	private CommentaireSecteurDaoImpl commSecteurDao;// = (CommentaireSecteurDaoImpl) getDAOFactory().getCommentaireSecteurDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<CommentaireSecteur> getListCommentaireSecteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentaireSecteur getCommentaireSecteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerCommentaireTopo(CommentaireSecteur pCommSecteur) {
		// TODO Auto-generated method stub
		
	}

}
