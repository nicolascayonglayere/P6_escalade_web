package oc.P6.escalade.business.impl.manager.commentaire;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSiteManager;
import oc.P6.escalade.consumer.DAO.impl.manager.commentaire.CommentaireSiteDaoImpl;
import oc.P6.escalade.model.bean.commentaire.CommentaireSite;

@Named
public class CommentaireSiteImpl extends AbstractDAOManager implements CommentaireSiteManager{

	@Inject
	private CommentaireSite commSite;
	
	@Inject
	private CommentaireSiteDaoImpl commSiteDao;// = (CommentaireSiteDaoImpl) getDAOFactory().getCommentaireSiteDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<CommentaireSite> getListCommentaireSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentaireSite getCommentaireSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerCommentaireSite(CommentaireSite pCommSite) {
		// TODO Auto-generated method stub
		
	}

}
