package oc.P6.escalade.business.impl.manager.commentaire;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireVoieManager;
import oc.P6.escalade.consumer.DAO.impl.manager.commentaire.CommentaireVoieDaoImpl;
import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;

@Named
public class CommentaireVoieImpl extends AbstractDAOManager implements CommentaireVoieManager{

	@Inject
	private CommentaireVoie commVoie;
	
	@Inject
	private CommentaireVoieDaoImpl commVoieDao;// = (CommentaireVoieDaoImpl) getDAOFactory().getCommentaireVoieDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<CommentaireVoie> getListCommentaireVoie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentaireVoie getCommentaireVoie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerCommentaireVoie(CommentaireVoie pCommVoie) {
		// TODO Auto-generated method stub
		
	}

}
